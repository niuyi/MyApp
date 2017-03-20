package com.example.myapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.myapp.fragment.FrameFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by linniu on 2015/12/10.
 */
public class SlideActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_activity);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.root, new LoadingFragment());
        ft.commit();
    }


    public static class LoadingFragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            TextView tv = new TextView(getActivity());
            tv.setText("Loading");

            Picasso.with(getActivity()).load("http://image.box.xiaomi.com/mfsv2/download/s010/p0183FWuG4X9/zQWxtA4hlYmCp7.jpg").fetch(new Callback() {
                @Override
                public void onSuccess() {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.root, new FrameFragment());
                    ft.commit();
                }

                @Override
                public void onError() {

                }
            });
            return tv;
        }
    }
}