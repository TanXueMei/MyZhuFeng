package myzhufeng.mydex.com.myzhufeng.activitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import myzhufeng.mydex.com.myzhufeng.R;
import myzhufeng.mydex.com.myzhufeng.fragments.CustomFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.DiscoverFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.DownloadFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.ProfileFragment;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
        initView();
    }

//    初始化Fragment
    private void initFragments(){
        if(fragments==null){
            fragments=new LinkedList<Fragment>();
            //TODO　添加相应的Ｆｒａｇｍｅｎｔ
            fragments.add(new DiscoverFragment());
            fragments.add(new CustomFragment());
            fragments.add(new DownloadFragment());
            fragments.add(new ProfileFragment());

            FragmentManager manager=getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();

            //利用hide，show来实现Fragment切换
            //因此建议使用FragmentLayout
            if(fragments.size()>0){
                for(Fragment fragment:fragments){
                    transaction.add(R.id.main_fragment_container,fragment);
                    transaction.hide(fragment);
                }
                transaction.show(fragments.get(0));

            }
            transaction.commit();
        }

    }
//    初始化界面控件
    private void initView(){
        RadioGroup tabBar= (RadioGroup) findViewById(R.id.main_tab_bar);
        tabBar.setOnCheckedChangeListener(this);
    }

    //对RadioGroup进行点击切换Fragment

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index=0;
        switch (checkedId){
            case R.id.main_tab_item_discover:
                index=0;
                break;
            case R.id.main_tab_item_custom:
                index = 1;
                break;
            case R.id.main_tab_item_download:
                index = 2;
                break;
            case R.id.main_tab_item_profile:
                index = 3;
                break;
        }

        // TODO 切换隐藏和显示
        int size=fragments.size();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

         for(int i=0;i<size;i++){
             if(i!=index){
                 transaction.hide(fragments.get(i));

             }else {
                 transaction.show(fragments.get(i));
             }
         }
        transaction.commit();

    }

    //两次返回的提示退出

    //后退键上一次点击的时间
    private long lastPressedTime;

    @Override
    public void onBackPressed() {
        //利用上一次，和本次的时间，进行对比，只要在2s之内，就默认为点击了两次，直接退出
        long lg=System.currentTimeMillis();
        if(lg-lastPressedTime<=2000){
            finish();
        }else {
            Toast.makeText(this,"再按一下退出",Toast.LENGTH_SHORT).show();;
            lastPressedTime=lg;
        }
    }


}
