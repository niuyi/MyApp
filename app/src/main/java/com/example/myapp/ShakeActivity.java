package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

/**
 * Created by linniu on 2015/7/5.
 */
public class ShakeActivity extends Activity {

    private EditText edit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake);
        edit = (EditText)findViewById(R.id.edit);
    }

    public void doShake(View view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
        animation.setDuration(500);
        edit.startAnimation(animation);
    }
}