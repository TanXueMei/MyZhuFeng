package myzhufeng.mydex.com.myzhufeng.fragments.downloads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import myzhufeng.mydex.com.myzhufeng.R;

/**
 * Created by beyond on 2015/10/5.
 */
public class DownloadAlbumFragment extends Fragment {

    public DownloadAlbumFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_download_album,container,false);

        return view;
    }
}
