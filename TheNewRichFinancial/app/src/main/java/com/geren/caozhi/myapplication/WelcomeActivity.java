package com.geren.caozhi.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.geren.caozhi.myapplication.tabbar.TextActivitty;

/**
 * Created by Administrator on 2016/8/11.
 * 第一次下载APP时进入引导页面，退出再进入时直接进入主页面
 */
public class WelcomeActivity extends Activity {

    private boolean isFirstIn = false;//用来判断是哪个值
    //声明一个延迟的时间
    private static final int TIME = 2000;
    private static final int GO_HOME = 1;
    private static final int GO_GUIDE = 2;

    //跳转时等待时间不能在主线程中，另创建一个线程
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        init();
    }
    //根据这个值来判断进入的是哪个界面
    //通过SharedPreferences进行存储，首先获取对象
    private void init(){
        SharedPreferences perPreferences = getSharedPreferences("xinfujinrong", MODE_PRIVATE);
        //判断prepreferences的值，并把它赋值给isFirstIn,如果第一次获取是没有的，为空true
        isFirstIn = perPreferences.getBoolean("isFirstIn",true);
        if (!isFirstIn){
            mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
        }else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
            //当进入引导页面后我们要存储这个值，
            SharedPreferences.Editor editor = perPreferences.edit();
            //传入数据
            editor.putBoolean("isFirstIn",false);
            //提交修改
            editor.commit();
        }
    }
    private void goHome() {
        Intent i = new Intent(WelcomeActivity.this, TextActivitty.class);
        startActivity(i);
        finish();
    }
    private void goGuide() {
        Intent i = new Intent(WelcomeActivity.this, Guides.class);
        startActivity(i);
        finish();
    }
}
