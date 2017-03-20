package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowId;
import android.widget.TextView;

/**
 * Created by linniu on 2015/7/18.
 */
public class FocusActivity extends Activity {

    private TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.focus_activity);

        tv = (TextView)findViewById(R.id.tv_text);
    }

    @Override
    protected void onResume() {
        Log.i("FocusActivity", "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("FocusActivity", "onPause: ");
        super.onPause();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.i("FocusActivity", "onWindowFocusChanged: " + hasFocus);

        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onAttachedToWindow() {
        Log.i("FocusActivity", "onAttachedToWindow");
        super.onAttachedToWindow();

        WindowId windowId = getWindow().getDecorView().getWindowId();
        windowId.registerFocusObserver(mObserver);
    }

    @Override
    public void onDetachedFromWindow() {
        Log.i("FocusActivity", "onDetachedFromWindow");
        super.onDetachedFromWindow();
        getWindow().getDecorView().getWindowId().unregisterFocusObserver(mObserver);
    }


    final WindowId.FocusObserver mObserver = new WindowId.FocusObserver() {

        @Override
        public void onFocusGained(WindowId token) {
            Log.i("FocusActivity", "onFocusGained");
            tv.setText("Gained focus");
        }

        @Override
        public void onFocusLost(WindowId token) {
            Log.i("FocusActivity", "onFocusLost");
            tv.setText("Lost focus");
        }
    };
}