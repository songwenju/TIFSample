package com.songwenju.tifsample;

import android.util.Log;

/**
 * Log工具类
 */
public class LogUtil {
    //控制项目是否显示log
    private static boolean isShowLog = true;
    private static String msign = "swj_itv_";

    public static void i(String tag, String msg){
        if (isShowLog){
            Log.i(msign+tag,msg);
        }
    }

    public static void i(Object tag, String msg){
        if (isShowLog){
            Log.i(msign+tag.getClass().getSimpleName(),msg);
        }
    }
    public static void d(String tag, String msg){
        if (isShowLog){
            Log.d(msign+tag, msg);
        }
    }

    public static void d(Object tag, String msg){
        if (isShowLog){
            Log.d(msign+tag.getClass().getSimpleName(), msg);
        }
    }
    public static void w(String tag, String msg){
        if (isShowLog){
            Log.w(msign+tag, msg);
        }
    }

    public static void w(Object tag, String msg){
        if (isShowLog){
            Log.w(msign+tag.getClass().getSimpleName(), msg);
        }
    }
    public static void e(String tag, String msg){
        if (isShowLog){
            Log.e(msign+tag, msg);
        }
    }

    public static void e(Object tag, String msg){
        if (isShowLog){
            Log.e(msign+tag.getClass().getSimpleName(), msg);
        }
    }
}
