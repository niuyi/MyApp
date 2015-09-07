package com.example.myapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by linniu on 2015/8/30.
 */
public class ClipActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }


    public class MyView extends View {

        private Path mPath = new Path();

        public MyView(Context context) {
            super(context);

            int top = 100;
            int bottom = 300;
            Log.i("TEST", "mid: " + ((top + bottom)/2) + " : " + ((top + bottom) >> 1));
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

//            mPath.reset();
//            canvas.clipPath(mPath);
//            mPath.addCircle(100, 100, 100, Path.Direction.CCW);
////            canvas.clipPath(mPath, Region.Op.XOR);
//            canvas.clipPath(mPath);
//
//            canvas.clipRect(0, 0, 400, 400);
//            canvas.drawColor(Color.GRAY);
//            Paint p = new Paint();
//            p.setColor(Color.RED);
//
//            canvas.drawLine(0, 0, 1000, 1000, p);


            canvas.save();
            canvas.translate(10, 10);
            drawScence(canvas);
            canvas.restore();

            canvas.save();
            canvas.translate(510, 10);
            canvas.clipRect(50, 50, 350, 350);
            canvas.clipRect(100, 100, 300, 300, Region.Op.DIFFERENCE);
            drawScence(canvas);
            canvas.restore();

            canvas.save();
            canvas.translate(10, 510);
            mPath.reset();
            canvas.clipPath(mPath);
            mPath.addCircle(100, 100, 100, Path.Direction.CCW);
            canvas.clipPath(mPath, Region.Op.REPLACE);
            drawScence(canvas);
            canvas.restore();
        }
    }

    private void drawScence(Canvas canvas) {
        canvas.clipRect(0, 0, 400, 400);

        canvas.drawColor(Color.GRAY);
        Paint p = new Paint();
        p.setColor(Color.RED);

        canvas.drawLine(0, 0, 1000, 1000, p);
        p.setColor(Color.GREEN);
        canvas.drawCircle(280, 120, 120, p);

        p.setColor(Color.BLACK);
        p.setTextSize(60);
        canvas.drawText("test", 120, 280, p);
    }
}