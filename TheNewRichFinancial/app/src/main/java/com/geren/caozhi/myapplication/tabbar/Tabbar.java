package com.geren.caozhi.myapplication.tabbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/7/5.
 */
public class Tabbar extends LinearLayout{
    public Tabbar(Context context){
        super(context);
        init();
    }
    //构造方法
    public Tabbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    //构造方法
    public Tabbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //创建Init方法（初始化方法）
    private void init(){
        //把自己改成横向
        this.setOrientation(HORIZONTAL);
    }
    // 当前被选中的Item ID
    int mCheckedId = -1;

    //定义一个接口 当Item被点击的时候，就调用onChecked 方法
    private OnTabGroupCheckedListener onTabGroupCheckedListener = null;
    public void setOnTabGroupCheckedListener(
            OnTabGroupCheckedListener onTabGroupCheckedListener) {
        this.onTabGroupCheckedListener = onTabGroupCheckedListener;
    }

    //创建ViewGroup的时候，如果ViewGroup 有子View就会调用下面的方法
    /**
     * 第一个参数 View child
     * 第二个参数 int index (0,1,2,3) 当前的View 是第几个
     * 第三个参数 LayoutParams 形容View 大小
     * */
    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child,index,params);
        if(child instanceof TabbarItem){
            final TabbarItem tab = (TabbarItem) child;
            if (tab.isChecked()) {
                check(tab.getId());
            }
        }
    }
    //检查当前的Item 是否被点击
    public void check(int checkId) {
        //如果Item被点击过 不做任何事情
        if (mCheckedId == checkId) {
            return;
        }
        //如果没被点击过 do some thing
        setCheckedStateForView(mCheckedId, false);
        setCheckedId(checkId);
        mCheckedId = checkId;
        if (onTabGroupCheckedListener != null)
            onTabGroupCheckedListener.onChecked(checkId);
    }

    private void setCheckedId(int id) {
        View checkedView = findViewById(id);
        if (checkedView != null && checkedView instanceof TabbarItem) {
            ((TabbarItem) checkedView).setChecked(true);
        }
    }
    //从View中，设置自己的状态
    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView != null && checkedView instanceof TabbarItem) {
            ((TabbarItem) checkedView).setChecked(checked);
        }
    }
}
