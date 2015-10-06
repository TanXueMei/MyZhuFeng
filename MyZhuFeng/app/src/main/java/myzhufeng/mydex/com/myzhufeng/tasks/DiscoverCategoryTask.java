package myzhufeng.mydex.com.myzhufeng.tasks;

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
        TaskResult result=new TaskResult();
        result.action= Constants.TASK_ACTION_DISCOVERY_CATEGORIES;
        result.data= DiscoverClientAPI.getDiscoveryCategories();

        return result;
    }
}
