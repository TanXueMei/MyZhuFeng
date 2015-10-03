package myzhufeng.mydex.com.myzhufeng.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import myzhufeng.mydex.com.myzhufeng.R;

/**
 * Created by beyond on 2015/10/3.
 */
public class ProfileFragment extends Fragment {

    private ViewPager pager;
    public ProfileFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        pager= (ViewPager) view.findViewById(R.id.profile_view_pager);
        return view;
    }
}
