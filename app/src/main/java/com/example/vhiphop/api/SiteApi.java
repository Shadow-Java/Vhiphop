package com.example.vhiphop.api;

import android.content.Context;

import com.example.vhiphop.model.Channel;
import com.example.vhiphop.model.Site;

/*
 *作者：created by 影子 on 2020/4/26 14:41
 *邮箱：1723928492@qq.com
 */
public class SiteApi {//组合LetvApi和SohuApi
    public static void onGetChannelAlbums(Context context,int pageNo, int pageSize, int siteId, int channelId, onGetChannelAlbumListener listener){
        switch (siteId){
            case Site.LETV:
                new LetApi().onGetChannelAlbums(new Channel(channelId,context),pageNo,pageSize,listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetChannelAlbums(new Channel(channelId,context),pageNo,pageSize,listener);
                break;
        }

    }
}
