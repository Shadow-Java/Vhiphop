package com.example.vhiphop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/*
 *作者：created by Administrator on 2020/4/10 22:39
 *邮箱：1723928492@qq.com
 */public class HomePicAdapter extends PagerAdapter {
     private Context mContext;
     private int[] mDes = new int[]{
             R.string.a_name,
             R.string.b_name,
             R.string.c_name,
             R.string.d_name,
             R.string.e_name,
     };
    private int[] mImg = new int[]{
            R.drawable.iv1,
            R.drawable.iv2,
            R.drawable.iv3,
            R.drawable.iv4,
            R.drawable.iv5,
    };
     public HomePicAdapter(Activity activity){
         mContext=activity;
     }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.home_pic_item,null);
        TextView textView = (TextView) view.findViewById(R.id.tv_desc);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_img);
        textView.setText(mDes[position]);
        imageView.setImageResource(mImg[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
         container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
