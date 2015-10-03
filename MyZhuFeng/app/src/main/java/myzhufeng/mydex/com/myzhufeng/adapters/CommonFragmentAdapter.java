package myzhufeng.mydex.com.myzhufeng.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by beyond on 2015/10/3.
 */
public class CommonFragmentAdapter  extends FragmentPagerAdapter{
    private final List<Fragment> fragmentslist;

    public CommonFragmentAdapter(FragmentManager fm,List<Fragment> fragments){
        super(fm);
        this.fragmentslist=fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentslist.get(position);
    }

    @Override
    public int getCount() {
        int count=0;
        if(fragmentslist!=null){
            count=fragmentslist.size();
        }
        return count;
    }
}
