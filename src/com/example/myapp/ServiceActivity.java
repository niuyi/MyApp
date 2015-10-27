package com.example.myapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import com.example.myapp.aidl.Book;
import com.example.myapp.aidl.IBookManager;

/**
 * Created by linniu on 2015/10/27.
 */
public class ServiceActivity extends Activity {
    private static final String TAG = "ServiceActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        Log.i(TAG, "main thread: " + Thread.currentThread().getId());
    }

    public void onTest(View view){
        final Book b = new Book("mybook", 123);
        Intent in = new Intent();
        in.setClass(this, BookService.class);
        bindService(in, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i(TAG, String.format("onServiceConnected: pid(%d) tid(%d)", android.os.Process.myPid() ,  Thread.currentThread().getId()));


                IBookManager manager = IBookManager.Stub.asInterface(service);
                try {
                    manager.addBook(b);
                    Log.i(TAG, "getid: " + manager.getId());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }
}