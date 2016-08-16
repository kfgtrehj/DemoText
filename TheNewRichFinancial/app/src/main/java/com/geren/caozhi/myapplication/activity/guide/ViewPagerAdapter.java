package com.geren.caozhi.myapplication.activity.guide;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by Administrator on 2016/8/11.
 * 引导页面，跟Guides要一起写
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> views; //承载所有的view
    private Context context; //传递上下文

    public ViewPagerAdapter(List<View> views, Context context){
        this.views = views;
        this.context = context;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //我们不能将所有的View都保存，当不需要的时候我将它销毁
        ((ViewPager)container).removeView(views.get(position));
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //加载View
        ((ViewPager)container).addView(views.get(position));
        //返回当前view
        return views.get(position);
    }
    @Override
    public int getCount() {
        return views.size(); //返回当前view的一个长度
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        //判断当前的View是不是我们需要的对象
        return (arg0 == arg1);
    }
}
