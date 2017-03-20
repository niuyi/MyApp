package com.example.myapp;

import android.animation.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by linniu on 2015/8/24.
 */
public class LayoutActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity);

        final ViewGroup root = (ViewGroup)findViewById(R.id.root);

        Button addButton = new Button(this);
        addButton.setText("add");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = root.getChildCount();
                for (int i = 0; i < size; i++) {
                    root.getChildAt(i).setVisibility(View.VISIBLE);
                }
            }
        });

        root.addView(addButton);

        for(int i = 0 ; i < 5; i++){
            Button newButton = new Button(this);
            newButton.setText(String.valueOf(i));
            root.addView(newButton);
            LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
            newButton.setLayoutParams(para);
            newButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    v.setVisibility(View.GONE);
                }
            });
        }

        LayoutTransition lt = new LayoutTransition();

//        lt.setStagger(LayoutTransition.CHANGE_APPEARING, 30);
//        lt.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);

        PropertyValuesHolder pvhLeft =
                PropertyValuesHolder.ofInt("left", 0, 10);
        PropertyValuesHolder pvhTop =
                PropertyValuesHolder.ofInt("top", 0, 10);
        PropertyValuesHolder pvhRight =
                PropertyValuesHolder.ofInt("right", 0, 10);
        PropertyValuesHolder pvhBottom =
                PropertyValuesHolder.ofInt("bottom", 0, 10);
        PropertyValuesHolder pvhScaleX =
                PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY =
                PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);
//        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX, pvhScaleY).
//                setDuration(lt.getDuration(LayoutTransition.CHANGE_APPEARING));

        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(this,
                pvhScaleX, pvhScaleY).
                setDuration(lt.getDuration(LayoutTransition.CHANGE_APPEARING));


        lt.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
        changeIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });

        ObjectAnimator show = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f);
        show.setDuration(lt.getDuration(LayoutTransition.APPEARING));
        show.setInterpolator(new BounceInterpolator());
        show.addListener(new AnimatorListenerAdapter() {


            @Override
            public void onAnimationEnd(Animator animation) {
                View v = (View) ((ObjectAnimator) animation).getTarget();
                v.setRotationY(0f);
            }
        });
        lt.setAnimator(LayoutTransition.APPEARING, show);


        ObjectAnimator hide = ObjectAnimator.ofFloat(null, "rotationX", 0f, 90f);
        hide.setDuration(lt.getDuration(LayoutTransition.DISAPPEARING));
        hide.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View v = (View) ((ObjectAnimator) animation).getTarget();
                v.setRotationX(0f);
            }
        });

        lt.setAnimator(LayoutTransition.DISAPPEARING, hide);

        root.setLayoutTransition(lt);
    }
}