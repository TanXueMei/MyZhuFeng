package myzhufeng.mydex.com.myzhufeng.tasks;

/**
 * Created by beyond on 2015/10/6.
 */

/*
当异步任务执行完成，获取的结果交给回调接口
利用TaskResult来抽象结果的实际类型
 */
public interface TaskCallBack {
    void onTaskFinished(TaskResult result);
}
