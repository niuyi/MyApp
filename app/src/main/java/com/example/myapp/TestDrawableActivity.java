package com.example.myapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.*;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuyi on 2015/9/7.
 */
public class TestDrawableActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    class MyView extends View {

        List<Drawable> drawables = new ArrayList<Drawable>();

        public MyView(Context context) {
            super(context);

            float[] outerR = new float[] { 12, 12, 12, 12, 0, 0, 0, 0 };
            RectF   inset = new RectF(6, 6, 6, 6);
            float[] innerR = new float[] { 12, 12, 0, 0, 12, 12, 0, 0 };

            ShapeDrawable drawable1 = new ShapeDrawable(new RectShape());
            drawable1.getPaint().setColor(getResources().getColor(android.R.color.holo_red_dark));
            drawables.add(drawable1);

            ShapeDrawable drawable2 = new ShapeDrawable(new OvalShape());
            drawable2.getPaint().setColor(getResources().getColor(android.R.color.holo_blue_dark));
            drawables.add(drawable2);

            ShapeDrawable drawable3 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
            drawable3.getPaint().setColor(getResources().getColor(android.R.color.holo_green_dark));
            drawables.add(drawable3);

            ShapeDrawable drawable4 = new ShapeDrawable(new RoundRectShape(outerR, inset, innerR));
            drawable4.getPaint().setColor(getResources().getColor(android.R.color.darker_gray));
            drawables.add(drawable4);

            Path path = new Path();
            path.moveTo(50, 0);
            path.lineTo(0, 50);
            path.lineTo(50, 100);
            path.lineTo(100, 50);
            path.close();

            ShapeDrawable drawable5 = new ShapeDrawable(new PathShape(path, 100 ,100));
            drawable5.getPaint().setColor(getResources().getColor(android.R.color.white));
            drawables.add(drawable5);

            ShapeDrawable drawable6 = new ShapeDrawable(new ArcShape(45, -270));
            drawable6.getPaint().setColor(getResources().getColor(android.R.color.white));
            drawables.add(drawable6);

            ShapeDrawable drawable7 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
            drawable7.getPaint().setColor(getResources().getColor(android.R.color.holo_green_dark));
            drawable7.getPaint().setShader(new SweepGradient(150, 25,
                    new int[]{0xFFFF0000, 0xFF00FF00, 0xFF0000FF, 0xFFFF0000},
                    null));
            drawables.add(drawable7);

            ShapeDrawable drawable8 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
            drawable8.getPaint().setColor(getResources().getColor(android.R.color.holo_green_dark));
            drawable8.getPaint().setShader(new LinearGradient(0, 0, 50, 50,
                    new int[]{0xFFFF0000, 0xFF00FF00, 0xFF0000FF},
                    null, Shader.TileMode.CLAMP));
            drawables.add(drawable8);

            ShapeDrawable drawable9 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
            drawable9.getPaint().setColor(getResources().getColor(android.R.color.holo_green_dark));
            drawable9.getPaint().setShader(new BitmapShader(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            drawables.add(drawable9);

            ShapeDrawable drawable10 = new ShapeDrawable(new RoundRectShape(outerR, null, null));
            PathEffect pe = new DiscretePathEffect(10, 4);
            PathEffect pe2 = new CornerPathEffect(4);
            drawable10.getPaint().setColor(getResources().getColor(android.R.color.holo_orange_dark));
            drawable10.getPaint().setPathEffect(new ComposePathEffect(pe2, pe));
            drawables.add(drawable10);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int h = 0;
            for(Drawable d : drawables){
                canvas.save();
                canvas.translate(0, h);
                h += 150;
                d.setBounds(0, 0, 600, 100);
                d.draw(canvas);
                canvas.restore();
            }
        }
    }
}