package com.example.vhiphop.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
 *作者：created by Administrator on 2020/3/17 16:27
 *邮箱：1723928492@qq.com
 */public abstract  class BaseFragment extends Fragment {

     private View mContentView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //mContentView = getActivity().getLayoutInflater().inflate(getLayoutId(),container);//有错误
        mContentView = getActivity().getLayoutInflater().inflate(getLayoutId(),container,false);
        initView();
        initData();
        return mContentView;
    }
    protected abstract void initView();
    protected abstract int getLayoutId();
    protected abstract void initData();
    protected <T extends View> T bindViewId(int resId){//泛型 任何类型
        return (T)mContentView.findViewById(resId);
    }
}
