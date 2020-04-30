package com.example.vhiphop.api;

import com.example.vhiphop.AppManager;
import com.example.vhiphop.model.Album;
import com.example.vhiphop.model.AlbumList;
import com.example.vhiphop.model.Channel;
import com.example.vhiphop.model.EorrorInfo;
import com.example.vhiphop.model.Site;
import com.example.vhiphop.model.sohu.Result;
import com.example.vhiphop.model.sohu.ResultAlbum;
import com.example.vhiphop.utils.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 *作者：created by 影子 on 2020/4/26 14:40
 *邮箱：1723928492@qq.com
 */
public class SohuApi extends BaseSiteApi {
    private static final String TAG = SohuApi.class.getSimpleName();
    private static final int SOHU_CHANNELID_MOVIE = 1;//搜狐电影频道ID
    private static final int SOHU_CHANNELID_SERIES = 2;//搜狐电视剧频道ID
    private static final int SOHU_CHANNELID_VERIETY = 7;//搜狐综艺频道ID
    private static final int SOHU_CHANNELID_DOCUMENTRY = 8;//搜狐纪录片频道ID
    private static final int SOHU_CHANNELID_COMIC = 16;//搜狐动漫频道ID
    private static final int SOHU_CHANNELID_MUSIC = 24;//搜狐音乐频道ID


    private final static String API_CHANNEL_ALBUM_FORMAT = "http://api.tv.sohu.com/v4/search/channel.json"+"?cid=%s&o=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&"+
            "sver=6.2.0&sysver=4.4.2&partner=47&page=%s&page_size=%s";
    @Override
   public void onGetChannelAlbums(Channel channel, int pageNo, int pageSize, onGetChannelAlbumListener listener) {
        String url = getChannelAlbumUrl(channel,pageNo,pageSize);
        doGetChannelAlbumsByUrl(url,listener);
    }

    public void doGetChannelAlbumsByUrl(final String url,onGetChannelAlbumListener listener){
        //TODO 网络请求
        OkHttpUtils.excute(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(listener != null){
                    EorrorInfo info = buildErrorInfo(url,"doGetChannelAlbumsByUrl",e,EorrorInfo.ERROR_TYPE_URL);//还没响应就直接失败了
                    listener.onGetChannelAlbumFailed(info);
                }

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {//开始请求网络请求相关
                if(!response.isSuccessful()){
                    EorrorInfo info = buildErrorInfo(url,"doGetChannelAlbumsByUrl",null,EorrorInfo.ERROR_TYPE_HTTP);//http的错误类型
                    listener.onGetChannelAlbumFailed(info);
                    return;
                }
                //1. 取到数据映射Result
                //2. 转换ResultAlbum变成Album
                //3.Album存到AlbumList中
                Result result = AppManager.getGson().fromJson(response.body().string(), Result.class);//将response映射到resultAlbum中去
                AlbumList albumList = toConvertAlbumList(result);//转换字段
                if(albumList != null){
                    if(albumList.size() > 0 && listener != null){
                        listener.onGetChannelAlbumSuccess(albumList);
                    }
                }else {//如果为空
                    EorrorInfo info = buildErrorInfo(url,"doGetChannelAlbumsByUrl",null,EorrorInfo.ERROR_TYPE_DATA_CONVERT);//http的错误类型
                    listener.onGetChannelAlbumFailed(info);
                }
            }
        });
    }

    private AlbumList toConvertAlbumList(Result result){
        if(result.getData().getResultAlbumList().size()>0){
            AlbumList albumList = new AlbumList();
            for(ResultAlbum resultAlbum : result.getData().getResultAlbumList()){
                Album album = new Album(Site.SOHU,AppManager.getContext());
                album.setAlbumDesc(resultAlbum.getTvDesc());
                album.setAlbumId(resultAlbum.getAlbumId());
                album.setHorImgUrl(resultAlbum.getHorHighPic());
                album.setMainActor(resultAlbum.getMainActor());
                album.setTip(resultAlbum.getTip());
                album.setTitle(resultAlbum.getAlbumName());
                album.setVerImgUrl(resultAlbum.getVerHighPic());
                album.setDirector(resultAlbum.getDirector());
                albumList.add(album);
            }
            return albumList;
        }
        return null;
    }

    private EorrorInfo buildErrorInfo(String url,String functionName,Exception e,int type){
        EorrorInfo info = new EorrorInfo(Site.SOHU,type);
        info.setExceptionString(e.getMessage());
        info.setFunctionName(functionName);
        info.setUrl(url);
        info.setTag(TAG);
        info.setClassName(TAG);
        return info;
    }

    private String getChannelAlbumUrl(Channel channel, int pageNo, int pageSize){//格式化URL
        return String.format(API_CHANNEL_ALBUM_FORMAT,toConvertChannelId(channel),pageNo,pageSize);
    }

    private int toConvertChannelId(Channel channel){
        int channelId = -1;//无效值
        switch (channel.getChannelId()){
            case Channel.SHOW:
                channelId = SOHU_CHANNELID_SERIES;
                break;
            case Channel.MOVIE:
                channelId = SOHU_CHANNELID_MOVIE;
                break;
            case Channel.COMIC:
                channelId = SOHU_CHANNELID_COMIC;
                break;
            case Channel.MUSIC:
                channelId = SOHU_CHANNELID_MUSIC;
                break;
            case Channel.DOCUMENTRY:
                channelId = SOHU_CHANNELID_DOCUMENTRY;
                break;
            case Channel.VARIETY:
                channelId = SOHU_CHANNELID_VERIETY;
                break;
        }
        return channelId;
    }

}
