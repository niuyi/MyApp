package com.example.myapp.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by linniu on 2015/12/7.
 */
public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public View findFocus() {
        Log.i("FocusTestActivity", "findFocus: " + getTag() + " ,start");
        View view = super.findFocus();
        Log.i("FocusTestActivity", "findFocus: " + getTag() + " ,result: " + view.getTag());
        Exception e = new Exception("MyLinearLayout findFocus");
        e.printStackTrace();
        return view;
    }

    @Override
    public View focusSearch(View focused, int direction) {
        Log.i("FocusTestActivity", "focusSearch: " + getTag() + " ,start");
        View view = super.focusSearch(focused, direction);
        Log.i("FocusTestActivity", "focusSearch: " + getTag() + ", focused: " + getViewTag(focused) + " ,result: " + view.getTag());
        return view;
    }

    private String getViewTag(View focused) {
        if(focused == null)
            return "null";

        return (String)focused.getTag();
    }

    private Object getTag(View focused) {
        return focused.getTag();
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        Log.i("FocusTestActivity", "requestChildFocus: " + getTag() + ", child: " + child.getTag() + " ,focused: " + focused.getTag());
        super.requestChildFocus(child, focused);
    }

    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
//        Log.i("FocusTestActivity", "onRequestFocusInDescendants: " + getTag() + ", previouslyFocusedRect: " + previouslyFocusedRect + " ,start");
//        boolean result = super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
//        Log.i("FocusTestActivity", "onRequestFocusInDescendants: " + getTag() + ", previouslyFocusedRect: " + previouslyFocusedRect + " ,result: " + result);
//        return result;
        getChildAt(1).requestFocus();
        return true;
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        Log.i("FocusTestActivity", "requestFocus: " + getTag() + " ,start");
        boolean result = super.requestFocus(direction, previouslyFocusedRect);
        Log.i("FocusTestActivity", "requestFocus: " + getTag() + ", previouslyFocusedRect: " + previouslyFocusedRect + " ,result: " + result);
        return result;
    }

    @Override
    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        Log.i("FocusTestActivity", "addFocusables: " + getTag());
        super.addFocusables(views, direction, focusableMode);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        Log.i("FocusTestActivity", "onFocusChanged: " + getTag() + " ,focused: " + focused);
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }
}
