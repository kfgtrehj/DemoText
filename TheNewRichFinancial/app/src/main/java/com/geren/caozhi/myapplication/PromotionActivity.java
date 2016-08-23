package com.geren.caozhi.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.geren.caozhi.myapplication.activity.MainActivity;
import com.geren.caozhi.myapplication.activity.fragment.EventFragment;
import com.geren.caozhi.myapplication.activity.fragment.PromotionFragment;

/**
 * Created by Administrator on 2016/8/16.
 * 活动、动态页面主页面
 */
public class PromotionActivity extends BaseActivity implements View.OnClickListener{

    Button bt_activity,bt_dynamic;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion);

        replaceFragment(new  EventFragment());

        bt_activity = (Button) findViewById(R.id.bt_activity);
        bt_dynamic = (Button) findViewById(R.id.bt_dynamic);
        image = (ImageView) findViewById(R.id.image_event);

        bt_activity.setOnClickListener(this);
        bt_dynamic.setOnClickListener(this);
        image.setOnClickListener(this);
    }

    private void replaceFragment(Fragment newFragment) {
        try {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.Evevt_ListView, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.bt_activity:   //跳转到活动页面
                fragment = new EventFragment();
                replaceFragment(fragment);
                break;
            case R.id.bt_dynamic:    //跳转到动态页面
                fragment = new PromotionFragment();
                replaceFragment(fragment);
                break;
            case R.id.image_event:
                intent.setClass(PromotionActivity.this, MainActivity.class);
                PromotionActivity.this.finish();
                break;
        }
    }
}
