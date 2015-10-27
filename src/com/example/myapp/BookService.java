package com.example.myapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.example.myapp.aidl.IBookManager;
import com.example.myapp.model.MyBookManager;

/**
 * Created by linniu on 2015/10/27.
 */
public class BookService extends Service {

    private static final String TAG = "BookService";
    public IBookManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
        manager = new MyBookManager();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, String.format("onBind: pid(%d) tid(%d)", android.os.Process.myPid(), Thread.currentThread().getId()));
        return manager.asBinder();
    }
}
