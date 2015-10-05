package myzhufeng.mydex.com.myzhufeng;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import myzhufeng.mydex.com.myzhufeng.activitys.MainActivity;
import myzhufeng.mydex.com.myzhufeng.activitys.WelcomeActivity;


public class SplashActivity extends FragmentActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        开个线程，让扉页停留两分钟再跳转到欢迎页中
        Thread thread=new Thread(this);
        thread.start();

    }


    @Override
    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SharedPreferences sp=getSharedPreferences(Constants.SP_NAME_APP, MODE_PRIVATE);
        //获取上一次显示引导页的版本信息
        String wsv=sp.getString(Constants.SP_KEY_WELCOME_SHOW_VER,null);

        // Intent intent=new Intent(this,MainActivity.class);
        Intent intent=new Intent(this,MainActivity.class);

        //使用Gradle模式获取版本信息 BuildConfig来获取

        //    如果版本不同，那么欢迎页启动

        if(!BuildConfig.VERSION_NAME.equals(wsv)){
         //TODO 显示欢迎页
            intent=new Intent(this, WelcomeActivity.class);
        }
        startActivity(intent);
        finish();

    }





}
