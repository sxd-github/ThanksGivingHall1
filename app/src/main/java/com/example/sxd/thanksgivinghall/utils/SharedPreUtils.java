package com.example.sxd.thanksgivinghall.utils;

import android.content.Context;

/**
 * Created by Administrator on 2016/4/23.
 */

//SharedPreferences能够轻松的存放数据和读取数据，但只能保存简单的数据类型，例如：String/int等。
// 一般将复杂类型的数据转化成Base64编码，然后将转换后的数据以字符串的形式保存在XML文件中，再用SharedPreferences保存
public class SharedPreUtils {

    //context.getSharedPreferences(),打开Preferences,存储key-value的文件的名称为“config”,如果存在打开，负责创建新的preferences
    //put存放数据，get读取数据
    public static void putBoolean(Context context, String key, boolean value){
        context.getSharedPreferences("config", Context.MODE_PRIVATE).edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key){
        return context.getSharedPreferences("config", Context.MODE_PRIVATE).getBoolean(key, false);
    }

    public static void putString(Context context, String key, String value) {
        context.getSharedPreferences("config", Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key){
        return context.getSharedPreferences("config", Context.MODE_PRIVATE).getString(key, "");
    }
}
