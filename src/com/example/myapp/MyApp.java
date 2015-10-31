package com.example.myapp;

import android.app.Application;
import org.litepal.LitePalApplication;

/**
 * Created by linniu on 2015/10/31.
 */
public class MyApp extends Application {

    public static MyApp i = null;

    public MyApp(){
        i = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);
    }
}
