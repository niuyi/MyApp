package com.example.myapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class MyActivity extends Activity {

    private static final String TAG = "MyActivity";

    private String[] activities = new String[]{"TestActivityActivity", "MyWheelActivity", "WebViewActivity", "SlideActivity", "PagerActivity", "FragmentTestActivity","CoordsTestActivity", "FocusTestActivity", "LitepalActivity", "ServiceActivity", "AActivity", "HtmlActivity", "RListActivity", "ParcelableActivity", "ShakeActivity",
            "TextPushActivity",
            "TestViewActivity",
            "FocusActivity",
            "WindowActivity",
            "ScrollActivity",
            "TestBallActivity",
            "LayoutActivity",
            "BitmapActivity",
            "FontActivity",
            "ArcsActivity",
            "ClipActivity",
    "DensityActivity", "DrawableActivity","TestDrawableActivity", "MyPaintActivity", "MeasureTextActivity", "TextAlignActivity", "Scroll2Activity"};
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


        Log.i(TAG, "CODENAME: " + Build.VERSION.CODENAME);
        Log.i(TAG, "BOARD: " + Build.BOARD);
        Log.i(TAG, "DEVICE: " + Build.DEVICE);
        Log.i(TAG, "DISPLAY: " + Build.DISPLAY);
        Log.i(TAG, "FINGERPRINT: " + Build.FINGERPRINT);
        Log.i(TAG, "HARDWARE: " + Build.HARDWARE);
        Log.i(TAG, "MANUFACTURER: " + Build.MANUFACTURER);
        Log.i(TAG, "MODEL: " + Build.MODEL);
        Log.i(TAG, "PRODUCT: " + Build.PRODUCT);
        Log.i(TAG, "SERIAL: " + Build.SERIAL);
        Log.i(TAG, "TYPE: " + Build.TYPE);
        Log.i(TAG, "USER: " + Build.USER);

        Log.i(TAG, "densityDpi: " + densityDpi);
        Log.i(TAG, "density: " + dm.density);

        Log.i(TAG, "heightPixels: " + dm.heightPixels);
        Log.i(TAG, "widthPixels: " + dm.widthPixels);
        Log.i(TAG, "scaledDensity: " + dm.scaledDensity);
        Log.i(TAG, "xdpi: " + dm.xdpi);
        Log.i(TAG, "ydpi: " + dm.ydpi);

        Configuration config = getResources().getConfiguration();

        Log.i(TAG, "screenHeightDp: " + config.screenHeightDp);
        Log.i(TAG, "screenWidthDp: " + config.screenWidthDp);



        ViewGroup root = (ViewGroup)findViewById(R.id.root);

        for(final String s : activities){
            Button btn = new Button(this);
            btn.setText(s.substring(0, s.indexOf("Activity")));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent();
                    in.setClass(MyActivity.this, ShakeActivity.class);
                    in.setClassName(MyActivity.this, "com.example.myapp." + s);

                    List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(in, 0);

                    Log.i(TAG, "resolveInfos: " + resolveInfos.size());

                    if(resolveInfos.size() > 0){
                        Log.i(TAG, "resolveInfos.get(0).activityInfo:" + resolveInfos.get(0).activityInfo);
                    }

                    startActivity(in);
                }
            });
            root.addView(btn);
        }

//        Button btn = new Button(this);
//        btn.setText("shake");
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent();
//                in.setClass(MyActivity.this, ShakeActivity.class);
//                startActivity(in);
//            }
//        });
//        root.addView(btn);
//
//        btn = new Button(this);
//        btn.setText("push");
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent();
//                in.setClass(MyActivity.this, TextPushActivity.class);
//                startActivity(in);
//            }
//        });
//        root.addView(btn);
    }

    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode, Bundle options) {
        super.startActivityFromFragment(fragment, intent, requestCode, options);
    }
}
