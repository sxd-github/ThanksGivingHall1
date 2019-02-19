package com.example.sxd.thanksgivinghall.CustomerOrder;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.CustomerOrderDetailEntity;
import com.example.sxd.thanksgivinghall.CustomerOrder.OrderDetailContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class OrderDetailModelImpl implements OrderDetailContract.Model{
    String baseUrl = "";

    public OrderDetailModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String id, final ResultListener<CustomerOrderDetailEntity> result) {
        Call<CustomerOrderDetailEntity> call = AppMainService.getCustomerOrderService(baseUrl).customerOrderDetail(id);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<CustomerOrderDetailEntity>() {
            @Override
            public void onResponse(Call<CustomerOrderDetailEntity> call, Response<CustomerOrderDetailEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<CustomerOrderDetailEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }


}
