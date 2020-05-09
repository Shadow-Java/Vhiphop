package com.example.vhiphop.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Switch;

import com.example.vhiphop.HomeFragment;

import java.io.Serializable;

/*
 *作者：created by Administrator on 2020/4/12 03:02
 *邮箱：1723928492@qq.com
 */
public class Channel implements Serializable {//序列化
     public static final int SHOW =1;//电视剧
     public static final int MOVIE=2;//电影
     public static final int COMIC=3;//动漫
     public static final int DOCUMENTRY=4;//记录片
     public static final int MUSIC=5;//音乐
     public static final int VARIETY=6;//综艺
     public static final int LIVE=7;//直播
     public static final int FAVORITE=8;//收藏
     public static final int HISTORY=9;//历史记录
     public static final int MAX_COUNT=9;//频道数
     private   int ID;//序列化ID
     private   String ChannelName;
     private Context mContext;
     //private static final String TAG = HomeFragment.class.getSimpleName();
//     public Channel(int position, Activity activity){
//          ID=position;
//          act=activity;
//     }

    public Channel(int id, Context context){
        ID=id;
        mContext = context;
        switch(ID){
            case SHOW:
                ChannelName = "电视剧";//电视剧
                break;
            case MOVIE:
                ChannelName = "电影";
                break;
            case COMIC:
                ChannelName = "动漫";
                break;
            case DOCUMENTRY:
                ChannelName = "纪录片";
                break;
            case MUSIC:
                ChannelName = "音乐";
                break;
            case VARIETY:
                ChannelName = "综艺";
                break;
            case LIVE:
                ChannelName = "直播";
                break;
            case FAVORITE:
                ChannelName = "收藏";
                break;
            case HISTORY:
                ChannelName = "历史记录";
                break;
        }
    }
     public  String getChannelName(){
//          String[] Name=new String[]{"电视剧","电影","动漫","纪录片","音乐","综艺","直播","收藏","历史记录"};
//          int m=ID-1;
//               ChannelName=Name[m];//private static类型不能用Name[ID]
          return ChannelName;
     }

     public  int getChannelId(){
          return ID;
     }
}
