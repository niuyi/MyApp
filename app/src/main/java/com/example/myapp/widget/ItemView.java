package com.example.myapp.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.example.myapp.R;
import org.apache.commons.logging.Log;

/**
 * Created by niuyi on 2015/9/24.
 */
public class ItemView extends LinearLayout implements View.OnFocusChangeListener {

    private static final String TAG = "ItemView";
    private Button btn1;
    private Button btn2;
    private View focus;

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public View focusSearch(View focused, int direction) {
        android.util.Log.i(TAG, "focusSearch" + direction);
        return super.focusSearch(focused, direction);
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        android.util.Log.i(TAG, "requestChildFocus child: " + child + " ,focused: " + focused);
        super.requestChildFocus(child, focused);
    }


    @Override
    protected void onFinishInflate() {
        btn1 = (Button)findViewById(R.id.btn_1);
        btn2 = (Button)findViewById(R.id.btn_2);

        btn1.setOnFocusChangeListener(this);
        btn2.setOnFocusChangeListener(this);
    }

    @Override
    public void clearChildFocus(View child) {
        android.util.Log.i(TAG, "clearChildFocus" + child);
        super.clearChildFocus(child);
    }

    @Override
    public View getFocusedChild() {
        android.util.Log.i(TAG, "getFocusedChild");
        if(focus != null){
            return focus;
        }

        return super.getFocusedChild();
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        android.util.Log.i(TAG, "prop: " + getDescendantFocusability());
        android.util.Log.i(TAG, "requestFocus: " + direction + " rect: " + previouslyFocusedRect);
//        boolean b = super.requestFocus(direction, previouslyFocusedRect);
//        android.util.Log.i(TAG, "requestFocus " + b);
//        return b;

//        if(previouslyFocusedRect != null && previouslyFocusedRect.left == 1000){
//            android.util.Log.i(TAG, "find 2");
//            btn2.requestFocus();
//            return true;
//        }

        return super.requestFocus(direction, previouslyFocusedRect);
    }

    @Override
    public void setShowDividers(int showDividers) {
        super.setShowDividers(showDividers);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        android.util.Log.i(TAG, "onFocusChanged");
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    public View findFocus() {
        android.util.Log.i(TAG, "findFocus");
        View v = super.findFocus();
        android.util.Log.i(TAG, "findFocus: " + v);
        return v;
    }

    @Override
    public void clearFocus() {
        android.util.Log.i(TAG, "clearFocus");
        super.clearFocus();
    }

    @Override
    public void focusableViewAvailable(View v) {
        android.util.Log.i(TAG, "focusableViewAvailable " + v.getClass());
        super.focusableViewAvailable(v);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        android.util.Log.i(TAG, "onFocusChange: " + hasFocus + " v: " + v);
        if(hasFocus){
            focus = v;
        }else{
            focus = null;
        }

    }

    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }
}
