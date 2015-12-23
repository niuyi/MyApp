package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by niuyi on 2015/10/28.
 */
public class WebViewActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);

        WebView view = (WebView)findViewById(R.id.activity_main_webview);

        WebSettings webSettings = view.getSettings();
        webSettings.setJavaScriptEnabled(true);

        android.util.Log.i("WebViewActivity", "webSettings.getUserAgentString(): " + webSettings.getUserAgentString());

        view.loadUrl("https://mclient.alipay.com/service/rest.htm?sec_id=MD5&call_back_url=https%3A%2F%2Fb2capi.pay.xiaomi.com%2Fcallback%2F200110005151028117382482%2F14&format=xml&sign=bc601e36136a05207d52e4707febd8b2&v=2.0&req_data=%3Cauth_and_execute_req%3E%3Crequest_token%3E20151028f993c2b9f3548d431cabfc1f11faab4a%3C%2Frequest_token%3E%3C%2Fauth_and_execute_req%3E&service=alipay.wap.auth.authAndExecute&partner=2088701035562289");

    }
}