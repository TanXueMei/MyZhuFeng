package myzhufeng.mydex.com.myzhufeng.tasks;

import android.os.AsyncTask;

/**
 * Created by beyond on 2015/10/6.
 */
abstract class BaseTask extends AsyncTask<String,Integer,TaskResult> {
    private TaskCallBack callBack;
    public BaseTask(TaskCallBack callBack){
        this.callBack=callBack;
    }

    @Override
    protected void onPostExecute(TaskResult taskResult) {
       if(callBack!=null){
           callBack.onTaskFinished(taskResult);
       }
    }
}
