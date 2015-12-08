package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

/**
 * Created by linniu on 2015/12/7.
 */
public class FocusTestActivity extends Activity {

    private View layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.focus_test_activity);
        layout = findViewById(R.id.my_linear_layout);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN){
            android.util.Log.i("FocusTestActivity", "move down");
            layout.requestFocus();
            return true;
        }

        return super.dispatchKeyEvent(event);
    }
}