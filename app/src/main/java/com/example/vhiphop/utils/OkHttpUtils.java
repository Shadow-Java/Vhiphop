package com.example.vhiphop.utils;

import com.example.vhiphop.AppManager;

import okhttp3.Callback;
import okhttp3.Request;

/*
 *作者：created by 影子 on 2020/4/28 20:31
 *邮箱：1723928492@qq.com
 */
public class OkHttpUtils {
    private static final String REQUEST_TAG = "okhttp";


    public static Request buildRequest(String url){
        if(AppManager.isNetWorkAvailable()){
            Request request = new Request.Builder()
                    .tag(REQUEST_TAG)
                    .url(url)
                    .build();
            return null;
        }
        return null;
    }

    public static void excute(String url, Callback callback){
        Request request = buildRequest(url);
        excute(request,callback);
    }

    public static void excute(Request request,Callback callback){
        AppManager.getHttpClient().newCall(request).enqueue(callback);
    }

}
