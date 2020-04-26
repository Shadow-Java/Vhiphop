package com.example.vhiphop;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vhiphop.base.BaseFragment;
import com.example.vhiphop.widget.PullLoadRecyclerView;

/*
 *作者：created by 影子 on 2020/4/20 22:16
 *邮箱：1723928492@qq.com
 */
public class DetailListFragment extends BaseFragment {
    private static int mSiteId;
    private static int mChannelId;
    private static final String CHANNEL_ID = "channelid";
    private static final String SITE_ID = "siteid";
    private PullLoadRecyclerView mRecyclerView;

//    public DetailListFragment(int siteId,int channId){//SiteId是搜狐还是乐视 channId是电影还是电视剧
//        mSiteId = siteId;
//        mChannelId = channId;
//    }
    public DetailListFragment(){//需要空的构造 才不会出错
    }

    public static Fragment newInstance(int siteId,int channId){
        DetailListFragment fragment = new DetailListFragment();
        mSiteId = siteId;
        mChannelId = channId;
        Bundle bundle = new Bundle();//把值传入fragment里面去 bundle intent传值
        bundle.putInt(SITE_ID,siteId);//把值放入bundle
        bundle.putInt(CHANNEL_ID,channId);
        fragment.setArguments(bundle);//如： 乐视，电视剧页面，fragment需要知道
        return fragment;
    }

    @Override
    protected void initView() {
        mRecyclerView = bindViewId(R.id.pullloadRecyclerView);//拥有刷新和上拉加载更多的特点
        mRecyclerView.setGridLayout(3);//设置3列
        mRecyclerView.setAdapter(new DetailListAdapter());
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
    }

    class PullLoadMoreListener implements PullLoadRecyclerView.OnPullLoadMoreListener {

        @Override
        public void reRresh() {

        }

        @Override
        public void loadMore() {

        }
    }

    class DetailListAdapter extends RecyclerView.Adapter{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detaillist;
    }

    @Override
    protected void initData() {

    }
}
