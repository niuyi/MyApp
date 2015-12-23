package com.example.myapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
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

    private static final String TAG = "MyWheelView";
    private WheelAdapter mAdapter;

    private int mCount = 5;
    private int mMid = 2;
    private int mCurrentIndex = 0;

    public MyWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyWheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyWheelView);
        mCount = typedArray.getInteger(R.styleable.MyWheelView_itemCount, 5);
        mMid = mCount/2;

        int itemHeight = typedArray.getDimensionPixelSize(R.styleable.MyWheelView_itemHeight, 100);
        int itemWidth = typedArray.getDimensionPixelSize(R.styleable.MyWheelView_itemWidth, 400);
        int size1 = typedArray.getDimensionPixelSize(R.styleable.MyWheelView_itemTextSize1, 36);
        int size2 = typedArray.getDimensionPixelSize(R.styleable.MyWheelView_itemTextSize2, 42);
        int resourceId = typedArray.getResourceId(R.styleable.MyWheelView_focusButtonDrawable, R.drawable.btn_normal2);

        typedArray.recycle();

        LinearLayout.LayoutParams paras = new LinearLayout.LayoutParams(itemWidth, itemHeight);
        paras.gravity = Gravity.CENTER;
        for(int i = 0 ; i < mCount ; i ++){
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textView.setMarqueeRepeatLimit(-1);

            if(i == mMid){
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size2);
                textView.setFocusable(true);
                textView.setBackgroundResource(resourceId);
                textView.setOnKeyListener(new OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.getAction() == KeyEvent.ACTION_DOWN){
                            int newIndex = getNewIndex(mCurrentIndex + 1);
                            android.util.Log.i(TAG, "onKey: newIndex: " + newIndex + " mCurrentIndex: " + mCurrentIndex);
                            if(newIndex != mCurrentIndex){
                                mCurrentIndex = newIndex;
                                initView();
                                if(mListener != null){
                                    mListener.onSelectedChanged(mCurrentIndex);
                                }
                            }
                        }else if(keyCode == KeyEvent.KEYCODE_DPAD_UP && event.getAction() == KeyEvent.ACTION_DOWN){
                            int newIndex = getNewIndex(mCurrentIndex - 1);
                            android.util.Log.i(TAG, "onKey: newIndex: " + newIndex + " mCurrentIndex: " + mCurrentIndex);
                            if(newIndex != mCurrentIndex){
                                mCurrentIndex = newIndex;
                                initView();
                                if(mListener != null){
                                    mListener.onSelectedChanged(mCurrentIndex);
                                }
                            }
                        }
                        return false;
                    }
                });
            }else{
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size1);
                textView.setAlpha((float) 0.7);
                textView.setFocusable(false);
            }

            addView(textView, paras);
        }
    }

    public void setAdapter(WheelAdapter adapter){
        mAdapter = adapter;
        if(mAdapter.getItemsCount() <= mCount){
            mCurrentIndex = mAdapter.getItemsCount()/2;
        }else{
            mCurrentIndex = 0;
        }
        initView();
    }

    private int getNewIndex(int index){
        boolean needLoop = mAdapter.getItemsCount() > mCount;
        int ret = index;
        if(index > mAdapter.getItemsCount() - 1){
            if(needLoop){
                ret = index;
            }else{
                ret = index - 1;
            }
        }

        if(index < 0){
            if(needLoop){
                ret = mAdapter.getItemsCount() - 1;
            }else{
                ret = 0;
            }
        }

        android.util.Log.i(TAG, "getNewIndex: " + index + " ret: " + ret);
        return ret;
    }

    private void initView(){
        String[] texts = getTexts(mCurrentIndex);

        for(int i = 0 ; i < texts.length ; i++){
            ((TextView)getChildAt(i)).setText(texts[i]);
        }
    }

    private String[] getTexts(int index){
        String[] results = new String[mCount];

        int size = mAdapter.getItemsCount();

        if(size <= mCount){
            int newMid = index;
            for(int i = 0 ; i < mCount ; i ++){
                results[i] = mAdapter.getItem(i - (mMid - newMid));
            }
        }else{
            for(int i = 0 ; i < mCount ; i ++){

                int diff = mMid - i;
                if(Math.abs(diff) >= size){
                    results[i] = mAdapter.getItem(diff);
                }else{
                    results[i] = mAdapter.getItem(getIndex(index - diff, size));
                }
            }
        }


//        for(int i = 0 ; i < mMid ; i ++){
//            results[i] = mAdapter.getItem(getIndex(index - (mMid - i), size));
//        }
//
//        results[mMid] = mAdapter.getItem(index);
//
//        for(int i = mMid + 1 ; i < mCount ; i ++){
//            results[3] = mAdapter.getItem(getIndex(index + (i - mMid), size));
//        }
//
//        results[0] = mAdapter.getItem(getIndex(index - 2, size));
//        results[1] = mAdapter.getItem(getIndex(index - 1, size));
//        results[2] = mAdapter.getItem(index);
//        results[3] = mAdapter.getItem(getIndex(index + 1, size));
//        results[4] = mAdapter.getItem(getIndex(index + 2, size));

        return results;
    }

    private int getIndex(int index, int size){
        if(index < 0){
            return size + index;
        }else if(index > size - 1){
            return index - size;
        }

        return index;
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

    @Override
    public void setOnClickListener(OnClickListener l) {
        getChildAt(mMid).setOnClickListener(l);
    }

    private MyWheelViewChangeSelectedListener mListener;

    public void setListener(MyWheelViewChangeSelectedListener l) {
        mListener = l;
    }

    public interface MyWheelViewChangeSelectedListener{
        public void onSelectedChanged(int pos);
    }
}
