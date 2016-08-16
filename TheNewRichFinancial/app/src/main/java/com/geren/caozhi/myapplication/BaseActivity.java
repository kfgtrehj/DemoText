package com.geren.caozhi.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by neil on 16/8/15.
 */
public class BaseActivity extends Activity {

    /**
     * 设置status Bar 采用开源控件 System bar Tint
     * @link https://github.com/jgilfelt/SystemBarTint
     * 初始化
     * 因为这是别人写的开源控件，要求了要在build.gradle中的dependencies，
     * 添加一句compile 'com.readystatesoftware.systembartint:systembartint:1.0.3'
     * 大概意思是：此项目依赖另一个项目
     * */

    private SystemBarTintManager tintManager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager = new SystemBarTintManager(this);
        initWindow();
//        setContentView(R.layout.load_activity_view);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//        }
//
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.white);//通知栏所需颜色

    }
    @TargetApi(19)
    protected void initWindow(int colorRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(colorRes));
            tintManager.setStatusBarTintEnabled(true);
        }
        //mDecorView = getWindow().getDecorView();
    }

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.title_blue));
            tintManager.setStatusBarTintEnabled(true);
        }
       // mDecorView = getWindow().getDecorView();
    }


    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 显示一个弹出框 内容，可变。
     * @param str 传递需要显示的信息
     * */
    public void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
