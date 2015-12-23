package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.example.myapp.widget.MyWheelView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuyi on 2015/12/23.
 */
public class MyWheelActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wheel_activity);

        MyWheelView view = (MyWheelView)findViewById(R.id.wheel_view);
        view.setAdapter(new MyWheelViewAdapter(2));

        MyWheelView view2 = (MyWheelView)findViewById(R.id.wheel_view2);
        view2.setAdapter(new MyWheelViewAdapter(3));

        MyWheelView view3 = (MyWheelView)findViewById(R.id.wheel_view3);
        view3.setAdapter(new MyWheelViewAdapter(4));

        MyWheelView view4 = (MyWheelView)findViewById(R.id.wheel_view4);
        view4.setAdapter(new MyWheelViewAdapter(5));

        MyWheelView view5 = (MyWheelView)findViewById(R.id.wheel_view5);
        view5.setAdapter(new MyWheelViewAdapter(10));

        MyWheelView view6 = (MyWheelView)findViewById(R.id.wheel_view6);
        view6.setAdapter(new MyWheelViewAdapter(1));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.util.Log.i("MyWheelView", "on click!");
            }
        });

        view.setListener(new MyWheelView.MyWheelViewChangeSelectedListener() {
            @Override
            public void onSelectedChanged(int pos) {
                android.util.Log.i("MyWheelView", "onSelectedChanged: " + pos);
                if(pos == 2){
                    view2.setAdapter(new MyWheelViewAdapter(3));
                }
            }
        });

    }

    class MyWheelViewAdapter implements MyWheelView.WheelAdapter {

        private List<String> list = new ArrayList<String>();

        public MyWheelViewAdapter(int count) {
            for(int i = 0 ; i < count ; i ++){
                list.add(String.valueOf(i));
            }
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        public String getItem(int index) {
            String s = "";
            if(index < 0 || index > list.size() - 1){
                s = "";
            }else{
                s = list.get(index);
            }


            return s;
        }

        @Override
        public int getMaximumLength() {
            return 0;
        }
    }
}