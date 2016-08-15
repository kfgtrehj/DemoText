package com.geren.caozhi.myapplication.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import com.geren.caozhi.myapplication.BaseActivity;
import com.geren.caozhi.myapplication.R;
import com.geren.caozhi.myapplication.utility.SharedPreferencesUtils;

/**
 * Created by neil on 16/8/15.
 */
public class LoadActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_activity_view);
        boolean isFirst = (boolean) SharedPreferencesUtils.getParam(this, "Boolean", true);
        //如果用户第一次登录， 设置，第一次登录为false.同时去引导页面
        if(isFirst){
            SharedPreferencesUtils.setParam(this, "Boolean", false);
            showToast("去引导");
        }else{
            showToast("去首页");
        }

    }

}
