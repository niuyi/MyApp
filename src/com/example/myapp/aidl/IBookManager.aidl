package com.example.myapp.aidl;

import com.example.myapp.aidl.Book;
import com.example.myapp.aidl.IListener;

interface IBookManager{
    String getId();
    void addBook(in Book book);
    void addListener(IListener listener);
    void removeListener(IListener listener);
}