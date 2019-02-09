package com.example.sxd.thanksgivinghall.supplier;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.SupplierListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class SupplierModelImpl implements SupplierContract.Model{
    String baseUrl = "";

    public SupplierModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String userId, final ResultListener<SupplierListEntity> result) {
        Call<SupplierListEntity> call = AppMainService.getSupplierService(baseUrl).supplierList(userId);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<SupplierListEntity>() {
            @Override
            public void onResponse(Call<SupplierListEntity> call, Response<SupplierListEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<SupplierListEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }


}
