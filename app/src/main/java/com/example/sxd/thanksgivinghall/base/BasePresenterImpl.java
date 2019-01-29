package com.example.sxd.thanksgivinghall.base;
import android.content.Context;

import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;

/**
 * Created by Administrator on 2018/3/13.
 */
public class BasePresenterImpl
{
    public String getBaseUrl(Context paramContext)
    {
//        paramContext = SharedPreUtils.putString(paramContext,"SP_FILE_NAME",);
        return String.format("http://%s:%s/jnoa/a/", new Object[] { SharedPreUtils.getString(paramContext,"SP_IP"),  SharedPreUtils.getString(paramContext,"SP_PORT") });
    }
}