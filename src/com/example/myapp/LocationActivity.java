package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Created by niuyi on 2015/11/10.
 */
public class LocationActivity extends Activity {

    private LocationClient mLocationClient;
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity);

        mLocationClient = ((MyApp)getApplication()).mLocationClient;
    }

    public void doGetLoc(View view){
        android.util.Log.i("baidu", "doGetLoc");


        initLocation();

        mLocationClient.start();//��λSDK start֮���Ĭ�Ϸ���һ�ζ�λ���󣬿����������ж�isstart����������request
        mLocationClient.requestLocation();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);//��ѡ��Ĭ�ϸ߾��ȣ����ö�λģʽ���߾��ȣ��͹��ģ����豸
//        option.setCoorType(tempcoor);//��ѡ��Ĭ��gcj02�����÷��صĶ�λ�������ϵ��
//        int span=1000;
//        try {
//            span = Integer.valueOf(frequence.getText().toString());
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
        option.setScanSpan(0);//��ѡ��Ĭ��0��������λһ�Σ����÷���λ����ļ����Ҫ���ڵ���1000ms������Ч��
        option.setIsNeedAddress(true);//��ѡ�������Ƿ���Ҫ��ַ��Ϣ��Ĭ�ϲ���Ҫ
//        option.setOpenGps(true);//��ѡ��Ĭ��false,�����Ƿ�ʹ��gps
//        option.setLocationNotify(true);//��ѡ��Ĭ��false�������Ƿ�gps��Чʱ����1S1��Ƶ�����GPS���
        option.setIgnoreKillProcess(true);//��ѡ��Ĭ��true����λSDK�ڲ���һ��SERVICE�����ŵ��˶������̣������Ƿ���stop��ʱ��ɱ��������̣�Ĭ�ϲ�ɱ��

        mLocationClient.setLocOption(option);
    }
}