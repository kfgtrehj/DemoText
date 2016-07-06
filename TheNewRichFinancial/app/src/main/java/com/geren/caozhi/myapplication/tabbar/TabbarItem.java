package com.geren.caozhi.myapplication.tabbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geren.caozhi.myapplication.R;

/**
 * Created by caozhi on 2016/6/28.
 * 设置ID
 * 自己能够高亮
 * 自己是否被选中
 */
public class TabbarItem extends LinearLayout implements Checkable{

    private TextView itemText;
    private ImageView itemImg;

    private  boolean isChecked = false;
    private int defulatRes = 0;
    private int pressRes = 0;

    public void setPressRes(int pressRes) {
        this.pressRes = pressRes;
    }

    public void setDefulatRes(int defulatRes) {
        this.defulatRes = defulatRes;
    }



    public TabbarItem(Context context) {
        super(context);
        init(context);
    }

    public TabbarItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabbarItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    OnClickListener OnClickListener = null;

    @Override
    public void setOnClickListener(OnClickListener l) {
        OnClickListener = l;
    }
    private void init(Context context){
        inflate(context,R.layout.tabbar,this);

        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onChecked();
                if (OnClickListener != null)
                    OnClickListener.onClick(v);
            }
        });
        itemText = (TextView) findViewById(R.id.texttabbar);
        itemImg = (ImageView) findViewById(R.id.imgtabbar);

    }

    private void onChecked() {
        if (getParent() instanceof Tabbar) {
            final Tabbar group = (Tabbar) getParent();
            group.check(getId());
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }
    public void setTabbarText(String text){
        itemText.setText(text);
    }
    public void setTabarImgRes(int resId){
        itemImg.setImageResource(resId);
    }


    //设置当前的View被点击状态
    @Override
    public void setChecked(boolean checked) {
        if (isChecked == checked){
            return;
        }


        isChecked = checked;
        //设置状态
        setState(isChecked);
    }

    //设置状态
    private void setState(boolean isChecked) {
        if(isChecked){
            itemImg.setImageResource(pressRes);
        }else{
            itemImg.setImageResource(defulatRes);
        }
    }

    //放回当前有没有被选中
    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }
}
