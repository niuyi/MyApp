package com.example.myapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by linniu on 2015/7/8.
 */
public class MyTextView extends View {

    private Paint p = new Paint();
    private String mText = "abcdefgIL";

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        p.setAntiAlias(true);
        p.setTextSize(100);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        Log.i("MyTextView", String.format("mode:%d size:%d", mode, size));

        if(mode == MeasureSpec.EXACTLY){
            Log.i("MyTextView", "EXACTLY");
        }else if(mode == MeasureSpec.AT_MOST){
            Log.i("MyTextView", "AT_MOST");
        }else if(mode == MeasureSpec.UNSPECIFIED){
            Log.i("MyTextView", "UNSPECIFIED");
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        float tw = p.measureText(mText);

        int h = getHeight();
        float th = -p.ascent() + p.descent();
        Log.i("MyTextView", String.format("measureText:%f p.ascent():%f p.descent():%f", tw, p.ascent(), p.descent()));
        Log.i("MyTextView", String.format("y: %f",(h-th)/2 + th));
        canvas.drawText(mText, (w - tw - getPaddingLeft() - getPaddingRight())/2, (h-th)/2 - p.ascent(), p);
    }
}
