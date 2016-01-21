package com.example.myapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapp.widget.GridLayoutManager;
import com.example.myapp.widget.OnChildSelectedListener;
import com.example.myapp.widget.VerticalGridView;

import java.util.ArrayList;
import java.util.List;
//import com.example.myapp.widget.GridLayoutManager;

/**
 * Created by linniu on 2015/9/23.
 */
public class RListActivity extends Activity implements VerticalGridView.FocusSearchListener {
    private static final String TAG = "RListActivity";
    private Button button;
    private VerticalGridView list;
    private MyAdapter adapter;

    private Handler handler = new Handler();
    private int offset = 240;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_list_activity);

//        button = (Button)findViewById(R.id.test_btn);

        list = (VerticalGridView)findViewById(R.id.list_view);
        list.setFocusable(true);
//        list.setLayoutManager(new GridLayoutManager(this, 2));
//        list.setSelectedPosition(20);
        adapter = new MyAdapter();
        list.setAdapter(adapter);


        list.setSelectedPosition(1);

        list.setOnChildSelectedListener(new OnChildSelectedListener() {
            @Override
            public void onChildSelected(ViewGroup parent, View view, int position, long id) {
                Log.i(TAG, "onChildSelected: " + view.getClass() + " , pos: " + position + " id: " + id + " bottom: " + view.getBottom());

//                try {
//                    Log.i(TAG, "onChildSelected2: " + list.getChildAt(position + 1).getBottom()) ;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                if (position == 2) {
                    offset = offset - 1;
                    Log.i(TAG, "offset: " + offset); //240 - 230 -- 234
                    list.setItemAlignmentOffset(offset);
                } else {
                    list.setItemAlignmentOffset(0);
                }
            }
        });

//        list.setFocusScrollStrategy(VerticalGridView.FOCUS_SCROLL_ITEM);
        list.computeVerticalScrollOffset();

//        list.setFocusSearchListener(this);

//        list.requestFocus();

        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "getItemAlignmentOffset: " + list.getItemAlignmentOffset());
                    }
                },1000
        );
    }

    @Override
    public void onFocusSearch() {
        button.requestFocus();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        Button btn1;
        Button btn2;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv);
            btn1 = (Button)itemView.findViewById(R.id.btn_1);
            btn2 = (Button)itemView.findViewById(R.id.btn_2);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedPosition = list.getSelectedPosition();
                    adapter.setItem(selectedPosition, "test1 : " + selectedPosition);
                    adapter.notifyDataSetChanged();
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedPosition = list.getSelectedPosition();
                    adapter.setItem(selectedPosition, "test2: " + selectedPosition);
                    adapter.notifyDataSetChanged();

//                    list.requestLayout();
                }
            });
        }
    }

    class MyAdapter extends RecyclerView.Adapter{


        List<String> list = new ArrayList<String>();
        public MyAdapter() {
            for(int i = 0 ; i < 4 ; i++){
                list.add("Test: " + i);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(RListActivity.this);

            View view = layoutInflater.inflate(R.layout.list_item, null);
            view.setFocusable(false);
            view.setBackground(getResources().getDrawable(R.drawable.btn_normal));

            int h = 300;
            if(i == 100){
                h = 500;
            }
            GridLayoutManager.LayoutParams para = new GridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h);
            view.setLayoutParams(para);
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

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            MyViewHolder holder = (MyViewHolder)viewHolder;
            holder.tv.setText(list.get(i));
        }

        @Override
        public int getItemViewType(int position) {
            if(position >= 2){
                return 100;
            }

            return 1;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void setItem(int index, String val){
            list.set(index, val);
        }
    }
}