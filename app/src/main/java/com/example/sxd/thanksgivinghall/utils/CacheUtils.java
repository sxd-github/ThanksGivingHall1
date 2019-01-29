package com.example.sxd.thanksgivinghall.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 沈晓迪1 on 2017/3/2.
 * 作用：缓存工具类
 */

public class CacheUtils {
    /**
     * 保存参数
     * @param context
     * @param key
     * @param values
     */
    public static void putBoolean(Context context, String key, boolean values) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,values).commit();
    }


    /**
     * 得到保存的参数
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }
}
