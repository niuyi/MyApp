package com.example.myapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapp.model.MyData;

/**
 * Created by niuyi on 2015/9/28.
 */
public class ParcelableActivity extends Activity {
    private static final String TAG = "ParcelableActivity";
    private MyData data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parcelable_test);

        TestFragment frag = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", "test");

        data = new MyData(100, "hello");

        bundle.putParcelable("data", data);

        frag.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.root_container, frag).commit();
    }

    public class TestFragment extends Fragment {
        private static final String TAG = "TestFragment";

        public TestFragment(){
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            android.util.Log.i(TAG, "name: " + getArguments().getString("name"));

            MyData temp = (MyData) getArguments().getParcelable("data");
            android.util.Log.i(TAG, "name: " + temp.name + " v: " + temp.value);
            android.util.Log.i(TAG, "same: " + (temp == data));


            return null;
        }
    }

}