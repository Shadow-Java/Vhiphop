package com.example.vhiphop.model.sohu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 *作者：created by 影子 on 2020/4/28 21:12
 *邮箱：1723928492@qq.com
 */
public class Data {
    @Expose
    private int count;
    @Expose
    @SerializedName("videos")
    private List<ResultAlbum> resultAlbumList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResultAlbum> getResultAlbumList() {
        return resultAlbumList;
    }

    public void setResultAlbumList(List<ResultAlbum> resultAlbumList) {
        this.resultAlbumList = resultAlbumList;
    }
}
