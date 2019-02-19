package com.example.sxd.thanksgivinghall.customer;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.CustomerListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class CustomerModelImpl implements CustomerContract.Model{
    String baseUrl = "";

    public CustomerModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String userId, final ResultListener<CustomerListEntity> result) {
        Call<CustomerListEntity> call = AppMainService.getCustomerService(baseUrl).customerList(userId);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<CustomerListEntity>() {
            @Override
            public void onResponse(Call<CustomerListEntity> call, Response<CustomerListEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<CustomerListEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }


}
