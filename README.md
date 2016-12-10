##TIFSample
效果图：

![image](https://github.com/songwenju/TIFSample/blob/master/raw/show.gif)

这是一个关于TIF（TV input Framework）的使用例子。TIF是Google向电视制造商提供了  
一套标准的API，用于创建Input模块来控制Android电视。不同的Input模块包括HDMI，ATV，  
DTV，网络等。这个例子是使用网络对应的Input模块，也就是通常所说的IPTv。  


视频源：
https://storage.googleapis.com/android-tv/android_tv_videos_new.json   

使用方法：
要求运行在Android 5.0以上的系统，最好是电视。  
1.运行module tifService，点击加载数据，会写入数据到/data/data/com.android.providers/tv/databases/tv.db 的channels表中  
2.运行app模块，点击播放第一个或第二个按钮会播放相应的视频。     


对应的博客地址：http://www.jianshu.com/p/385c92fceb16  