package com.example.vhiphop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.Gravity;
import android.view.MenuItem;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import com.example.vhiphop.base.BaseActivity;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends BaseActivity {
    private Drawable drawable;
    private TextView textView;
    private MenuItem mPreItem;
    private DrawerLayout mDrawableLayout;;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private androidx.appcompat.widget.Toolbar mToolBar;
    private FragmentManager mFragmentManger;
    private Fragment mCurrentFragment;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        //setSupportActionBar();//设置actionBar
        //setActionBarIcon(R.drawable.ic_drawer_home);//设置图标
        //mToolBar.setTitle(R.string.home_title);
        mToolBar=bindViewId(R.drawable.ic_drawer_home);
        setTitle("首页");
        mDrawableLayout=bindViewId(R.id.drawer_layout);//整个页面
        mNavigationView=bindViewId(R.id.navigation_view);//侧拉菜单
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawableLayout,mToolBar,R.string.drawer_open,R.string.drawer_close);
        mActionBarDrawerToggle.syncState();//同步状态
        mDrawableLayout.addDrawerListener(mActionBarDrawerToggle);//mActionBarDrawerToggle能控制开关，md能控制弹入弹出显示

        mPreItem=mNavigationView.getMenu().getItem(0);
        mPreItem.setCheckable(true);
        initFragment();
        handleNavigationViewItem();
    }
    private void initFragment(){
        mFragmentManger = getSupportFragmentManager();
        mCurrentFragment=FragmentManagerWrapper.getInstance().createFrament(HomeFragment.class,true);
        mFragmentManger.beginTransaction().add(R.id.fl_main_content,mCurrentFragment).commit();//事务回滚
//        mFragmentManger.beginTransaction().commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mDrawableLayout.isDrawerOpen(mNavigationView))
            mDrawableLayout.closeDrawer(mNavigationView);
        else
            mDrawableLayout.openDrawer(mNavigationView);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        //这是手动控制
    }

    private void handleNavigationViewItem(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(mPreItem != null){
                    mPreItem.setCheckable(false);
                }
                mToolBar=bindViewId(R.drawable.ic_drawer_home);
                switch(menuItem.getItemId()){
                    case R.id.navigation_item_video:
                        switchFragement(HomeFragment.class);
                        setTitle("首页");
                        //mToolBar.setTitle(R.string.home_title);
                        break;
                    case R.id.navigation_item_blog:
                        switchFragement(BlogFragment.class);
                        setTitle("我的博客");
                        break;
                    case R.id.navigation_item_about:
                        switchFragement(AboutFragment.class);
                        //mToolBar.setTitle(R.string.about_title);
                        setTitle("关于Vhiphop");
                        break;
                }
                mDrawableLayout.closeDrawer(Gravity.LEFT);
                mPreItem = menuItem;
                return false;
            }
        });
    }

    private void switchFragement(Class<?> clazz) {
       Fragment fragment= FragmentManagerWrapper.getInstance().createFrament(clazz);
        if(fragment.isAdded()){
            mFragmentManger.beginTransaction().hide(mCurrentFragment).show(fragment).commit();//事务操作
        }else{
            mFragmentManger.beginTransaction().hide(mCurrentFragment).add(R.id.fl_main_content,fragment).commitAllowingStateLoss();//当前全部提交
        }
        mCurrentFragment = fragment;
    }

    @Override
    protected void initData() {
        //TODO
    }
}
