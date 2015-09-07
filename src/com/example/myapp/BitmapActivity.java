package com.example.myapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.InputStream;

/**
 * Created by linniu on 2015/8/29.
 */
public class BitmapActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new BitmapView(this));
    }


    class BitmapView extends View {

        Bitmap bitmap1;
        Bitmap bitmap2;
        Bitmap bitmap3;
        LinearGradient mShader;

        public BitmapView(Context context) {
            super(context);

            InputStream is = getResources().openRawResource(R.drawable.app_sample_code);
            bitmap1 = BitmapFactory.decodeStream(is);
            bitmap2 = bitmap1.extractAlpha();
            bitmap3 = Bitmap.createBitmap(200, 200, Bitmap.Config.ALPHA_8);


            Canvas canvas = new Canvas(bitmap3);
            Paint p = new Paint();
            p.setAlpha(0x80);
            p.setAntiAlias(true);
            canvas.drawCircle(100, 100, 100, p);

//            p.setAlpha(0x30);
            p.setTextAlign(Paint.Align.CENTER);
            p.setColor(Color.RED);
            p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            p.setTextSize(60);
            Paint.FontMetrics fm = p.getFontMetrics();
            Log.i("test", "y: " + (200 - fm.ascent) + " as: " + fm.ascent);
//            canvas.drawText("Alpha", 100, (200 - fm.ascent) / 2, p);
            canvas.drawText("Alpha", 100, 200 - (200 + fm.ascent)/2, p);
//            canvas.drawText("Alpha", x/2, (y-fm.ascent)/2, p);

            mShader = new LinearGradient(0, 0, 100, 70, new int[] {
                    Color.RED, Color.GREEN, Color.BLUE },
                    null, Shader.TileMode.MIRROR);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint p = new Paint();
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(bitmap1, 0, 0, p);

            p.setColor(Color.BLUE);
            canvas.drawBitmap(bitmap2, 0, 100, p);

            p.setShader(mShader);
            canvas.drawBitmap(bitmap3, 200, 500, p);
        }
    }
}