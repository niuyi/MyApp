package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by niuyi on 2015/10/22.
 */
public class AActivity extends Activity {

    private static final String TAG = "AActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity);
        android.util.Log.i(TAG, "onCreate");
    }

    public void gotoB(View view){
        Intent in = new Intent();
        in.setClass(this, BActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        in.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(in);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        android.util.Log.i(TAG, "onNewIntent");
    }
}