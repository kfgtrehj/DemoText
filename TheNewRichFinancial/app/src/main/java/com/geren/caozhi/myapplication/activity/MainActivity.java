package com.geren.caozhi.myapplication.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import com.geren.caozhi.myapplication.BaseActivity;
import com.geren.caozhi.myapplication.R;
import com.geren.caozhi.myapplication.activity.fragment.FriendFragment;
import com.geren.caozhi.myapplication.activity.fragment.GuaranteeFragment;
import com.geren.caozhi.myapplication.activity.fragment.HomeFragment;
import com.geren.caozhi.myapplication.activity.fragment.MineFragment;
import com.geren.caozhi.myapplication.tabbar.TabbarItem;

/**
 * Created by Administrator on 2016/7/5.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{

    private TabbarItem myTabbarItem1;
    private TabbarItem myTabbarItem2;
    private TabbarItem myTabbarItem3;
    private TabbarItem myTabbarItem4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        myTabbarItem1 = (TabbarItem) findViewById(R.id.myTabbarItem1);
        //设置tabbar 显示的string
        myTabbarItem1.setTabbarText("理财");
        //设置没被点击的时候显示什么
        myTabbarItem1.setDefulatRes(R.mipmap.financing);
        //设置按下的时候，显示什么
        myTabbarItem1.setPressRes(R.mipmap.financing_press);
        //初始化显示什么
        myTabbarItem1.setTabarImgRes(R.mipmap.financing);
//        //设置状态为按下
        myTabbarItem1.setChecked(true);



        myTabbarItem2 = (TabbarItem) findViewById(R.id.myTabbarItem2);
        myTabbarItem2.setTabbarText("保障");
        myTabbarItem2.setDefulatRes(R.mipmap.security);
        myTabbarItem2.setPressRes(R.mipmap.security_press);
        myTabbarItem2.setTabarImgRes(R.mipmap.security);

        myTabbarItem3 = (TabbarItem) findViewById(R.id.myTabbarItem3);
        myTabbarItem3.setTabbarText("好友");
        myTabbarItem3.setDefulatRes(R.mipmap.optimist);
        myTabbarItem3.setPressRes(R.mipmap.optimist_press);
        myTabbarItem3.setTabarImgRes(R.mipmap.optimist);

        myTabbarItem4 = (TabbarItem) findViewById(R.id.myTabbarItem4);
        myTabbarItem4.setTabbarText("我的");
        myTabbarItem4.setDefulatRes(R.mipmap.mine);
        myTabbarItem4.setPressRes(R.mipmap.mine_press);
        myTabbarItem4.setTabarImgRes(R.mipmap.mine);

        myTabbarItem1.setOnClickListener(this);
        myTabbarItem2.setOnClickListener(this);
        myTabbarItem3.setOnClickListener(this);
        myTabbarItem4.setOnClickListener(this);

        replaceFragment(new HomeFragment());

    }

    private void replaceFragment(Fragment newFragment){
        try{
            FragmentManager fm =getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }catch (Exception e){

        }
    }

    //    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.myTabbarItem1:
                fragment = new HomeFragment();
                replaceFragment(fragment);
                break;
            case R.id.myTabbarItem2:
                //fragment管理器
                fragment = new GuaranteeFragment();
                replaceFragment(fragment);
                break;
            case R.id.myTabbarItem3:
                //fragment管理器
                fragment = new FriendFragment();
                replaceFragment(fragment);
                break;
            case R.id.myTabbarItem4:
                //fragment管理器
                fragment = new MineFragment();
                replaceFragment(fragment);
                break;
        }
    }
    protected void onResume(){
       int id = getIntent().getIntExtra("id", 0);
           if (id == 2){
               FriendFragment fragment =new FriendFragment();
               replaceFragment(fragment);
//            TabHost mTabHost = null;
//            mTabHost.setCurrentTab(3);
//            Fragment fragmen = new FriendFragment();
//            FragmentManager fmanger =getFragmentManager();
//            FragmentTransaction ftran =fmanger.beginTransaction();
//            ftran.replace(R.id.franme_meun,fragmen);
//            mit();
//            info_meun.setChecked(true);
//            li.setVisibility(View.GONE);
              super.onResume();

        }
  }
}
