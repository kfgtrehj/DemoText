package com.geren.caozhi.myapplication.activity.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.geren.caozhi.myapplication.R;

/**
 * 在AndroidManifest.xml中一定要设置一个网络权限，否则无法连接网络，如下一行
 * <uses-permission android:name="android.permission.INTERNET"/>
 * Created by Administrator on 2016/7/5.
 * 保障页面
 */
public class GuaranteeFragment extends Fragment{
    private WebView webview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.guarantee, container, false);

        webview = (WebView) view.findViewById(R.id.webview);

        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //  加载需要显示的网页
        webview.loadUrl("https://www.baidu.com/");
//        String url = "http://www.fxxw.net";
//        webview.loadUrl(url);

        return view;

    }
}
