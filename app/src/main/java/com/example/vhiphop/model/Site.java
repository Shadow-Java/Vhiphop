package com.example.vhiphop.model;

import android.content.Context;

import com.example.vhiphop.R;

/*
 *作者：created by 影子 on 2020/4/20 22:49
 *邮箱：1723928492@qq.com
 */
public class Site {
    public static final int LETV =1;//乐视TV
    public static final int SOHU=2;//搜狐

    private   int siteId;//序列化ID
    private   String siteName;
    private Context mContext;
    public static final int MAX_SITE = 2;

    public Site(int id, Context context){
        siteId = id;
        mContext = context;
        switch(siteId){
            case LETV:
                siteName = mContext.getResources().getString(R.string.site_letv);
                break;
            case SOHU:
                siteName = mContext.getResources().getString(R.string.site_souhu);
                break;

        }
    }
    public  String getChannelName(){
        return siteName;
    }

    public  int getChannelId(){
        return siteId;
    }

}
