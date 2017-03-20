package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.myapp.model.Address;
import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by linniu on 2015/10/31.
 */
public class LitepalActivity extends Activity {

    private static final String TAG = "LitepalActivity";
    private TextView tv;

    public int index = 0;
    private long id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.litepal_activity);
        tv = (TextView)findViewById(R.id.tv_content);

//        DataSupport.deleteAll(Address.class, null);
    }

    public void doList(View view){
        tv.setText("");
        List<Address> allSongs = DataSupport.order("timestamp desc").find(Address.class);
        if(allSongs.size() == 0){
            tv.setText("empty");
        }else{
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for(Address a : allSongs){
                sb.append(String.format("id(%d) value(%s) time(%s) orderId(%s) test2(%s)", a.getId(), a.getValue(), a.getTimestamp(), a.getOrderId(), a.getTest2())).append("\r\n");
                if(i++ == 3){
                    id = a.getId();
                }
            }
            tv.setText(sb.toString());
        }
    }

    public void doAdd(View view){
        Log.i(TAG, "doadd");
        Address a = new Address();
        a.setValue("value");
        a.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000));
        a.save();
    }

    public void doUpdate(View view){
        Address a = new Address();
        a.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000));
        a.update(id);
    }
}