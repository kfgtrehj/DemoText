package com.geren.caozhi.myapplication.activity.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.geren.caozhi.myapplication.R;
import com.geren.caozhi.myapplication.activity.MainActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/11.
 * 引导页面的四个页面，并按第四个页面时进入主页面
 * 跟ViewPagerAdapter要一块写
 */
public class Guides extends Activity {
    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    private ImageView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        initViews();
    }
    //用来加载滑动的View
    private void initViews(){
        LayoutInflater inflater = LayoutInflater.from(this);
        //因为view是加载在集合中，所以先实例化
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.guide_img, null));
        views.add(inflater.inflate(R.layout.guide_img2, null));
        views.add(inflater.inflate(R.layout.guide_img3, null));
        views.add(inflater.inflate(R.layout.guide_img4, null));

        vpAdapter = new ViewPagerAdapter(views, this);
        vp = (ViewPager) findViewById(R.id.guide);
        vp.setAdapter(vpAdapter);

        btn = (ImageView) views.get(3).findViewById(R.id.image4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Guides.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
