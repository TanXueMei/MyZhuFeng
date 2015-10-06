package myzhufeng.mydex.com.myzhufeng.tasks;

import myzhufeng.mydex.com.myzhufeng.Constants;
import myzhufeng.mydex.com.myzhufeng.client.DiscoverClientAPI;

/**
 * Created by beyond on 2015/10/6.
 */
public class DiscoveryTabsTask extends BaseTask {

    public DiscoveryTabsTask(TaskCallBack callBack){
        super(callBack);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult result=new TaskResult();
        //异步任务标识符，代表是哪个异步任务，此处是获取发现的tabs异步任务
        result.action= Constants.TASK_ACTION_DISCOVERY_TABS;
        //通过url获取API，转化为了jsonObject数据，data为jsonObject数据
        result.data= DiscoverClientAPI.getDiscoverTabs();
        return result;
    }

}
