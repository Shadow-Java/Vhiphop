package com.example.vhiphop.model.sohu;

import com.google.gson.annotations.Expose;

/*
 *作者：created by 影子 on 2020/4/28 21:07
 *邮箱：1723928492@qq.com
 */
/**
  *搜狐数据频道数据返回集
 */
public class Result {
     @Expose
     private long status;

     @Expose
     private String statusText;

     @Expose
     private Data data;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
