package com.example.sxd.thanksgivinghall.CustomerOrder;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.CustomerOrderListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class CustomerOrderModelImpl implements CustomerOrderContract.Model{
    String baseUrl = "";

    public CustomerOrderModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String userId, final ResultListener<CustomerOrderListEntity> result) {
        Call<CustomerOrderListEntity> call = AppMainService.getCustomerOrderService(baseUrl).customerOrderList(userId);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<CustomerOrderListEntity>() {
            @Override
            public void onResponse(Call<CustomerOrderListEntity> call, Response<CustomerOrderListEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<CustomerOrderListEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }


}
