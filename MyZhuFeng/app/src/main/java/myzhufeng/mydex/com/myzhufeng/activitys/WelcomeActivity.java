package myzhufeng.mydex.com.myzhufeng.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;


import myzhufeng.mydex.com.myzhufeng.BuildConfig;
import myzhufeng.mydex.com.myzhufeng.Constants;
import myzhufeng.mydex.com.myzhufeng.R;

public class WelcomeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

//        进入欢迎页，要保存已经显示的版本信息

        //对sp进行写，修改需要获取的Editor对象
        SharedPreferences sp=getSharedPreferences(Constants.SP_NAME_APP,MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
//        保存版本号，对应的值：BuildConfig.VERSION_NAME，以键值对的形式存起来
        editor.putString(Constants.SP_KEY_WELCOME_SHOW_VER, BuildConfig.VERSION_NAME);
        //执行完写，或者修改的操作后，只有调用sp的commit方法，数据才会保存下来 ,apply也可以
        editor.apply();
    }

    public void btnGoMain(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        btnGoMain(null);
    }
}
