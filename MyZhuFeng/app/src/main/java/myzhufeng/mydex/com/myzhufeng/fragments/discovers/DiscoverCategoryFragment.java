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
   private Context context;
    private ListView categoryListView;
    private DiscoverCategoryAdaper adaper;
    private LinkedList<Category> categoryLinkedList;
    public DiscoverCategoryFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("DiscoverCategoryFragment","进到了发现分类中");
       View view=inflater.inflate(R.layout.fragment_discover_category, container, false);
        categoryListView= (ListView) view.findViewById(R.id.fragment_discover_category_listview);
        categoryLinkedList=new LinkedList<Category>();

        Category category=new Category();
        TaskResult result=new TaskResult();
        JSONObject jsonObject= (JSONObject) result.data;
        if(jsonObject!=null){
            try {
                int code=jsonObject.getInt("ret");
                if(code==0) {
                    List<Category> categories = DataParser.parseDiscoverCategories(jsonObject);
                    if(categories!=null){
                        categoryLinkedList.clear();

                        categoryLinkedList.addAll(categories); // 直接添加

                       // adapter.notifyDataSetChanged();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        categoryLinkedList.add(category);
        context=getContext();
        DiscoverCategoryTask task=new DiscoverCategoryTask(this);
        task.execute();
        Log.d("adapter", "context=" + context);
        Log.d("adapter", "categoryLinkedList=" + categoryLinkedList);
        adaper=new DiscoverCategoryAdaper(context,categoryLinkedList);
        Log.d("adapter", "adaper=" + adaper);
        categoryListView.setAdapter(adaper);

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
                adaper.notifyDataSetChanged();}
        }

    } catch (JSONException e) {
        e.printStackTrace();
    }
}

    }
}
