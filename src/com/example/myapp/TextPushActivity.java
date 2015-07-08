package com.example.myapp;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import java.util.zip.Inflater;

/**
 * Created by linniu on 2015/7/5.
 */
public class TextPushActivity extends Activity implements AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {

    private TextSwitcher switcher;
    private TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.push_text);

        switcher = (TextSwitcher)findViewById(R.id.switcher);
        switcher.setFactory(this);

        switcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

        tv = (TextView)findViewById(R.id.tv);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.text_item, new String[]{"1", "2", "3", "4"}));
        spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(position){
            case 0:
                switcher.setText("0000000");
                break;
            case 1:
                switcher.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_up_in));
                switcher.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_up_out));
                switcher.setText("111111");
                break;
            case 2:
                switcher.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.hyperspace_in));
                switcher.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.hyperspace_out));
                switcher.setText("2222222");
                break;
            case 3:
                switcher.setText("4444444");
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public View makeView() {
        return getLayoutInflater().inflate(R.layout.text_item, null);
    }

    public void doRun(View view){
        View parent = (View)tv.getParent();
        int diff = parent.getWidth() - tv.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(tv, "translationX", 0, diff);
        translationX.setDuration(700);
        translationX.setRepeatMode(Animation.RESTART);
        translationX.setRepeatCount(Animation.INFINITE);
        translationX.setInterpolator(new AccelerateInterpolator());

        translationX.start();
    }
}