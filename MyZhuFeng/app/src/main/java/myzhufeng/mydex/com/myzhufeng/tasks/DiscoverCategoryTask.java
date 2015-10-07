package myzhufeng.mydex.com.myzhufeng.tasks;

import android.util.Log;

import myzhufeng.mydex.com.myzhufeng.Constants;
import myzhufeng.mydex.com.myzhufeng.client.DiscoverClientAPI;

/**
 * Created by beyond on 2015/10/6.
 */
public class DiscoverCategoryTask extends BaseTask {
    public DiscoverCategoryTask(TaskCallBack callBack){
        super(callBack);
    }
    @Override
    protected TaskResult doInBackground(String... params) {
        Log.d("DiscoverCategoryFragment", "进到了发现分类中的异步任务DiscoverCategoryTask");
        TaskResult result=new TaskResult();
        result.action= Constants.TASK_ACTION_DISCOVERY_CATEGORIES;
        result.data= DiscoverClientAPI.getDiscoveryCategories();
        Log.d("DiscoverCategoryFragment", "DiscoverCategoryTask"+result);
        return result;
    }
}
