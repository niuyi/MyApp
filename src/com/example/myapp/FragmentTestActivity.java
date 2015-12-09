package com.example.myapp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import com.example.myapp.fragment.TestButtonFragment;
import com.example.myapp.fragment.TestTextFragment;

/**
 * Created by linniu on 2015/12/9.
 */
public class FragmentTestActivity extends Activity {

    private TestButtonFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        fragment = new TestButtonFragment();
        ft.replace(R.id.root, fragment, TestButtonFragment.class.getCanonicalName());
        ft.commit();
    }

    public void gotoNewFragment(View view){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(fragment);
        TestTextFragment newFrag = new TestTextFragment();
        ft.add(R.id.root, newFrag, TestTextFragment.class.getCanonicalName());
//        ft.show(newFrag);
        ft.addToBackStack(null);
        ft.commit();
    }
}