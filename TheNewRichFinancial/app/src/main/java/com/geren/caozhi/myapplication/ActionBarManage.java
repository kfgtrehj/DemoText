package com.geren.caozhi.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/12.
 */
public class ActionBarManage extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionbarmanage);

        //获取ActionBar
        ActionBar actionBar = getActionBar(); //对于for < 3.0的版本，需要getSupportActionBar();来获取
        //设置导航方式
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);



        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

            //选种时
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

                Toast.makeText(ActionBarManage.this,"TabSelected" + tab.getPosition(),0).show();
            }

            //未选种时
            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            //重复选种时
            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
        for (int i = 0; i < 3; i++){
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText("Tab" + i);
            tab.setTabListener(tabListener);
            actionBar.addTab(tab);
        }
    }
}
