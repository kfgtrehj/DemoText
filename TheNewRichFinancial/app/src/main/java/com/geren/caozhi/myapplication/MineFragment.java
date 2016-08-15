package com.geren.caozhi.myapplication;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/5.
 * 我的页面
 */
public class MineFragment extends Fragment {

    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.mine, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager1);
        viewPager.setAdapter(new MyViewPagerAdapter());
        return view;
    }
    class MyViewPagerAdapter extends PagerAdapter {
        String[] names = {"周计划","我的推广"};
        ArrayList<View> views = new ArrayList<>();
        private LayoutInflater layoutInflater = null;
        public MyViewPagerAdapter(){
            layoutInflater = getActivity().getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.account,null);
            View view2 = layoutInflater.inflate(R.layout.account,null);
            views.add(view1);
            views.add(view2);
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        @Override
        public int getCount() {
            return names.length;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            TextView tv = (TextView) views.get(position).findViewById(R.id.account);
            tv.setText(names[position]);
            container.addView(views.get(position));
            return views.get(position);
        }
    }
}