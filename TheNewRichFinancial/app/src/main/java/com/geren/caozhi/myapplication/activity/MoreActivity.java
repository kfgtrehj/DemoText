package com.geren.caozhi.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.geren.caozhi.myapplication.BaseActivity;
import com.geren.caozhi.myapplication.R;

/**
 * Created by caozhi on 2016/7/1.
 * 更多页面
 */
public class MoreActivity extends BaseActivity {

    private ImageView button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more);



        button = (ImageView) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //后退到之前页面，当前页面被消除，可节省内存
                MoreActivity.this.finish();
            }
        });

    }




}
