package myzhufeng.mydex.com.myzhufeng.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.json.JSONObject;

import java.util.LinkedList;

import myzhufeng.mydex.com.myzhufeng.Constants;
import myzhufeng.mydex.com.myzhufeng.R;
import myzhufeng.mydex.com.myzhufeng.adapters.CommonFragmentAdapter;
import myzhufeng.mydex.com.myzhufeng.fragments.discovers.DiscoverAnchorFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.discovers.DiscoverCategoryFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.discovers.DiscoverDrectFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.discovers.DiscoverListFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.discovers.DiscoverRecommendFragment;
import myzhufeng.mydex.com.myzhufeng.tasks.DiscoveryTabsTask;
import myzhufeng.mydex.com.myzhufeng.tasks.TaskCallBack;
import myzhufeng.mydex.com.myzhufeng.tasks.TaskResult;

/**
 * Created by beyond on 2015/10/3.
 */
public class DiscoverFragment extends Fragment implements TabLayout.OnTabSelectedListener, TaskCallBack {

    private ViewPager pager;
    private TabLayout tabLayout;
    public DiscoverFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        pager = (ViewPager) view.findViewById(R.id.discover_view_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.discover_tab_bar);

        if (tabLayout != null) {
          ///////////////////////////////////////////
            //调用异步任务，获取tabs的值
            DiscoveryTabsTask task=new DiscoveryTabsTask(this);
                 task.execute();


            //////////////////////////////////////
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText("推荐");
            tabLayout.addTab(tab);

            tab = tabLayout.newTab();
            tab.setText("分类");
            tabLayout.addTab(tab);

            tab = tabLayout.newTab();
            tab.setText("直播");
            tabLayout.addTab(tab);

            tab = tabLayout.newTab();
            tab.setText("榜单");
            tabLayout.addTab(tab);

            tab = tabLayout.newTab();
            tab.setText("主播");
            tabLayout.addTab(tab);

           tabLayout.setOnTabSelectedListener(this);

        LinkedList<Fragment> fragments = new LinkedList<Fragment>();
            fragments.add(new DiscoverRecommendFragment());
            fragments.add(new DiscoverCategoryFragment());
            fragments.add(new DiscoverDrectFragment());
            fragments.add(new DiscoverListFragment());
            fragments.add(new DiscoverAnchorFragment());

        CommonFragmentAdapter adapter = new CommonFragmentAdapter(getChildFragmentManager(), fragments);
        pager.setAdapter(adapter);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
        return view;
    }
/////////////////////////////////////////////////
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
////////////////////////////////////////////////////
    //异步任务获取值回调的方法
    @Override
    public void onTaskFinished(TaskResult result) {
if(result!=null){
    int action=result.action;
    switch (action){
        case Constants.TASK_ACTION_DISCOVERY_TABS:
            setupTabs((JSONObject)result.data);
            break;
    }
}

    }

    private void setupTabs(JSONObject data) {
        Log.d("Discovery", "Tabs " + data);
    }
}
