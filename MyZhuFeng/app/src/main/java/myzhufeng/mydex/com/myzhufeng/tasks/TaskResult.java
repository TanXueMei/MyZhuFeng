package myzhufeng.mydex.com.myzhufeng.tasks;

/**
 * Created by beyond on 2015/10/6.
 */

/*
异步任务返回结果，通用数据对象
包含：1、action 代表当前的结果由哪一个异步任务来返回的
     2、data 代表Object对象，就是异步任务返回的实际对象
 */
public class TaskResult {

    public int action;//异步任务标识
    public Object data;//结果数据
}
