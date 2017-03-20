package com.example.myapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.myapp.R;

/**
 * Created by linniu on 2015/12/9.
 */
public class TestTextFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_text_fragment, container, false);
        TextView tv = (TextView)view.findViewById(R.id.textView);
        if(getArguments() != null){
            tv.setText(getArguments().getString("Text"));
        }

        return view;
    }
}