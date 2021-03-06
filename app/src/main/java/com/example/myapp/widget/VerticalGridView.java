/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.example.myapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.example.myapp.R;

/**
 * A view that shows items in a vertically scrolling list. The items come from
 * the {@link Adapter} associated with this view.
 */
public class VerticalGridView extends BaseGridView {

    private static final String TAG = "VerticalGridView";

    public VerticalGridView(Context context) {
        this(context, null);
    }

    public VerticalGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        initAttributes(context, attrs);
    }

    @Override
    public View focusSearch(View focused, int direction) {
        View view = super.focusSearch(focused, direction);

        android.util.Log.i(TAG, "focusSearch: " + view + " dir: " + direction);

        if(mFocusSearchListener != null){
            int pos = getSelectedPosition();
            if(pos < mColumnWidth && direction == View.FOCUS_UP){
                mFocusSearchListener.onFocusSearch();
                return null;
            }
        }

        return view;
    }

    protected void initAttributes(Context context, AttributeSet attrs) {
//        initBaseGridViewAttributes(context, attrs);
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.lbVerticalGridView);
//        setColumnWidth(a);
//        setNumColumns(a.getInt(R.styleable.lbVerticalGridView_numberOfColumns, 1));
//        a.recycle();
    }

    void setColumnWidth(TypedArray array) {
//        TypedValue typedValue = array.peekValue(R.styleable.lbVerticalGridView_columnWidth);
//        int size;
//        if (typedValue != null && typedValue.type == TypedValue.TYPE_DIMENSION) {
//            size = array.getDimensionPixelSize(R.styleable.lbVerticalGridView_columnWidth, 0);
//        } else {
//            size = array.getInt(R.styleable.lbVerticalGridView_columnWidth, 0);
//        }
//        setColumnWidth(size);
    }

    /**
     * Set the number of columns.  Defaults to one.
     */
    public void setNumColumns(int numColumns) {
        mLayoutManager.setNumRows(numColumns);
        requestLayout();
    }

    /**
     * Set the column width.
     *
     * @param width May be WRAP_CONTENT, or a size in pixels. If zero,
     * column width will be fixed based on number of columns and view width.
     */

    private int mColumnWidth = 1;

    public void setColumnWidth(int width) {
        mColumnWidth = width;
        mLayoutManager.setRowHeight(width);
        requestLayout();
    }

    private FocusSearchListener mFocusSearchListener;

    public void setFocusSearchListener(FocusSearchListener listener){
        mFocusSearchListener = listener;
    }

    public interface FocusSearchListener{
        public void onFocusSearch();
    }
}
