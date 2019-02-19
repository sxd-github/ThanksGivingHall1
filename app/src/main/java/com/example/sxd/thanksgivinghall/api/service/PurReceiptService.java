package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.PurReceiptDetailEntity;
import com.example.sxd.thanksgivinghall.bean.SupplierDetailEntity;
import com.example.sxd.thanksgivinghall.bean.PurReceiptListEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sxd on 2018/2/2.
 */

public abstract interface PurReceiptService {


    /**
     * 新增订单
     * @param
     * @return
     */
    @POST("infc/infcPurReceipt/addPurReceipt")
    Call<Base> addPurReceipt(@Body RequestBody Body);


    /**
     * 获取当前的订单列表
     * @param userId  当前用户id
     * @return
     */
    @GET("infc/infcPurReceipt/PurReceiptList")
    Call<PurReceiptListEntity> purReceiptList(@Query("userId") String userId);

    /**
     * 获取订单详情
     * @param supplierId  通知通告id
     * @return
     */
    @GET("infc/infcPurReceipt/PurReceiptDetails")
    Call<PurReceiptDetailEntity> purReceiptDetails(@Query("supplierId") String supplierId);


    /**
     * 获取当前用户发布的订单列表
     * @param userId  当前用户id
     * @return
     */
    @GET("infc/infcOaNotify/publishNotifyList")
    Call<SupplierDetailEntity> sendList(@Query("userId") String userId);
}
