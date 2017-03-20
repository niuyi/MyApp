package com.example.myapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

/**
 * Created by linniu on 2015/8/30.
 */
public class FontActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new FontView(this));
    }

    public class FontView extends View {
        private Paint paint = new Paint();

        public FontView(Context context) {
            super(context);
            paint.setAntiAlias(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            paint.setColor(Color.BLACK);

            paint.setTextSize(100);
            String text = "This s a test for gg";
            canvas.drawText(text, 0, 500, paint);

            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            paint.setColor(Color.GREEN);

            float w = paint.measureText(text);
            paint.setColor(Color.RED);
            canvas.drawLine(0, 500, w, 500, paint);

            canvas.drawLine(0, 500 + fontMetrics.descent, w, 500 + fontMetrics.descent, paint);

            paint.setColor(Color.YELLOW);
            canvas.drawLine(0, 500 + fontMetrics.ascent, w, 500 + fontMetrics.ascent, paint);

            paint.setColor(Color.BLUE);
            canvas.drawLine(0, 500 + fontMetrics.bottom, w, 500 + fontMetrics.bottom, paint);

            paint.setColor(Color.CYAN);
            canvas.drawLine(0, 500 + fontMetrics.top, w, 500 + fontMetrics.top, paint);

        }
    }
}