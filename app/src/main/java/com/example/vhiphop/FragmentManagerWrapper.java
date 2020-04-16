package com.example.vhiphop;

import androidx.fragment.app.Fragment;

import java.util.HashMap;
/*
 *作者：created by Administrator on 2020/3/23 07:28
 *邮箱：1723928492@qq.com
 */
public class FragmentManagerWrapper {//实现单例模式 多线程
     private volatile static  FragmentManagerWrapper mInstance =null;//多线程

     public static FragmentManagerWrapper getInstance(){
         if(mInstance == null){
             synchronized (FragmentManagerWrapper.class){
                 if(mInstance == null){
                     mInstance = new FragmentManagerWrapper();
                 }
             }
         }
         return mInstance;
     }

     private HashMap<String,Fragment> mHashMap=new HashMap<>();

    public Fragment createFrament(Class<?> clazz){
        return createFrament(clazz,true);
    }
     public Fragment createFrament(Class<?> clazz,boolean isobtain){
         Fragment fragment = null;
         String className = clazz.getName();
         if(mHashMap.containsKey(className)) {
             fragment = mHashMap.get(className);
         }else{
             try {
                 fragment = (Fragment) Class.forName(className).newInstance();
             } catch (IllegalAccessException e) {
                 e.printStackTrace();
             } catch (InstantiationException e) {
                 e.printStackTrace();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }
         }
         if(isobtain){
             mHashMap.put(className,fragment);
         }

         return fragment;
     }
}
