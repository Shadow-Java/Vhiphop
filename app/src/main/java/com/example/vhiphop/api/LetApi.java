package com.example.vhiphop.api;

import com.example.vhiphop.model.Album;
import com.example.vhiphop.model.AlbumList;
import com.example.vhiphop.model.Channel;
import com.example.vhiphop.model.EorrorInfo;
import com.example.vhiphop.model.Site;
import com.example.vhiphop.utils.OkHttpUtils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 *作者：created by 影子 on 2020/4/26 14:39
 *邮箱：1723928492@qq.com
 */
public class LetApi extends BaseSiteApi {

    private static final String TAG = LetApi.class.getSimpleName();
    private static final int LETV_CHANNELID_MOVIE = 1;//乐视电影频道ID
    private static final int LETV_CHANNELID_SERIES = 2;//乐视电视剧频道ID
    private static final int LETV_CHANNELID_VERIETY = 11;//乐视综艺频道ID
    private static final int LETV_CHANNELID_DOCUMENTRY = 16;//乐视纪录片频道ID
    private static final int LETV_CHANNELID_COMIC = 5;//乐视动漫频道ID
    private static final int LETV_CHANNELID_MUSIC = 9;//乐视音乐频道ID
    //http://static.meizi.app.m.letv.com/android/mod/mob/ctl/listalbum/act/index/src/1/cg/2/or/20/vt/180001/ph/420003,420004/pt/-141003/pn/1/ps/30/pcode/010110263/version/5.6.2.mindex.html
    private final static String ALBUM_LIST_URL_FORMAT="http://static.meizi.app.m.letv.com/android/" +
            "mod/mob/ctl/listalbum/act/index/src/1/cg/%s/ph/420003,420004/pn/%s/ps/%s/pcode/010110263/version/5.6.2.mindex.html";
    private final static String ALBUM_LIST_URL_DOCUMENTARY_FORMAT="http://static.meizi.app.m.letv.com/android/" +
            "mod/mob/ctl/listalbum/act/index/src/1/cg/%s/or/3/ph/420003,420004/ph/%s/ps/%s/pcode/"+"010110263/version/5.6.2.mindex.html";//true
    private final static String ALBUM_LIST_URL_SHOW_FORMAT="http://static.meizi.app.m.letv.com/android/" +
            "mod/mob/ctl/listalbum/act/index/src/1/cg/%s/or/20/vt/180001/ph/420003,420004/pt/-141003/pn/%s/ps/%s/pcode/010110263/version/5.6.2.mindex.html";
    private final static String API_CHANNEL_ALBUM_FORMAT ="http://static.meizi.app.m.letv.com/android/mod/mob/ctl/listalbum/act/index/src/1/cg/2/or/20/vt/180001/ph/420003,420004/pt/-141003/pn/1/ps/30/pcode/010110263/version/5.6.2.mindex.html";

    @Override
    public void onGetChannelAlbums(Channel channel, int pageNo, int pageSize, onGetChannelAlbumListener listener) {
        String url = getChannelAlbumUrl(channel,pageNo,pageSize);
        doGetChannelAlbumByUrl(url,listener);
    }

    private String getChannelAlbumUrl(Channel channel,int pageNo,int pageSize){
        if(channel.getChannelId() == Channel.DOCUMENTRY){
            return String.format(ALBUM_LIST_URL_DOCUMENTARY_FORMAT,conVertChannelId(channel),pageNo,pageSize);
        }else if(channel.getChannelId() == Channel.DOCUMENTRY){
            return String.format(ALBUM_LIST_URL_SHOW_FORMAT,conVertChannelId(channel),pageNo,pageSize);
        }
        return String.format(ALBUM_LIST_URL_FORMAT,conVertChannelId(channel),pageNo,pageSize);
    }

//    private String getChannelAlbumUrl(Channel channel, int pageNo, int pageSize){//格式化URL
//        return String.format(API_CHANNEL_ALBUM_FORMAT,conVertChannelId(channel),pageNo,pageSize);
//    }

    private int conVertChannelId(Channel channel){
        int channelId = -1;//无效值
        switch (channel.getChannelId()){
            case Channel.SHOW:
                channelId = LETV_CHANNELID_SERIES;
                break;
            case Channel.MOVIE:
                channelId = LETV_CHANNELID_MOVIE;
                break;
            case Channel.COMIC:
                channelId = LETV_CHANNELID_COMIC;
                break;
            case Channel.MUSIC:
                channelId = LETV_CHANNELID_MUSIC;
                break;
            case Channel.DOCUMENTRY:
                channelId = LETV_CHANNELID_DOCUMENTRY;
                break;
            case Channel.VARIETY:
                channelId = LETV_CHANNELID_VERIETY;
                break;
        }
        return channelId;
    }

    private void doGetChannelAlbumByUrl(final String url,final onGetChannelAlbumListener listener){//通过okhttp请求url
        OkHttpUtils.excute(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(listener != null){
                    EorrorInfo info = buildErrorInfo(url,"doGetChannelAlbumsByUrl",e,EorrorInfo.ERROR_TYPE_URL);//还没响应就直接失败了
                    listener.onGetChannelAlbumFailed(info);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()){
                    EorrorInfo info = buildErrorInfo(url,"doGetChannelAlbumsByUrl",null,EorrorInfo.ERROR_TYPE_HTTP);//http的错误类型
                    listener.onGetChannelAlbumFailed(info);
                    return;
                }
                String json = response.body().toString();
                try {
                    JSONObject resultJson = new JSONObject(json);
                    JSONObject bodyJson = resultJson.optJSONObject("body");
                    if (bodyJson.optInt("album_count") > 0){
                        AlbumList list = new AlbumList();//list即每个li
                        JSONArray albumListJson = bodyJson.optJSONArray("album_list");//解析url的albumlist
                        for (int i = 0;i < albumListJson.length();i++){//遍历我们的数组 每个li
                            Album album = new Album(Site.LETV);
                            JSONObject albumJson = albumListJson.getJSONObject(i);
                            album.setAlbumId(albumJson.getString("aid"));//把url的li中的数据赋值到本地album
                            album.setAlbumDesc(albumJson.getString("subname"));
                            album.setTitle(albumJson.getString("name"));
                            album.setTip(albumJson.getString("subname"));
                            JSONObject jsonImage = albumJson.getJSONObject("images");
                            //读取【400*300】字符
                            String imageurl = StringEscapeUtils.unescapeJava(jsonImage.getString("400*300"));
                            album.setHorImgUrl(imageurl);
                            list.add(album);
                        }

                        if(list != null){
                            if(list.size() > 0 && listener != null){
                                listener.onGetChannelAlbumSuccess(list);
                            }
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    EorrorInfo info = buildErrorInfo(url,"doGetChannelAlbumsByUrl",null,EorrorInfo.ERROR_TYPE_PASE_JSON);//http的错误类型
                    listener.onGetChannelAlbumFailed(info);
                }
            }
        });
    }

    private EorrorInfo buildErrorInfo(String url,String functionName,Exception e,int type){
        EorrorInfo info = new EorrorInfo(Site.LETV,type);
        //info.setExceptionString(e.getMessage());
        info.setFunctionName(functionName);
        info.setUrl(url);
        info.setTag(TAG);
        info.setClassName(TAG);
        return info;
    }

}
