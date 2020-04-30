package com.example.vhiphop.api;

import com.example.vhiphop.model.Album;
import com.example.vhiphop.model.AlbumList;
import com.example.vhiphop.model.EorrorInfo;

/*
 *作者：created by 影子 on 2020/4/26 14:48
 *邮箱：1723928492@qq.com
 */
public interface onGetChannelAlbumListener {
    void onGetChannelAlbumSuccess(AlbumList albumList);
    void onGetChannelAlbumFailed(EorrorInfo info);
}
