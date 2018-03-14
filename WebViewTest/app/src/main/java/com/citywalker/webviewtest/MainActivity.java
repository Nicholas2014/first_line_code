package com.citywalker.webviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView test = (WebView) findViewById(R.id.wv_test);
        test.getSettings().setJavaScriptEnabled(true);
        test.setWebViewClient(new WebViewClient());
        test.loadUrl("http://www.daydaycome.com");
    }
}
