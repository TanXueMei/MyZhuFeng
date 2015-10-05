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

import myzhufeng.mydex.com.myzhufeng.R;
import myzhufeng.mydex.com.myzhufeng.adapters.CommonFragmentAdapter;
import myzhufeng.mydex.com.myzhufeng.fragments.discovers.DiscoverAnchorFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.discovers.DiscoverCategoryFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.discovers.DiscoverDrectFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.discovers.DiscoverListFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.discovers.DiscoverRecommendFragment;

/**
 * Created by beyond on 2015/10/3.
 */
public class DiscoverFragment extends Fragment implements TabLayout.OnTabSelectedListener {

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
}
