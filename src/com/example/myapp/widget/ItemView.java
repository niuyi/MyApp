package com.example.myapp.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by niuyi on 2015/9/24.
 */
public class ItemView extends LinearLayout {

    private static final String TAG = "ItemView";

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
    public void clearChildFocus(View child) {
        android.util.Log.i(TAG, "clearChildFocus" + child);
        super.clearChildFocus(child);
    }

    @Override
    public View getFocusedChild() {
        android.util.Log.i(TAG, "getFocusedChild");
        return super.getFocusedChild();
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        boolean b = super.requestFocus(direction, previouslyFocusedRect);
        android.util.Log.i(TAG, b + " requestFocus: " + direction + " rect: " + previouslyFocusedRect);

        return b;
    }


}
