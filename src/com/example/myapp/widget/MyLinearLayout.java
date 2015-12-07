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
        View view = super.findFocus();
        Log.i("FocusTestActivity", "findFocus: " + getTag() + " ,result: " + view.getTag());
        return view;
    }

    @Override
    public View focusSearch(View focused, int direction) {
        View view = super.focusSearch(focused, direction);
        Log.i("FocusTestActivity", "focusSearch: " + getTag() + ", focused: " + focused.getTag() + " ,result: " + view.getTag());
        return view;
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        Log.i("FocusTestActivity", "requestChildFocus: " + getTag() + ", child: " + child.getTag() + " ,focused: " + focused.getTag());
        super.requestChildFocus(child, focused);
    }

    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        boolean result = super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
        Log.i("FocusTestActivity", "onRequestFocusInDescendants: " + getTag() + ", previouslyFocusedRect: " + previouslyFocusedRect + " ,result: " + result);
        return result;
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
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
