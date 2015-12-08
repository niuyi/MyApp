package com.example.myapp;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by linniu on 2015/12/8.
 */
public class CoordsTestActivity extends Activity {
    private static final String TAG = "CoordsTestActivity";
    private ViewGroup root;
    private static int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coords_activity);
        root = (ViewGroup)findViewById(R.id.root_container);

        Button button = (Button)findViewById(R.id.btn_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index != 0){
                    button.scrollBy(5,10);
                }

                index++;

                Log.i(TAG, "left: " + v.getLeft() + " ,top: " + v.getTop() + " ,right: " + v.getRight() + " ,bottom: " + v.getBottom());
                Log.i(TAG, "scrollX: " + v.getScrollX() + " ,scrollY: " + v.getScrollY());
                Rect temp = new Rect();
                v.getFocusedRect(temp);
                Log.i(TAG, "getFocusedRect:" + temp);

                root.offsetDescendantRectToMyCoords(v, temp);

                Log.i(TAG, "offsetDescendantRectToMyCoords:" + temp);
            }
        });
    }
}
//
//12-08 21:43:47.625  14303-14303/com.example.myapp I/MyActivity©s resolveInfos.get(0).activityInfo:ActivityInfo{42e4bc68 com.example.myapp.CoordsTestActivity}
//        12-08 21:43:49.452  14303-14303/com.example.myapp I/CoordsTestActivity©s left: 50 ,top: 100 ,right: 187 ,bottom: 244
//        12-08 21:43:49.453  14303-14303/com.example.myapp I/CoordsTestActivity©s scrollX: 0 ,scrollY: 0
//        12-08 21:43:49.455  14303-14303/com.example.myapp I/CoordsTestActivity©s getFocusedRect:Rect(0, 0 - 137, 144)
//        12-08 21:43:49.455  14303-14303/com.example.myapp I/CoordsTestActivity©s offsetDescendantRectToMyCoords:Rect(50, 100 - 187, 244)
//        12-08 21:44:07.340  14303-14303/com.example.myapp I/CoordsTestActivity©s left: 50 ,top: 100 ,right: 187 ,bottom: 244
//        12-08 21:44:07.340  14303-14303/com.example.myapp I/CoordsTestActivity©s scrollX: 5 ,scrollY: 0
//        12-08 21:44:07.342  14303-14303/com.example.myapp I/CoordsTestActivity©s getFocusedRect:Rect(5, 0 - 142, 144)
//        12-08 21:44:07.343  14303-14303/com.example.myapp I/CoordsTestActivity©s offsetDescendantRectToMyCoords:Rect(50, 100 - 187, 244)