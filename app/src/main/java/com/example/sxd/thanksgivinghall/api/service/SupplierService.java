package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.SupplierEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoNotifyDetailEntity;
import com.example.sxd.thanksgivinghall.bean.SupplierListEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sxd on 2018/2/2.
 */

public abstract interface SupplierService {

/*
    *//**
     * 新增供应商信息
     * @param
     * @return
     */
    @POST("infc/infcAddSupplier/addSupplier")
    Call<Base> addSupplier(@Body RequestBody Body);


    /**
     * 获取当前用户接收的通知通告列表
     * @param userId  当前用户id
     * @return
     */
    @GET("infc/infcSupplier/supplierList")
    Call<SupplierListEntity> supplierList(@Query("userId") String userId);

    /**
     * 获取供应商详情
     * @param supplierId  通知通告id
     * @return
     */
    @GET("infc/infcSupplier/supplierDetails")
    Call<SupplierEntity> supplierDetails(@Query("supplierId") String supplierId);


    /**
     * 获取当前用户发布的通知通告列表
     * @param userId  当前用户id
     * @return
     *//*
    @GET("infc/infcOaNotify/publishNotifyList")
    Call<SupplierListEntity> sendList(@Query("userId") String userId);*/
}
