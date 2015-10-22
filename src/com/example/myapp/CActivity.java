package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by niuyi on 2015/10/22.
 */
public class CActivity extends Activity {
    private static final String TAG = "CActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_activity);
        android.util.Log.i(TAG, "onCreate");
    }


    public void gotoC(View view){
        Intent in = new Intent();
        in.setClass(this, BActivity.class);
//        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        in.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(in);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        android.util.Log.i(TAG, "onNewIntent");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.util.Log.i(TAG, "onDestroy");
    }
}