package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

public class MyActivity extends Activity {

    private static final String TAG = "MyActivity";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int densityDpi = dm.densityDpi;
        Log.i(TAG, "densityDpi: " + densityDpi);
        Log.i(TAG, "density: " + dm.density);
    }
}
