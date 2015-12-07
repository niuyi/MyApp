package com.example.myapp.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by linniu on 2015/12/7.
 */
public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public View findFocus() {
        View view = super.findFocus();
        Log.i("FocusTestActivity", "findFocus: " + getTag() + " ,result: " + view.getTag());
        Exception exception = new Exception("findFocus");
        exception.printStackTrace();
        return view;
    }

    @Override
    public View focusSearch(int direction) {
        View view = super.focusSearch(direction);
        Log.i("FocusTestActivity", "focusSearch: " + getTag() + " ,result: " + view.getTag());
        return view;
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
