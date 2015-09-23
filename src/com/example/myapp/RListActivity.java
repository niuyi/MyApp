package com.example.myapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v17.leanback.widget.VerticalGridView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//import com.example.myapp.widget.GridLayoutManager;

/**
 * Created by linniu on 2015/9/23.
 */
public class RListActivity extends Activity {
    private static final String TAG = "RListActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_list_activity);

        VerticalGridView list = (VerticalGridView)findViewById(R.id.list_view);
        list.setFocusable(true);
//        list.setSelectedPosition(20);
        list.setAdapter(new MyAdapter());
//        list.requestFocus();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            TextView tv = new TextView(RListActivity.this);
            tv.setFocusable(true);

            tv.setBackground(getResources().getDrawable(R.drawable.btn_normal));

            GridLayoutManager.LayoutParams para = new GridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
            tv.setLayoutParams(para);

//            tv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    Log.i(TAG, "onFocusChange: " + hasFocus);
//                    if (hasFocus) {
//                        v.setBackgroundColor(Color.BLUE);
//                    } else {
//                        v.setBackgroundColor(Color.BLACK);
//                    }
//                }
//            });

//            tv.setClickable(true);
//            tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    v.setBackgroundColor(Color.BLUE);
//                }
//            });

            return new MyViewHolder(tv);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            MyViewHolder holder = (MyViewHolder)viewHolder;
            ((TextView)holder.itemView).setText("test: " + i);
        }

        @Override
        public int getItemCount() {
            return 50;
        }
    }
}