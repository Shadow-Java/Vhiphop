package com.example.vhiphop;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.os.Handler;
import android.os.Message;

/**
  *@author created by 影子 on 2020/2/23
  *@email liyuanbo2020@163.com 
  */
public class SpalshActivity extends Activity {
    private SharedPreferences mSharedPreference;
    private static final int GO_HOME=1;
    private static final int GO_GUIDE=2;//引导页
    private static final int ENTER_DURATION=1000;//延时2s
    private Handler mHandler=new Handler(){
        //进入引导页发一个两秒的延时，加载页面
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GO_GUIDE:
                    startGuideActivity();
                    break;
                case GO_HOME:
                    startHomeActivity();
                    break;
                default:
                        break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreference=getSharedPreferences("config",MODE_PRIVATE);//读配置文件
        init();//初始化
    }
    private void init(){
        boolean isFirstIn=mSharedPreference.getBoolean("mIsFirstIn",true);//如果首次登录进，进入引导页
        if(isFirstIn){
            mHandler.sendEmptyMessageDelayed(GO_GUIDE,ENTER_DURATION);//延时2秒发送GO_GUIDE给handler
        }else{
            mHandler.sendEmptyMessageDelayed(GO_HOME,ENTER_DURATION);
        }
    }

    private void startHomeActivity(){
        Intent intent=new Intent(SpalshActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void startGuideActivity(){
        //Intent intent=new Intent(SpalshActivity.this,GuideActivity.class);
        Intent intent=new Intent();
        intent.setClass(getApplicationContext(),GuideActivity.class);//ctrl+Q看文档
        startActivity(intent);
        finish();//结束当前页面
    }
}
