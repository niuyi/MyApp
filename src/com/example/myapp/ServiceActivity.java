package com.example.myapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import com.example.myapp.aidl.Book;
import com.example.myapp.aidl.IBookManager;
import com.example.myapp.aidl.IListener;

/**
 * Created by linniu on 2015/10/27.
 */
public class ServiceActivity extends Activity {
    private static final String TAG = "ServiceActivity";
    private ServiceConnection conn;
    private IBinder.DeathRecipient recipient;
    private IBookManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        Log.i(TAG, "main thread: " + Thread.currentThread().getId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(conn != null){
            Log.i(TAG, "onUnbind");
            unbindService(conn);
        }
    }

    public void onUnbind(View view){
        if(conn != null){
            Log.i(TAG, "onUnbind");
            unbindService(conn);
        }
    }

    public void onTest(View view){
        final Book b = new Book("mybook", 123);
        Intent in = new Intent();
        in.setClass(this, BookService.class);
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i(TAG, String.format("onServiceConnected!: pid(%d) tid(%d)", android.os.Process.myPid(), Thread.currentThread().getId()));

                manager = IBookManager.Stub.asInterface(service);
                try {
                    manager.addBook(b);
                    Log.i(TAG, "getid: " + manager.getId());

                    recipient = new IBinder.DeathRecipient() {
                        @Override
                        public void binderDied() {
                            Log.i(TAG, "died!!");
                            if(manager != null){
                                manager.asBinder().unlinkToDeath(recipient, 0);
                                manager = null;
                            }
                        }
                    };
                    service.linkToDeath(recipient, 0);

                    manager.addListener(listener);

                    SystemClock.sleep(500);

                    manager.removeListener(listener);


                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "onServiceDisconnected: " + name);
            }
        };
        bindService(in, conn, Context.BIND_AUTO_CREATE);
    }

    private IListener listener = new IListener.Stub(){


        @Override
        public void onUpdate(String str) throws RemoteException {
            Log.i(TAG, "onUpdate : " + str);
            Log.i(TAG, String.format("onUpdate!: pid(%d) tid(%d)", android.os.Process.myPid(), Thread.currentThread().getId()));
        }
    };
}