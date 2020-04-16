package com.example.vhiphop.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;
//import androidx.appcompat.widget.Toolbar;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vhiphop.R;

/*
 *作者：created by Administrator on 2020/3/3 22:11
 *邮箱：1723928492@qq.com
 */public abstract class BaseActivity extends AppCompatActivity {
     protected Toolbar mToolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();

    protected <T extends View> T bindViewId(int resId){//泛型 任何类型
        return (T)findViewById(resId);
    }

    protected void setSupportActionBar(){
        mToolBar = bindViewId(R.id.toolbar);
        if(mToolBar != null){
            setActionBar(mToolBar);
        }
    }

    protected void setActionBarIcon(int resId){
        if(mToolBar != null){
            mToolBar.setNavigationIcon(resId);//minApi换成需要的21
        }
    }
}
