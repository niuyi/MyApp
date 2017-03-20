package com.example.myapp.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by linniu on 2015/10/27.
 */
public class Book implements Parcelable {

    public String name;
    public int id;

    public Book(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Book(Parcel source) {
        id = source.readInt();
        name = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    public static final Parcelable.Creator<Book>  CREATOR = new Parcelable.Creator<Book>(){

        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
