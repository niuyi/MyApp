package com.example.myapp.aidl;

import com.example.myapp.aidl.Book;

interface IBookManager{
    String getId();
    void addBook(in Book book);
}