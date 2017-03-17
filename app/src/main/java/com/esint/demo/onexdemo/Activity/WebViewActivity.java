package com.esint.demo.onexdemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.esint.demo.onexdemo.Common.Common;
import com.esint.demo.onexdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Linsa on 2017/3/17:09:03.
 * des: 进行展示扫码结果的Activity
 */

public class WebViewActivity extends AppCompatActivity {
    @InjectView(R.id.wv_asw_scan)
    WebView wvAswScan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_web);
        ButterKnife.inject(this);

        WebSettings webSettings = wvAswScan.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);


        Intent intent = getIntent();
        String resultUrl = intent.getStringExtra(Common.EXTRA_URL);
        if (!resultUrl.isEmpty()){
            wvAswScan.loadUrl(resultUrl);
        }


    }
}
