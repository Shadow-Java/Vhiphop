<h1 align="center">安卓点播视频APP</h1>

## 前言
本项目是从B站UP主学习所得，主要用于提供学友源码参考，共同学习共同进步！  
我跟小伙伴一样通过视频学习自己敲的代码，觉得帮助到你的可以点个**star**

**学习来源**：
[树`郗](https://www.bilibili.com/video/BV1Hb411T742?t=6854&p=4)

## 非功能需求：
+ 工具：[Android Studio](https://developer.android.google.cn/studio/)
+ 模拟器：[Genymotion](http://download.canadiancontent.net/Genymotion.html)
+ 语言：Java
##  首页图：
<img align="center" src="https://picturestr.oss-cn-shanghai.aliyuncs.com/img/20200421100213.png"/><br>

+ 首页知识点：


## 侧滑图：
<img align="center" src="https://picturestr.oss-cn-shanghai.aliyuncs.com/img/20200421094535.png"/>

+ 侧滑知识点：

## 详情页面：

1.自定义RecyclerView下拉刷新
  SwipeRefreshLayout,下拉刷新
  是一个Google原生的控件，属于android.support.v4.widget包下
2.自定义RecyclerView上拉加载更多
  Inflate一个View，在加载更多时，加载完成后消失


3.数据Model
+ 序列化的方法
+ 序列化原则
  + 在使用内存的时候，Parcelable比Serializable性能高，保存对象的字节序列到本地文件中
  + Serializable在序列化的时候会产生大量的临时变量，从而引起频繁的GC。
+ Parcelable接口
  + 优点：
    + 永久性保存对象，保存对象的字节序列到本地文件中；
    + 通过序列化对象在网络中传递对象；
    + 通过序列化在进程间传递对象

4.sohu数据Model

