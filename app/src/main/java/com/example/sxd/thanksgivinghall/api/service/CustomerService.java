package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.CustomerDetailEntity;
import com.example.sxd.thanksgivinghall.bean.CustomerListEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sxd on 2018/2/2.
 */

public abstract interface CustomerService {

/*
    *//**
     * 新增客户信息
     * @param
     * @return
     */
    @POST("infc/infcAddCustomer/addCustomer")
    Call<Base> addCustomer(@Body RequestBody Body);


    /**
     * 获取当前用户接收的通知通告列表
     * @param userId  当前用户id
     * @return
     */
    @GET("infc/infcCustomer/customerList")
    Call<CustomerListEntity> customerList(@Query("userId") String userId);

    /**
     * 获取客户详情
     * @param customerId  通知通告id
     * @return
     */
    @GET("infc/infcCustomer/customerDetail")
    Call<CustomerDetailEntity> customerDetail(@Query("customerId") String customerId);


    /**
     * 获取当前用户发布的通知通告列表
     * @param userId  当前用户id
     * @return
     *//*
    @GET("infc/infcOaNotify/publishNotifyList")
    Call<SupplierListEntity> sendList(@Query("userId") String userId);*/
}
