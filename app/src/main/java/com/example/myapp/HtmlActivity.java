package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.io.*;

/**
 * Created by niuyi on 2015/10/8.
 */
public class HtmlActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.html_activity);

        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setText(Html.fromHtml(getText()));
    }

    private String getText(){

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/data/temp/test.html")));

            StringBuilder sb = new StringBuilder();

            String s;

            while((s = reader.readLine()) != null){
                sb.append(s);
            }

            reader.close();

            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "no";

    }
}