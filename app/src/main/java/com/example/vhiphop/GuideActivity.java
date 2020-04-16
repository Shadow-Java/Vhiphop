package com.example.vhiphop;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener{
    private List<View> mViewList;
    private ViewPager mViewPager;
    private ImageView[] mDotList;
    private int mLastPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();//三个view
        initViewPager();
        initDots();
    }
    private void initView(){//三个布局
        LayoutInflater inflater = LayoutInflater.from(this);//打气筒填充view
        mViewList=new ArrayList<>();
        mViewList.add(inflater.inflate(R.layout.guide_one_layout,null));
        mViewList.add(inflater.inflate(R.layout.guide_two_layout,null));
        mViewList.add(inflater.inflate(R.layout.guide_three_layout,null));
    }

    private void initViewPager(){
        mViewPager =(ViewPager)findViewById(R.id.viewpager);
        MyPagerAdapter adapter= new MyPagerAdapter(mViewList,this);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
    }
    private void initDots(){//初始化点
        LinearLayout dotsLayout=(LinearLayout) findViewById(R.id.ll_dots_layout);
        mDotList=new ImageView[mViewList.size()];
        for(int i=0;i<mViewList.size();i++){
            mDotList[i]=(ImageView) dotsLayout.getChildAt(i);
            mDotList[i].setEnabled(false);//灰色
        }
        mLastPosition=0;
        mDotList[0].setEnabled(true);//第一页变成白色
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {//页面滚动的时候

    }

    @Override
    public void onPageSelected(int position) {//页面选中 滑动过程中的一个回调
        setCurrentDotPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {//在滚动过程中变化

    }
    private void setCurrentDotPosition(int position){
        mDotList[position].setEnabled(true);
        mDotList[mLastPosition].setEnabled(false);//灰色
        mLastPosition = position;
    }
    class MyPagerAdapter extends PagerAdapter{
        private List<View> mImageViewList;//从外面接受view
        private Context mContext;
        MyPagerAdapter(List<View> list, Context context){
            super();
            mImageViewList=list;
            mContext =context;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {//显示引导图
            if(mImageViewList != null){
                if(mImageViewList.size()>=0){
                    container.addView(mImageViewList.get(position));
                    if(position == mImageViewList.size()-1){
                        ImageView imageView = (ImageView)mImageViewList.get(position).findViewById(R.id.iv_start);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startHomeActiity();
                                setGuided();
                            }
                        });
                    }
                    return mImageViewList.get(position);
                }
            }
            return null;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,Object object) {
            if(mImageViewList != null){
                if(mImageViewList.size()>=0){
                    container.removeView(mImageViewList.get(position));
                }
            }
        }

        @Override
        public int getCount() {//list
            if(mImageViewList!=null){
                return mImageViewList.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }
    }

    private void setGuided(){//已经引导过了，下次进来可以不用引导
       SharedPreferences sp = getSharedPreferences("config",MODE_PRIVATE);
       sp.edit().putBoolean("mIsFirstIn",false);
       sp.edit().commit();
    }

    private void startHomeActiity(){
        Intent intent = new Intent(GuideActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
