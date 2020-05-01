package com.example.vhiphop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.vhiphop.base.BaseActivity;
import com.example.vhiphop.model.Channel;
import com.example.vhiphop.model.Site;

import java.util.HashMap;

/*
 *作者：created by Administrator on 2020/4/20 20:59
 *邮箱：1723928492@qq.com
 */
public class DetailListActivity extends BaseActivity {
    private static final String CHANNEL_ID = "channid";
    private int mChannId;
    private ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_list;
    }

    @Override
    protected void initView() {//channid传入LaunchDetailListActivity initview接收
        Intent intent = getIntent();//get获取当前页面的intent
        if(intent != null){
            mChannId = intent.getIntExtra(CHANNEL_ID,0);
        }
        Channel channel = new Channel(mChannId,this);
        String titleName = channel.getChannelName();

        setSupportActionBar();//表示当前页面支持ActionBar
        setSupportArrowActionBar(true);//当前的箭头
        setTitle(titleName);

        //mToolBar.setTitle(titleName);
        mViewPager = bindViewId(R.id.pager);
        mViewPager.setAdapter(new SitePagerAdapter(getSupportFragmentManager(),this,mChannId));
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//处理左上角返回箭头
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {

    }
    private class SitePagerAdapter extends FragmentPagerAdapter{
        private Context mContext;
        private int mChannelID;
        private HashMap<Integer,DetailListFragment> mPagerMap;

        public SitePagerAdapter(FragmentManager fm,Context context,int channId){
            super(fm);
            mContext = context;
            mChannelID = channId;
            mPagerMap = new HashMap<>();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Object obj = super.instantiateItem(container,position);
            if(obj instanceof DetailListFragment){//obJ是否是DetailListFragment
                mPagerMap.put(position,(DetailListFragment)obj);
            }
            return obj;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
            mPagerMap.remove(position);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = DetailListFragment.newInstance(position + 1,mChannelID);
            return fragment;
        }

        @Override
        public int getCount() {
            return Site.MAX_SITE;
        }
    }

    public static void LaunchDetailListActivity(Context context,int channelId){
        Intent intent=new Intent(context,DetailListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(CHANNEL_ID,channelId);
        context.startActivity(intent);//外面传入只能context.start

    }
}
