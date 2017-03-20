package com.example.myapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

/**
 * Created by linniu on 2015/7/19.
 */
public class WindowActivity extends Activity implements AbsListView.OnScrollListener {

    private TextView title;
    private WindowManager systemService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_activity);

        ListView list = (ListView)findViewById(R.id.list);

        list.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings
        ));

        list.setOnScrollListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent();
                in.setClass(WindowActivity.this, ShakeActivity.class);
                startActivity(in);
            }
        });

        title = new TextView(this);
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50);

        systemService = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams para = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        systemService.addView(title, para);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        String s = Cheeses.sCheeseStrings[firstVisibleItem].substring(0, 1);
        if(title != null){
            title.setText(s);
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        systemService.removeView(title);
//    }
}