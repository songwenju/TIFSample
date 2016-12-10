##TIFSample


###一.什么是TIF？
TIF（TV input Framework）是Google向电视制造商提供的一套关于TV相关数据（搜到的台，直播的节目单等）管理的一套框架和对应的API，用于创建Input模块来控制Android电视。   
不同的Input模块包括HDMI，ATV，DTV，网络等。这里是使用网络对应的Input模块，即通常说的IPTv）来展示TIF的相关内容。

###二.项目运行图：

![image](https://github.com/songwenju/TIFSample/blob/master/raw/show.gif)
  
###三.使用方法：
   要求运行在Android 5.0以上的系统，因为TIF在5.0之后提供的，最好是电视。  
   1.运行module tifService，点击加载数据，会写入数据到/data/data/com.android.providers/tv/databases/tv.db 的channels表中  
   2.运行app模块，点击播放第一个或第二个按钮会播放相应的视频。     

   在运行的时候可能播放不成功，这个时候要到tv.db数据库里看一下id，修改一下即可。  


#####更多关于TIF的介绍请看下面这篇博客：http://www.jianshu.com/p/385c92fceb16  