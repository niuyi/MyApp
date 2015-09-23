package com.example.myapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

/**
 * Created by linniu on 2015/9/10.
 */
public class TextAlignActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TextView(this));
    }


    class TextView extends View {

        public TextView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            Paint p = new Paint();

            p.setColor(Color.BLACK);

            p.setAntiAlias(true);
            p.setTextSize(60);
            p.setTextAlign(Paint.Align.LEFT);
            canvas.drawText("LEFT", 500, 150, p);

            p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("CENTER", 500, 350, p);

            p.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("RIGHT", 500, 550, p);

            Path path = new Path();

            path.moveTo(10, 10);
            path.cubicTo(100, 800, 20, 1000, 700, 700);

            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, p);
        }
    }
}