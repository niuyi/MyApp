package com.example.myapp.model;

import android.os.RemoteException;
import android.util.Log;
import com.example.myapp.aidl.Book;
import com.example.myapp.aidl.IBookManager;

/**
 * Created by linniu on 2015/10/27.
 */
public class MyBookManager extends IBookManager.Stub {

    private static final String TAG = "MyBookManager";

    @Override
    public String getId() throws RemoteException {
        return "myid";
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        Log.i(TAG, String.format("addBook: pid(%d) tid(%d)", android.os.Process.myPid() ,  Thread.currentThread().getId()));
        Log.i(TAG, "book: " + book.name);
    }
}
