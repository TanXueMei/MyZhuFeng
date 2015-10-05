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
import myzhufeng.mydex.com.myzhufeng.fragments.customs.CustomAttentionFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.customs.CustomCllectFragment;
import myzhufeng.mydex.com.myzhufeng.fragments.customs.CustomHistoryFragment;

/**
 * Created by beyond on 2015/10/3.
 */
public class CustomFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private ViewPager pager;
    private TabLayout tabLayout;
    public CustomFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_custom,container,false);
        pager= (ViewPager) view.findViewById(R.id.custom_view_pager);
        tabLayout= (TabLayout) view.findViewById(R.id.custom_tab_bar);

        if(tabLayout!=null) {

            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText("关注");
            tabLayout.addTab(tab);

            tab = tabLayout.newTab();
            tab.setText("收藏");
            tabLayout.addTab(tab);

            tab = tabLayout.newTab();
            tab.setText("历史");
            tabLayout.addTab(tab);

            tabLayout.setOnTabSelectedListener(this);

            List<Fragment> fragments = new LinkedList<Fragment>();
            fragments.add(new CustomAttentionFragment());
            fragments.add(new CustomCllectFragment());
            fragments.add(new CustomHistoryFragment());
            //fragment与viewpager联系起来
            CommonFragmentAdapter adapter = new CommonFragmentAdapter(getChildFragmentManager(), fragments);
            pager.setAdapter(adapter);

            //viewpager与tablayout联系起来
            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }
        return view;
    }
//  tabLayout.setOnTabSelectedListener(this);实现的三个方法
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
