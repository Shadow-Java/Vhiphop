package com.example.vhiphop.model;

import android.content.Context;

import com.example.vhiphop.R;

/*
 *作者：created by 影子 on 2020/4/20 22:49
 *邮箱：1723928492@qq.com
 */
public class Site {
    public static final int SOHU=1;//搜狐
    public static final int LETV =2;//乐视TV

    private   int siteId;//序列化ID
    private   String siteName;
    private Context mContext;
    public static final int MAX_SITE = 2;

    public Site(int id){
        siteId = id;
        switch(siteId){
            case LETV:
                siteName = "乐视视频";
                break;
            case SOHU:
                siteName = "搜狐视频";
                break;

        }
    }

    public int getSiteId(){
        return siteId;
    }

}
