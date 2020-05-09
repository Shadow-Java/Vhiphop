package com.example.vhiphop.model;

import com.example.vhiphop.AppManager;
import com.google.gson.annotations.Expose;

/*
 *作者：created by 影子 on 2020/4/27 21:38
 *邮箱：1723928492@qq.com
 */
public class EorrorInfo {

    public static final int ERROR_TYPE_HTTP = 1;//http的问题
    public static final int ERROR_TYPE_URL = 2;
    public static final int ERROR_TYPE_FATAL = 3;
    public static final int ERROR_TYPE_DATA_CONVERT = 4;
    public static final int ERROR_TYPE_PASE_JSON = 5;

    @Expose //区分实体中不想被序列化的属性，自身包含序列化及反序列化
    int type;//错误类型
    @Expose
    String tag;//标签

    @Expose
    String functionName;//功能

    @Expose
    String className;//类名

    @Expose
    Site site;//网站

    @Expose
    String reason;//错误原因

    @Expose
    String exceptionString;//异常信息

    @Expose
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public EorrorInfo(int siteId, int type){
        site = new Site(siteId);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getExceptionString() {
        return exceptionString;
    }

    public void setExceptionString(String exceptionString) {
        this.exceptionString = exceptionString;
    }
}
