package com.example.vhiphop.api;

import com.example.vhiphop.model.Channel;

/*
 *作者：created by 影子 on 2020/4/26 14:37
 *邮箱：1723928492@qq.com
 */
public abstract class BaseSiteApi {
   public abstract void onGetChannelAlbums(Channel channel,int pageNo,int pageSize,onGetChannelAlbumListener listener);
}
