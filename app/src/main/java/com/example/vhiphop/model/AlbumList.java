package com.example.vhiphop.model;

import android.util.Log;

import java.util.ArrayList;

/*
 *作者：created by 影子 on 2020/4/27 20:23
 *邮箱：1723928492@qq.com
 */
public class AlbumList extends ArrayList<Album> {
    private static final String TAG = AlbumList.class.getSimpleName();
    public void debug(){
        for(Album a : this){
            Log.d(TAG,">> albumlist" + a.toString());
        }

    }
}
