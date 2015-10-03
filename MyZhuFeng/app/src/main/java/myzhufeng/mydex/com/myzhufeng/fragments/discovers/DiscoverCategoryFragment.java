package myzhufeng.mydex.com.myzhufeng.fragments.discovers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import myzhufeng.mydex.com.myzhufeng.R;

/**
 * Created by beyond on 2015/10/3.
 */
public class DiscoverCategoryFragment extends Fragment {

    public DiscoverCategoryFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_discover_category,container,false);

        return view;
    }
}
