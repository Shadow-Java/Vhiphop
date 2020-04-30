package com.example.vhiphop;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

/*
 *作者：created by 影子 on 2020/4/27 20:42
 *邮箱：1723928492@qq.com
 */
public class AppManager extends Application {//okhttp 和 Json请求数据相关
    private static Gson mGson;
    private static OkHttpClient mOkHttpClient;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mGson = new Gson();
        mContext = this;
        mOkHttpClient = new OkHttpClient();
    }

    public static Gson getGson(){
        return mGson;
    }

    public static OkHttpClient getHttpClient(){
        return mOkHttpClient;
    }
    public static Context getContext(){
        return mContext;
    }

    public static Resources getResource(){
        return mContext.getResources();
    }

    /**
     * 当前网络是否可用
     * @return
     */

    public static boolean isNetWorkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }
}
