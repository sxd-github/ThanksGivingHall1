package com.example.sxd.thanksgivinghall.customer;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.CustomerDetailEntity;
import com.example.sxd.thanksgivinghall.customer.CustomerModelImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class CustomerDetailModelImpl implements CustomerDetailContract.Model{
    String baseUrl = "";

    public CustomerDetailModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String id, final ResultListener<CustomerDetailEntity> result) {
        Call<CustomerDetailEntity> call = AppMainService.getCustomerService(baseUrl).customerDetail(id);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<CustomerDetailEntity>() {
            @Override
            public void onResponse(Call<CustomerDetailEntity> call, Response<CustomerDetailEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<CustomerDetailEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }


}
