//package com.geren.caozhi.myapplication;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import java.util.ArrayList;
//
///**
// * Created by caozhi on 2016/7/1.
// */
//public class Guide extends Activity {
//    private ViewPager viewPager;
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.guide);
//
//        viewPager = (ViewPager) findViewById(R.id.guide);
//        viewPager.setAdapter(new MyViewPagerAdapter());
//    }
//
//    class MyViewPagerAdapter extends PagerAdapter {
//
//        int[] img = {R.mipmap.splash_img1,R.mipmap.splash_img2,R.mipmap.splash_img3,R.mipmap.splash_img4};
//
//        ArrayList<View> views = new ArrayList<>();
//        private LayoutInflater layoutInflater = null;
//
//        public MyViewPagerAdapter() {
//            layoutInflater = getActivity().getLayoutInflater();
//            View view1 = layoutInflater.inflate(R.layout.guide_img, null);
//            View view2 = layoutInflater.inflate(R.layout.guide_img, null);
//            View view3 = layoutInflater.inflate(R.layout.guide_img, null);
//            View view4 = layoutInflater.inflate(R.layout.guide_img, null);
//            views.add(view1);
//            views.add(view2);
//            views.add(view3);
//            views.add(view4);
//        }
//        @Override
//        public boolean isViewFromObject(View arg0, Object arg1) {
//            return arg0 == arg1;
//        }
//        @Override
//        public int getCount() {
//            return img.length;
//        }
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(views.get(position));
//        }
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            // TODO Auto-generated method stub
//
//            ImageView tv = (ImageView)views.get(position).findViewById(R.id.image);
//            tv.setImageResource((img[position]));
//
//            container.addView(views.get(position));
//            return views.get(position);
//        }
//    }
//
//
//}
