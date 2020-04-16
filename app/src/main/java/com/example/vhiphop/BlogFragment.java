package com.example.vhiphop;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.vhiphop.base.BaseFragment;

/*
 *作者：created by Administrator on 2020/3/20 23:04
 *邮箱：1723928492@qq.com
 */public class BlogFragment extends BaseFragment {
     private WebView mWebView;
     private ProgressBar mProgressBar;
     private static final int MAX_VALUE= 100;
    private static final String BLOG_URL = "http://liyuanbo.top/";
    @Override
    protected void initView() {
       mWebView = bindViewId(R.id.webview);
       mProgressBar = bindViewId(R.id.pb_progress);
       WebSettings settings = mWebView.getSettings();//用来设置webview
       settings.setBuiltInZoomControls(true);//支持放大缩小
       settings.setJavaScriptEnabled(true);//支持Javascript
        mProgressBar.setMax(MAX_VALUE);
        mWebView.loadUrl(BLOG_URL);//网络操作 需要申请网络权限 AndroidManifest
        mWebView.setWebChromeClient(mwebChromeClient);//设置监听
    }
    private WebChromeClient mwebChromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
            if(newProgress == MAX_VALUE){
               mProgressBar.setVisibility(View.GONE); //加载到100时 隐藏进度条
               //((ViewGroup) view.getParent()).removeView(view);
            }
            super.onProgressChanged(view, newProgress);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blog;
    }

    @Override
    protected void initData() {

    }
}
