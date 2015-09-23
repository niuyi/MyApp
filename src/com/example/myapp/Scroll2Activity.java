package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;


/**
 * Created by linniu on 2015/9/12.
 */
public class Scroll2Activity extends Activity {

    private View mView;
    private Handler h = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll2);
        mView = findViewById(R.id.root);
    }

    public void onTest(View view){
        mView.scrollBy(-10, -10);
        h.post(new Runnable() {
            @Override
            public void run() {
                Log.i("Scroll2Activity", String.format("sx(%d) sy(%d)", mView.getScrollX(), mView.getScrollY()));
            }
        });
    }
}