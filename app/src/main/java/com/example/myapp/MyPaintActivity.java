package com.example.myapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;

/**
 * Created by niuyi on 2015/9/7.
 */
public class MyPaintActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }


    class MyView extends View {

        private float mX = 0;
        private float mY = 0;
        private Path mPath = new Path();
        private Paint mPaint = new Paint();
        private Canvas mCanvas;
        private Bitmap mBitmap;
        private Paint  mBitmapPaint = new Paint(Paint.DITHER_FLAG);;

        public MyView(Context context) {
            super(context);

            mPaint = new Paint();
            mPaint.setColor(0xFFFF0000);
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(50);
//            mPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.SOLID));
//            mPaint.setXfermode(new PorterDuffXfermode(
//                    PorterDuff.Mode.CLEAR));
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            Log.i("MyPaintActivityTest", "onSizeChanged : " + w + " ,h: " + h);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(0xFFFAAAAA);
            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
            canvas.drawPath(mPath, mPaint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    touchStart(event.getX(), event.getY());
                    invalidate();
                    break;

                case MotionEvent.ACTION_MOVE:
                    touchMove(event.getX(), event.getY());
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    touchUp(event.getX(), event.getY());
                    invalidate();
                    break;

            }

            return true;
        }

        private static final float TOUCH_TOLERANCE = 4;

        private void touchUp(float x, float y) {
            mPath.lineTo(x, y);
            mCanvas.drawPath(mPath, mPaint);
            mPath.reset();
            // commit the path to our offscreen
//            mCanvas.drawPath(mPath, mPaint);
//            // kill this so we don't double draw
//            mPath.reset();
        }

        private void touchMove(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
//                mPath.lineTo(x, y);
                mX = x;
                mY = y;
            }
        }

        private void touchStart(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }
    }

}