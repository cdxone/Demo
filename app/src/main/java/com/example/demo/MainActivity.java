package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    int[] imageResIds = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};
    boolean[] result = new boolean[] {false,false,false,false,false};
    private MainActivity mContext;
    private ViewPager vp;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        iv = findViewById(R.id.iv);
        vp = findViewById(R.id.vp);
        vp.setPageMargin(100);//设置page间间距，自行根据需求设置
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(adpter);
        vp.setCurrentItem(1);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //在翻页过程中触发。该方法的三个参数取值说明为:第-一个参数表示当前页面的序号
            //第二个参数表示当前页面偏移的百分比，取值为0到1;第三个参数表示当前页面的偏移距离
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG,"onPageScrolled,position:" + position);

            }

            //在翻页结束后触发。arg0 表示当前滑到了哪一一个页面
            @Override
            public void onPageSelected(int position) {
                Log.e(TAG,"onPageSelected,position:" + position);
            }

            //翻页状态改变时触发。state 取值说明为: 0表示静止，1 表示正在滑动，2表示滑动完毕
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e(TAG,"onPageScrollStateChanged,state:" + state);
                if (state == 0){
                    iv.setVisibility(View.VISIBLE);
                } else {
                    iv.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * ViewPager的适配器
     */
    PagerAdapter adpter = new PagerAdapter() {

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            //无限轮播效果
            return Integer.MAX_VALUE;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //从container中移除View
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_vp_item,null);
            ImageView iv = view.findViewById(R.id.iv);
            //
            iv.setImageResource(imageResIds[position % 4]);
            //container中加入View
            container.addView(view);
            return view;
        }
    };
}