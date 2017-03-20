package com.example.myapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import com.example.myapp.fragment.TestText2Fragment;

/**
 * Created by linniu on 2015/12/9.
 */
public class PagerActivity extends FragmentActivity {

    private ViewPager mPager;
    private static final float MIN_SCALE = 0.75f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_activity);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mPager.setPageTransformer(true, new ViewPager.PageTransformer() {
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