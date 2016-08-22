package com.geren.caozhi.myapplication.activity.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.geren.caozhi.myapplication.R;


/**
 * Created by caozhi on 2016/7/5.
 * 主页面
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private ImageView financialPromotion;
    private ImageView financialMore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.home_view, container, false);
        financialPromotion = (ImageView) view.findViewById(R.id.financialPromotion);
        financialMore = (ImageView) view.findViewById(R.id.financialMore);

        financialPromotion.setOnClickListener(this);
        financialMore.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            //去更多页面
            case R.id.financialMore:
                break;
            //去活动页面
            case R.id.financialPromotion:
                break;
        }
    }
}