package myzhufeng.mydex.com.myzhufeng.fragments.discovers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import myzhufeng.mydex.com.myzhufeng.Constants;
import myzhufeng.mydex.com.myzhufeng.R;
import myzhufeng.mydex.com.myzhufeng.adapters.DiscoverCategoryAdaper;
import myzhufeng.mydex.com.myzhufeng.client.DataParser;
import myzhufeng.mydex.com.myzhufeng.modle.Category;
import myzhufeng.mydex.com.myzhufeng.provider.DatabaseContract;
import myzhufeng.mydex.com.myzhufeng.tasks.DiscoverCategoryTask;
import myzhufeng.mydex.com.myzhufeng.tasks.TaskCallBack;
import myzhufeng.mydex.com.myzhufeng.tasks.TaskResult;

/**
 * Created by beyond on 2015/10/3.
 */
public class DiscoverCategoryFragment extends Fragment implements TaskCallBack {

    private ListView categoryListView;
    private DiscoverCategoryAdaper adaper;
    private LinkedList<Category> categoryLinkedList;
    public DiscoverCategoryFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryLinkedList = new LinkedList<Category>();

        Context context = getContext();

        ContentResolver resolver = context.getContentResolver();

        // 利用onCreate 一次调用的方式，从数据库 Provider 查询上一次获取的 分类列表，然后
        // 解析，并且显示，显示之后，再开启网络，进行加载

        Cursor cursor = resolver.query(
                DatabaseContract.DiscoverCategories.CONTENT_URI,
                null,
                null,
                null,
                DatabaseContract.DiscoverCategories.ORDER_NUM + " ASC" // 升序排列 按照 orderNum
        );

        if (cursor != null) {

            while (cursor.moveToNext()) {

                Category category = new Category();

                // 内部解析 Cursor
                category.parseCursor(cursor);

                categoryLinkedList.add(category);

            }

            cursor.close();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("DiscoverCategoryFragment", "进到了发现分类中");
       View view=inflater.inflate(R.layout.fragment_discover_category, container, false);
        categoryListView= (ListView) view.findViewById(R.id.fragment_discover_category_listview);
        adaper=new DiscoverCategoryAdaper(getContext(),categoryLinkedList);
        categoryListView.setAdapter(adaper);
        DiscoverCategoryTask task=new DiscoverCategoryTask(this);
        task.execute();

        return view;
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if(result!=null){
            int action=result.action;
            if(action== Constants.TASK_ACTION_DISCOVERY_CATEGORIES){
                setupCategories((JSONObject)result.data);
            }
        }
    }
//更新分类列表
    private void setupCategories(JSONObject json) {
        Log.d("DiscoverCategoryFragment", "进到了发现分类中的异步任务setupCategories");
    if(json!=null){
      try {
        int code=json.getInt("ret");
        if(code==0){
            List<Category> categories= DataParser.parseDiscoverCategories(json);
            if(categories!=null){
                categoryLinkedList.clear();
                categoryLinkedList.addAll(categories);
                adaper.notifyDataSetChanged();

                saveCategories(categories);
            }else{
                //显示错误信息
            }
        }

    } catch (JSONException e) {
        e.printStackTrace();
    }
}

    }
    /**
     * 保存从网络获取的分类，存储到ContentProvider
     *
     * @param categories
     */
    private void saveCategories(List<Category> categories) {
        if (categories != null) {

            Context context = getContext();
            if (context != null) {
                ContentResolver resolver = context.getContentResolver();
                for (Category category : categories) {
                    try {
                        resolver.insert(
                                DatabaseContract.DiscoverCategories.CONTENT_URI,
                                category.prepareValues()
                        );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
    }


}
