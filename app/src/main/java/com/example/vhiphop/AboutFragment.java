package com.example.vhiphop;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import com.example.vhiphop.base.BaseFragment;

/*
 *作者：created by Administrator on 2020/3/20 23:06
 *邮箱：1723928492@qq.com
 */
public class AboutFragment extends BaseFragment {
    @Override
    protected void initView() {
       TextView textView = bindViewId(R.id.tv_app_description);
       textView.setAutoLinkMask(Linkify.ALL);//表示文字中有链接可点
       textView.setMovementMethod(LinkMovementMethod.getInstance());//表示文字可以滚动
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initData() {

    }
}
