package com.example.myapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.view.View;

import java.util.Arrays;

/**
 * Created by niuyi on 2015/9/8.
 */
public class MeasureTextActivity extends Activity {
    private static final String TAG = "MeasureTextActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    class MyView extends View {

        private Paint mPaint = new Paint();

        public MyView(Context context) {
            super(context);

            mPaint.setAntiAlias(true);
            mPaint.setStrokeWidth(5);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setTextSize(150);
            mPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.ITALIC));
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            canvas.save();
            canvas.translate(10, 200);
            showText(canvas, "Measurefgg", Paint.Align.LEFT);
            canvas.restore();
        }

        private void showText(Canvas canvas, String text, Paint.Align align) {
            float[] ws = new float[text.length()];

            int count = mPaint.getTextWidths(text, 0, text.length(), ws);

            android.util.Log.i(TAG, "count: " + count);
            android.util.Log.i(TAG, "ws: " + Arrays.asList(ws));

            float width = mPaint.measureText(text, 0, text.length());
            android.util.Log.i(TAG, "measureText: " + width);

            Rect    bounds = new Rect();
            mPaint.getTextBounds(text, 0, text.length(), bounds);

            android.util.Log.i(TAG, "bounds: " + bounds);

            mPaint.setColor(Color.BLUE);
            canvas.drawRect(bounds, mPaint);
            mPaint.setColor(Color.BLACK);
            canvas.drawText(text, 0, 0, mPaint);

            float[] pts = new float[2 + count*2];
            float x = 0;
            float y = 0;
            pts[0] = x;
            pts[1] = y;
            for (int i = 0; i < count; i++) {
                x += ws[i];
                pts[2 + i*2] = x;
                pts[2 + i*2 + 1] = y;
            }
            mPaint.setColor(Color.RED);
            mPaint.setStrokeWidth(0);
            canvas.drawLine(0, 0, width, 0, mPaint);
            mPaint.setStrokeWidth(5);
            canvas.drawPoints(pts, 0, (count + 1) << 1, mPaint);

        }
    }


}