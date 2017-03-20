package com.example.myapp;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linniu on 2015/8/18.
 */
public class TestBallActivity extends Activity {
    private static final String TAG = "TestBallActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_ball);
        ViewGroup root = (ViewGroup)findViewById(R.id.root_container);
        root.addView(new MyView(this));
    }


    class MyView extends View {

        private List<ShapeHolder> balls = new ArrayList<ShapeHolder>();

        public MyView(Context context) {
            super(context);
            init();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            if (event.getAction() != MotionEvent.ACTION_DOWN &&
                    event.getAction() != MotionEvent.ACTION_MOVE) {
                return false;
            }

            Log.i(TAG, "onTouchEvent: " + event.getX());
            float x = event.getX();
            float y = event.getY();

            OvalShape ball = new OvalShape();
            ball.resize(200f, 200f);

            ShapeDrawable drawable = new ShapeDrawable(ball);



            ShapeHolder holder = new ShapeHolder(drawable);
            balls.add(holder);
            holder.x = x - 50;
            holder.y = y - 50;

            float h = (float)getHeight();
            float eventY = event.getY();
            int duration = (int)(500 * ((h - eventY)/h));

            ObjectAnimator y1 = ObjectAnimator.ofFloat(holder, "y", y - 50, getHeight() - 100);
            y1.setDuration(duration);
            y1.setInterpolator(new AccelerateInterpolator());

            ObjectAnimator x1 = ObjectAnimator.ofFloat(holder, "x", holder.x, holder.x - 100);
            x1.setDuration(duration / 4);
            x1.setRepeatCount(1);
            x1.setRepeatMode(ValueAnimator.REVERSE);
            x1.setInterpolator(new DecelerateInterpolator());


            ObjectAnimator x2 = ObjectAnimator.ofFloat(holder, "width", holder.getWidth(), holder.getWidth() + 100);
            x2.setDuration(duration / 4);
            x2.setRepeatCount(1);
            x2.setRepeatMode(ValueAnimator.REVERSE);
            x2.setInterpolator(new DecelerateInterpolator());


            ObjectAnimator x3 = ObjectAnimator.ofFloat(holder, "height", holder.getHeight(), holder.getHeight() - 100);
            x3.setDuration(duration / 4);
            x3.setRepeatCount(1);
            x3.setRepeatMode(ValueAnimator.REVERSE);
            x3.setInterpolator(new DecelerateInterpolator());


            ValueAnimator bounceBackAnim = ObjectAnimator.ofFloat(holder, "y", getHeight() - 100,
                    y - 50);
            bounceBackAnim.setDuration(duration);
            bounceBackAnim.setInterpolator(new DecelerateInterpolator());


            AnimatorSet set = new AnimatorSet();

            set.play(y1).before(x1);
            set.play(x1).with(x2);
            set.play(x1).with(x3);
            set.play(bounceBackAnim).after(x3);
            set.start();
//            set.play(alpha).after(y1);
//
//            ObjectAnimator alpha = ObjectAnimator.ofFloat(holder, "alpha", 1f, 0f);
//            alpha.setDuration(1000);
//
//            AnimatorSet set = new AnimatorSet();
//            set.play(alpha).after(y1);
//
//            set.start();

            return true;
        }

//        for (int i = 0; i < balls.size(); ++i) {
//            ShapeHolder shapeHolder = balls.get(i);
//            canvas.save();
//            canvas.translate(shapeHolder.getX(), shapeHolder.getY());
//            shapeHolder.getShape().draw(canvas);
//            canvas.restore();
//        }

        @Override
        protected void onDraw(Canvas canvas) {
            for(ShapeHolder ball : balls){
                Log.i(TAG, "onDraw ball!: " + ball.getY() + " : " + ball.getX());
                canvas.save();
                canvas.translate(ball.getX(), ball.getY());
                ball.s.draw(canvas);
                canvas.restore();
            }
        }

        private void init() {
            int red = (int) (255 * Math.random());
            int green = (int) (255*Math.random());
            int blue = (int) (255 * Math.random());

            int color = 0xFF000000 | red << 16 | green << 8 | blue;

//            setBackgroundColor(color);

            ObjectAnimator backgroundColor = ObjectAnimator.ofInt(this, "backgroundColor", color, Color.RED);
            backgroundColor.setDuration(5000);
            backgroundColor.setEvaluator(new ArgbEvaluator());
            backgroundColor.setRepeatMode(ValueAnimator.REVERSE);
            backgroundColor.setRepeatCount(ValueAnimator.INFINITE);
            backgroundColor.start();
        }
    }

    class ShapeHolder{
        private ShapeDrawable s;
        public float x;
        public float y;
        public Paint p;
        private float alpha = 1f;

        public ShapeHolder(ShapeDrawable s) {
            this.s = s;
            p = s.getPaint();
//            p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setColor(Color.BLUE);
            p.setStyle(Paint.Style.FILL_AND_STROKE);

            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            int color = 0xff000000 | red << 16 | green << 8 | blue;
            int darkColor = 0xff000000 | red/4 << 16 | green/4 << 8 | blue/4;
            RadialGradient gradient = new RadialGradient(37.5f, 12.5f,
                    50f, color, darkColor, Shader.TileMode.CLAMP);
            p.setShader(gradient);

        }

        public void setX(int x){
            this.x = x;
        }

        public float getY(){
            return y;
        }

        public float getX(){
            return x;
        }

        public void setY(float y){
//            Log.i(TAG, "setY" + y);
            this.y = y;
        }

        public void setAlpha(float alpha) {
            this.alpha = alpha;
            s.setAlpha((int)((alpha * 255f) + .5f));
        }

        public float getWidth(){
            return s.getShape().getWidth();
        }

        public void setWidth(float w){
            Shape shape = s.getShape();
            s.getShape().resize(w, shape.getHeight());
        }

        public float getHeight() {
            return s.getShape().getHeight();
        }
        public void setHeight(float height) {
            Shape shape = s.getShape();
            shape.resize(shape.getWidth(), height);
        }
    }
}