package com.example.myapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

/**
 * Created by linniu on 2015/8/30.
 */
public class ArcsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this ));
    }

    class MyView extends View {

        private  RectF oval;
        private int start = 0;
        private int sweep = 20;
        private  Drawable drawable;


        public MyView(Context context) {
            super(context);

            oval = new RectF(100, 100, 500, 500);

            drawable = getResources().getDrawable(R.drawable.button);
            drawable.setBounds(200,200 , 500, 500);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);

            canvas.drawRect(oval, paint);

            paint.setColor(Color.BLUE);

            sweep += 2;
            if(sweep >= 360){
                sweep -= 360;
                start += 15;
                if (start >= 360) {
                    start -= 360;
                }
            }

            canvas.drawArc(oval, start, sweep, true, paint);

//            drawable.draw(canvas);

            invalidate();
        }
    }
}