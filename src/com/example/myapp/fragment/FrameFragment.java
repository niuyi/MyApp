package com.example.myapp.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.example.myapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linniu on 2015/12/10.
 */
public class FrameFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_fragment, container, false);
        ViewPager pager = (ViewPager)view.findViewById(R.id.viewPager);
        pager.setAdapter(new MyAdapter(getActivity().getFragmentManager()));
        pager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                Log.i("mPager", "transformPage: " + position);
                int pageWidth = view.getWidth();

                if (position < -1) { // [-∞ ,-1)
// 这一页已经是最左边的屏幕页
                    view.setAlpha(0);
                } else if (position <= 0) { // [-1,0]
                    view.setAlpha(1 - Math.abs(position));
                    view.setTranslationX(pageWidth * -position);
                } else if (position <= 1) { // (0,1]
                    view.setAlpha(1 - position);
                    view.setTranslationX(pageWidth * -position);
                } else { // (1,+∞]
                    view.setAlpha(0);
                }
            }
        });
        pager.requestFocus();
        return view;
    }

    public class MyAdapter extends FragmentPagerAdapter {

        private List<String> list = new ArrayList<String>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
            list.add("http://image.box.xiaomi.com/mfsv2/download/s010/p0183FWuG4X9/zQWxtA4hlYmCp7.jpg");
            list.add("http://image.box.xiaomi.com/mfsv2/download/s010/p01ldVQQd98U/gZEPLDqq4oHY3a.jpg");
            list.add("http://image.box.xiaomi.com/mfsv2/download/s010/p01wU0PNvJO7/yMSVzIpl3Q1T6o.jpg");
            list.add("http://image.box.xiaomi.com/mfsv2/download/s010/p01oJiNWiuQe/FfSMO7E6ISXjYy.jpg");
            list.add("http://image.box.xiaomi.com/mfsv2/download/s010/p01pybo2Qd00/IaoqQDPLHicPs9.jpg");
        }

        @Override
        public Fragment getItem(int i) {
            Log.i("FrameFragment", "get item: " + i);
            ImageFragment fragment = new ImageFragment();
            fragment.url = list.get(i);
            return fragment;
        }

        @Override
        public int getCount() {
            return list.size();
        }


    }

    public static class ImageFragment extends Fragment{

        public String url;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Log.i("FrameFragment", "onCreateView " + url);

            ImageView iv = new ImageView(getActivity());
            FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(p);

            Picasso.with(getActivity()).load(url).into(iv);

            return iv;
        }

        @Override
        public void onDestroyView() {
            Log.i("FrameFragment", "onDestroyView " + url);
            super.onDestroyView();
        }

        @Override
        public void onDestroy() {
            Log.i("FrameFragment", "onDestroy " + url);
            super.onDestroy();
        }
    }
}