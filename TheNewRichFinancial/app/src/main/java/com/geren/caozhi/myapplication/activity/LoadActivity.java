package com.geren.caozhi.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.geren.caozhi.myapplication.BaseActivity;
import com.geren.caozhi.myapplication.R;
import com.geren.caozhi.myapplication.activity.guide.Guides;
import com.geren.caozhi.myapplication.utility.SharedPreferencesUtils;

/**
 * Created by neil on 16/8/15.
 */
public class LoadActivity extends BaseActivity{

    public static int TIME = 2000;
    public static final int GO_HOME = 1;
    public static final int GO_GUIED = 2;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIED:
                    goGuide();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow(R.color.white);
        setContentView(R.layout.load_activity_view);
        boolean isFirst = (boolean) SharedPreferencesUtils.getParam(this, "isFirst", true);
        //如果用户第一次登录， 设置，第一次登录为false.同时去引导页面
        if(isFirst){
            mHandler.sendEmptyMessageDelayed(GO_GUIED, TIME);
        }else{
            mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
            showToast("去首页");
        }
    }
    /**
     * 去引导页 同时设置第一次登陆为false
     * */
    private void goGuide() {
        SharedPreferencesUtils.setParam(this, "isFirst", false);
        Intent i = new Intent(LoadActivity.this, Guides.class);
        startActivity(i);
        finish();
    }
    /**
     * 去主页
     * */
    public void goHome(){
        Intent i = new Intent(LoadActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
