package com.example.vhiphop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.vhiphop.base.BaseFragment;

/*
 *作者：created by 影子 on 2020/4/20 22:16
 *邮箱：1723928492@qq.com
 */
public class DetailListFragment extends BaseFragment {
    private static int mSiteId;
    private static int mChannelId;
    private static final String CHANNEL_ID = "channelid";
    private static final String SITE_ID = "siteid";

//    public DetailListFragment(int siteId,int channId){//SiteId是搜狐还是乐视 channId是电影还是电视剧
//        mSiteId = siteId;
//        mChannelId = channId;
//    }
    public DetailListFragment(){//需要空的构造 才不会出错
    }

    public static Fragment newInstance(int siteId,int channId){
        DetailListFragment fragment = new DetailListFragment();
        mSiteId = siteId;
        mChannelId = channId;
        Bundle bundle = new Bundle();//把值传入fragment里面去 bundle intent传值
        bundle.putInt(SITE_ID,siteId);//把值放入bundle
        bundle.putInt(CHANNEL_ID,channId);
        fragment.setArguments(bundle);//如： 乐视，电视剧页面，fragment需要知道
        return fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detaillist;
    }

    @Override
    protected void initData() {

    }
}
