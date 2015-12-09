package com.example.myapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.example.myapp.fragment.TestText2Fragment;

/**
 * Created by linniu on 2015/12/9.
 */
public class PagerActivity extends FragmentActivity {

    private ViewPager mPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_activity);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    private class MyAdapter extends FragmentStatePagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            TestText2Fragment fragment = new TestText2Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("Text", String.valueOf(i));
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 10;
        }
    }

//    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
//        public ScreenSlidePagerAdapter(FragmentManager fm) {
//            super(fm);
//        } @
//                  Override
//          public Fragment getItem(int position) {
//            return new ScreenSlidePageFragment();
//        } @
//                  Override
//          public int getCount() {
//            return NUM_PAGES;
//        }
//    }
}