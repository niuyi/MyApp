package com.example.myapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by linniu on 2015/9/1.
 */
public class DensityActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        setContentView(layout);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo120dpi);
        View view = new View(this);
        BitmapDrawable d = new BitmapDrawable(getResources(), bitmap);

        Log.i("DensityActivity", "w: " + d.getIntrinsicWidth() + " , h: " + d.getIntrinsicHeight());
        view.setLayoutParams(new LinearLayout.LayoutParams(d.getIntrinsicWidth(),
                d.getIntrinsicHeight()));
        view.setBackground(d);
        layout.addView(view);

//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inScaled = false;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo240dpi);
        view = new View(this);
        d = new BitmapDrawable(getResources(), bitmap);

        Log.i("DensityActivity", "w: " + d.getIntrinsicWidth() + " , h: " + d.getIntrinsicHeight());
        view.setLayoutParams(new LinearLayout.LayoutParams(d.getIntrinsicWidth(),
                d.getIntrinsicHeight()));
        view.setBackground(d);
        layout.addView(view);

        DisplayMetrics metrics = new DisplayMetrics();

        Display display = getWindowManager().getDefaultDisplay();

        display.getMetrics(metrics);

        Log.i("DensityActivity", metrics.density + " dpi: " + metrics.densityDpi);
    }
}