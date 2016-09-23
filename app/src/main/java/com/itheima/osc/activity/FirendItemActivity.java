package com.itheima.osc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.itheima.osc.R;

/**
 * Created by admin on 2016/9/18.
 */
public class FirendItemActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_item);

        //找出webView
        WebView webView = (WebView) findViewById(R.id.friend_webview);

        //获取传递过俩的数据
        String webUrl = getIntent().getStringExtra("webUrl");
        //展示
        webView.loadUrl(webUrl);

    }
}
