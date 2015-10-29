package com.example.myapp.model;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import com.example.myapp.aidl.Book;
import com.example.myapp.aidl.IBookManager;
import com.example.myapp.aidl.IListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by linniu on 2015/10/27.
 */
public class MyBookManager extends IBookManager.Stub {

    private static final String TAG = "MyBookManager";
    private RemoteCallbackList<IListener> listeners = new RemoteCallbackList<IListener>();

    @Override
    public String getId() throws RemoteException {
        return "myid";
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        Log.i(TAG, String.format("addBook: pid(%d) tid(%d)", android.os.Process.myPid() ,  Thread.currentThread().getId()));
        Log.i(TAG, "book: " + book.name);
    }

    @Override
    public void addListener(IListener listener) throws RemoteException {
        listeners.register(listener);
        listener.onUpdate("this is update str");
    }

    @Override
    public void removeListener(IListener listener) throws RemoteException {
        int N = listeners.beginBroadcast();
       Log.i(TAG, "before remove:" + N);
        listeners.finishBroadcast();

        listeners.unregister(listener);

        N = listeners.beginBroadcast();
        Log.i(TAG, "before remove:" + N);
        listeners.finishBroadcast();

    }
}
