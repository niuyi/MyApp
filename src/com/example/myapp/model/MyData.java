package com.example.myapp.model;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by niuyi on 2015/9/28.
 */
public class MyData implements Parcelable{

    public int value = -10;
    public String name = "init";


    public MyData(int value, String name) {
        this.value = value;
        this.name = name;
    }

    private MyData(Parcel in) {
        name = in.readString();
        value = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name + "aaa");
        dest.writeInt(value + 1);
    }

    public static final Parcelable.Creator<MyData> CREATOR
            = new Parcelable.Creator<MyData>() {
        public MyData createFromParcel(Parcel in) {
            return new MyData(in);
        }

        public MyData[] newArray(int size) {
            return new MyData[size];
        }
    };


}
