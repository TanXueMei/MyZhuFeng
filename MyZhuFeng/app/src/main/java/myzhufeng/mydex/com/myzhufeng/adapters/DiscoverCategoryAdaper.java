package myzhufeng.mydex.com.myzhufeng.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import myzhufeng.mydex.com.myzhufeng.R;
import myzhufeng.mydex.com.myzhufeng.modle.Category;
import myzhufeng.mydex.com.myzhufeng.tasks.ImageLoadTask;

/**
 * Created by beyond on 2015/10/6.
 */

//发现 “分类”部分的列表适配器
public class DiscoverCategoryAdaper extends BaseAdapter{
    private final Context context;
    private final List<Category> categoryList;
    //////////////////////////////////////
    //利用volley进行网络加载
    //API入口点
    private static final String API_POINT = "http://mobile.ximalaya.com/mobile";
    //发现“分类”数据url
    String categoryurl=API_POINT+"/discovery/v1/categories?device=android&picVersion=10&scale=2";
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private TextView txtCategory1;
    private NetworkImageView imagC1;


    ////////////////////////////////////////////////////////////////////
    public DiscoverCategoryAdaper(Context context,List<Category> categoryList){
        this.context=context;
        this.categoryList=categoryList;
    }

    @Override
    public int getCount() {
        int count=0;
        if(categoryList!=null){
            //采用了一行两列的方式，所以数量应该进行除2的操作，并且补充余数
            //用两个分类实现一行，即两个分类为一个item
            count=categoryList.size();
            count=count/2+(count%2); //实际分类的行数

        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("DiscoverCategoryFragment", "进到了发现分类中的适配器DiscoverCategoryAdaper");
      View view=null;

        //1、convertView的复用
      if(convertView!=null){
          view=convertView;
      }else {
          LayoutInflater inflater=LayoutInflater.from(context);
          view=inflater.inflate(R.layout.item_category, parent, false);
      }
        //2、ViewHodler的处理
      CategoryViewHodler hodler=(CategoryViewHodler) view.getTag();

        if(hodler==null){
            hodler=new CategoryViewHodler();
            hodler.txtCategory1 = (TextView) view.findViewById(R.id.item_category_c1);
            hodler.txtCategory2= (TextView) view.findViewById(R.id.item_category_c2);
            hodler.imagC1 = (NetworkImageView) view.findViewById(R.id.item_category_c1_icon);
            hodler.imagC2= (NetworkImageView) view.findViewById(R.id.item_category_c2_icon);
            view.setTag(hodler);
        }
     //3、内容的展示，（所有Hodler内部的对象，应该全部更新内容
       //3、1 获取item中的左边的位置，右边的为左边的位置加1
        int c1Index=position*2;
        int c2Index=c1Index+1;

         Category c1=categoryList.get(c1Index);
         Category c2=null;

        int size=categoryList.size();
        if(c2Index<size){
            c2=categoryList.get(c2Index);
        }
        ////////////////////////////////////////////////////////////
        //用Volley给c1设置内容
        hodler.txtCategory1.setText(c1.getTitle());//设置左边标题
       // hodler.txtCategory2.setText(c2.getTitle());//设置右边的标题
        //第一步：启动volley的线程请求队列
        requestQueue=Volley.newRequestQueue(context.getApplicationContext());
        //第二步：图片加载器
        imageLoader=new ImageLoader(requestQueue,new ImageLoader.ImageCache(){
           private LruCache<String,Bitmap> cache=new LruCache<String,Bitmap>(30);
            @Override
            public Bitmap getBitmap(String s) {
                return cache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
               cache.put(s,bitmap);
            }
        });
        //第三步：请求网络

        final CategoryViewHodler finalHodler = hodler;
        ImageRequest imageRequest=new ImageRequest(
                c1.getCoverPath(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        finalHodler.imagC1.setImageBitmap(bitmap);
                    }
                }, 0, 0, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                       finalHodler.imagC1.setImageResource(R.mipmap.ic_launcher);
                    }
                }


        );
       requestQueue.add(imageRequest);
      // hodler.imagC1.setImageUrl(c1.getCoverPath(),imageLoader);//设置左边图片
       // hodler.imagC2.setImageUrl(c2.getCoverPath(),imageLoader);


        ///////////////////////////////////////////////////////////

//        //3、2 设置c1的内容
//
//          hodler.txtCategory1.setText(c1.getTitle());
//             //设置c1的图片
//          String c1Path=c1.getCoverPath();
//        // 设置 ImageView 的 tag，异步任务通过 ImageView Tag的检查来判断，是否设置图片
//         hodler.imagC1.setTag(c1Path);
//
//        if (c1Path != null) {
//            ImageLoadTask task = new ImageLoadTask(hodler.imagC1);
//            task.execute(c1Path);
//        }
        // 3.3 设置 C2 的内容

        // 因为在有些情况下 这个控件要隐藏，还有需要显示的情况
//        hodler.txtCategory2.setVisibility(View.VISIBLE);
//        hodler.imagC2.setVisibility(View.VISIBLE);
//        hodler.imagC2.setTag(null);
//        hodler.imagC2.setImageResource(R.mipmap.ic_launcher);
//
//        if (c2 != null) {
//            hodler.txtCategory2.setText(c2.getTitle());
//
//            // TODO 加载C2图片
//
//            String coverPath = c2.getCoverPath();
//
//                // 设置图片的 Tag
//                hodler.imagC2.setTag(coverPath);
//
//                if (coverPath != null) {
//                    ImageLoadTask task = new ImageLoadTask(hodler.imagC2);
//                    task.execute(coverPath);
//                }
//            }
//            // 最后一个为 null
//            hodler.txtCategory2.setVisibility(View.INVISIBLE);
//            hodler.imagC2.setVisibility(View.INVISIBLE);
//


        return view;
    }
    private final static class CategoryViewHodler{
        public TextView txtCategory1;
        public TextView txtCategory2;
        public NetworkImageView imagC1;
        public NetworkImageView imagC2;
    }
}
