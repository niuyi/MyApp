package com.example.myapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuyi on 2015/12/23.
 */
public class MyWheelView extends LinearLayout {

    private MyWheelViewAdapter mAdapter = new MyWheelViewAdapter();

    private int mCurrentIndex = 0;

    public MyWheelView(Context context) {
        super(context);
        init(context);
    }

    public MyWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyWheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LinearLayout.LayoutParams paras = new LinearLayout.LayoutParams(400, 100);
        paras.gravity = Gravity.CENTER;
        for(int i = 0 ; i < 5 ; i ++){
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);

            if(i == 2){
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 42);
                textView.setFocusable(true);
                textView.setBackgroundResource(R.drawable.btn_normal);
                textView.setOnKeyListener(new OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.getAction() == KeyEvent.ACTION_DOWN){
                            mCurrentIndex ++;
                            if(mCurrentIndex > mAdapter.getItemsCount() - 1){
                                mCurrentIndex = 0;
                            }

                            initView();

                        }else if(keyCode == KeyEvent.KEYCODE_DPAD_UP && event.getAction() == KeyEvent.ACTION_DOWN){
                            mCurrentIndex --;

                            if(mCurrentIndex < 0){
                                mCurrentIndex = mAdapter.getItemsCount() - 1;
                            }

                            initView();
                        }
                        return false;
                    }
                });
            }else{
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 36);
                textView.setAlpha((float) 0.7);
                textView.setFocusable(false);
            }

            addView(textView, paras);
        }

        initView();
    }

    private void initView(){
        String[] texts = getTexts(mCurrentIndex);

        for(int i = 0 ; i < texts.length ; i++){
            ((TextView)getChildAt(i)).setText(texts[i]);
        }
    }

    private String[] getTexts(int index){
        String[] resutls = new String[5];

        int size = mAdapter.getItemsCount();
        resutls[0] = mAdapter.getItem(getIndex(index - 2, size));
        resutls[1] = mAdapter.getItem(getIndex(index - 1, size));
        resutls[2] = mAdapter.getItem(index);
        resutls[3] = mAdapter.getItem(getIndex(index + 1, size));
        resutls[4] = mAdapter.getItem(getIndex(index + 2, size));

        return resutls;
    }

    private int getIndex(int index, int size){
        if(index < 0){
            return size + index;
        }else if(index > size - 1){
            return index - size;
        }

        return index;
    }

    private int getIndex(int index, int diff, int size){
        int result = index - diff;
        if(result < 0){
            result = size + result;
        }

        android.util.Log.i("MyWheelView", "get index : " + result);

        return result;
    }

    class MyWheelViewAdapter implements WheelAdapter{

        private List<String> list = new ArrayList<String>();

        public MyWheelViewAdapter() {
            for(int i = 0 ; i < 10 ; i ++){
                list.add(String.valueOf(i));
            }
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        public String getItem(int index) {
            return list.get(index);
        }

        @Override
        public int getMaximumLength() {
            return 0;
        }
    }

    public interface WheelAdapter {
        /**
         * Gets items count
         * @return the count of wheel items
         */
        int getItemsCount();

        /**
         * Gets a wheel item by index.
         *
         * @param index the item index
         * @return the wheel item text or null
         */
        String getItem(int index);

        /**
         * Gets maximum item length. It is used to determine the wheel width.
         * If -1 is returned there will be used the default wheel width.
         *
         * @return the maximum item length or -1
         */
        int getMaximumLength();
    }
}
