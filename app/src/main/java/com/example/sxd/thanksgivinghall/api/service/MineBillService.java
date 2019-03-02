package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.CustomerOrderDetailEntity;
import com.example.sxd.thanksgivinghall.bean.CustomerOrderListEntity;
import com.example.sxd.thanksgivinghall.bean.MineBillListEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sxd on 2018/2/2.
 */

public abstract interface MineBillService {

/*
    *//**
     * 新增订单信息
     * @param
     * @return
     */
//    @POST("infc/infcAddOrderCustomer/addOrderCustomer")
//    Call<Base> addOrderCustomer(@Body RequestBody Body);


    /**
     * 获取当前总客户账单
     * @param userId  当前用户id
     * @return
     */
//    @GET("infc/infcCustomerOrder/customerOrderList")
//    Call<CustomerOrderListEntity> customerOrderList(@Query("userId") String userId);

    /**
     * 获取订单详情
     * @param customerOrderId  通知通告id
     * @return
     */
//    @GET("infc/infcCustomerOrder/customerOrderDetail")
//    Call<CustomerOrderDetailEntity> customerOrderDetail(@Query("customerOrderId") String customerOrderId);

    /**
     * 获取账单
     * @param customerOrderId
     * @return
     */
    @GET("infc/infcMineBill/MineBill")
    Call<MineBillListEntity> MineBill(@Query("customerOrderId") String customerOrderId,@Query("dateTextRe")String dateTextRe);


    /**
     * 获取当前用户发布的通知通告列表
     * @param userId  当前用户id
     * @return
     *//*
    @GET("infc/infcOaNotify/publishNotifyList")
    Call<SupplierListEntity> sendList(@Query("userId") String userId);*/
}
