package com.geren.caozhi.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by caozhi on 2016/7/5.
 */
public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.manage, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyViewPagerAdapter());

        return view;
    }
    class MyViewPagerAdapter extends PagerAdapter {
        String[] names = {"周计划","月计划","年计划"};
        String[] discount = {"5%起","6%起","9%起"};
        String[] percentage = {"6","8","11"};
        String[] percentag = {".0%",".5%",".0%"};
        String[] number = {"7","30","365"};

        ArrayList<View> views = new ArrayList<>();
        private LayoutInflater layoutInflater = null;

        public MyViewPagerAdapter() {
            layoutInflater = getActivity().getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.viewpageplan, null);
            View view2 = layoutInflater.inflate(R.layout.viewpageplan, null);
            View view3 = layoutInflater.inflate(R.layout.viewpageplan, null);
            views.add(view1);
            views.add(view2);
            views.add(view3);
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return names.length;

        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            // TODO Auto-generated method stub

            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            TextView tv = (TextView) views.get(position).findViewById(R.id.jh);
            tv.setText(names[position]);
            TextView tv2 = (TextView) views.get(position).findViewById(R.id.jz);
            tv2.setText(discount[position]);
            TextView tv3 = (TextView) views.get(position).findViewById(R.id.nj1);
            tv3.setText(percentage[position]);
            TextView tv4 = (TextView) views.get(position).findViewById(R.id.nj2);
            tv4.setText(percentag[position]);
            TextView tv5 = (TextView) views.get(position).findViewById(R.id.nj3);
            tv5.setText(number[position]);

            container.addView(views.get(position));
            return views.get(position);
        }
    }
}