package myzhufeng.mydex.com.myzhufeng.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import myzhufeng.mydex.com.myzhufeng.R;
import myzhufeng.mydex.com.myzhufeng.adapters.CommonFragmentAdapter;
import myzhufeng.mydex.com.myzhufeng.fragments.downloads.DownloadAlbumFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.downloads.DownloadDownloadingFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.downloads.DownloadVoiceFragment;

/**
 * Created by beyond on 2015/10/3.
 */
public class DownloadFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private ViewPager pager;
    private TabLayout tabLayout;
    public DownloadFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmet_download,container,false);
        pager= (ViewPager) view.findViewById(R.id.download_view_pager);
        tabLayout= (TabLayout) view.findViewById(R.id.download_tab_bar);
        if(tabLayout!=null){
            TabLayout.Tab tab=tabLayout.newTab();
            tab.setText("专辑");
            tabLayout.addTab(tab);

            tab=tabLayout.newTab();
            tab.setText("声音");
            tabLayout.addTab(tab);

            tab=tabLayout.newTab();
            tab.setText("下载中");
            tabLayout.addTab(tab);

            //给tablayout添加监听
            tabLayout.setOnTabSelectedListener(this);

            List<Fragment> fragments=new LinkedList<Fragment>();
            fragments.add(new DownloadAlbumFragment());
            fragments.add(new DownloadVoiceFragment());
            fragments.add(new DownloadDownloadingFragment());

            CommonFragmentAdapter adapter=new CommonFragmentAdapter(getChildFragmentManager(),fragments);
            pager.setAdapter(adapter);

            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }

        return view;
    }
    //////////////////////////////////////////////////////////
// tabLayout.setOnTabSelectedListener(this);实现的三个方法
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        pager.setCurrentItem(tab.getPosition(),false);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    ///////////////////////////////////////////////
}
