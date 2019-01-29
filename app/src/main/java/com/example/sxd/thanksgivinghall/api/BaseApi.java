package com.example.sxd.thanksgivinghall.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/3/12.
 */

public class BaseApi {
    private static Retrofit mRetrofit;
    public static Retrofit retrofit(String paramString)
    {
        mRetrofit = new Retrofit.Builder().baseUrl(paramString)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return ((Retrofit)mRetrofit);
    }
}