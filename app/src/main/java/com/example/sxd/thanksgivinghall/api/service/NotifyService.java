package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.ToDoNotifyDetailEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoNotifyListEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sxd on 2018/2/2.
 */

public abstract interface NotifyService {


    /**
     * 新增通知通告
     * @param
     * @return
     */
    @POST("infc/infcAddOaNotify/addOaNotify")
    Call<Base> addOaNotify(@Body RequestBody Body);


    /**
     * 获取当前用户接收的通知通告列表
     * @param userId  当前用户id
     * @return
     */
    @GET("infc/infcOaNotify/selfList")
    Call<ToDoNotifyListEntity> selfList(@Query("userId") String userId);

    /**
     * 获取通知通告详情
     * @param notifyId  通知通告id
     * @return
     */
    @GET("infc/infcOaNotify/notifyDetails")
    Call<ToDoNotifyDetailEntity> notifyDetail(@Query("notifyId") String notifyId);


    /**
     * 获取当前用户发布的通知通告列表
     * @param userId  当前用户id
     * @return
     */
    @GET("infc/infcOaNotify/publishNotifyList")
    Call<ToDoNotifyListEntity> sendList(@Query("userId") String userId);
}
