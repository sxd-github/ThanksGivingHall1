package com.example.sxd.thanksgivinghall.supplier;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.SupplierDetailEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class SupplierDetailModelImpl implements SupplierDetailContract.Model{
    String baseUrl = "";

    public SupplierDetailModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String id, final ResultListener<SupplierDetailEntity> result) {
        Call<SupplierDetailEntity> call = AppMainService.getsupplierService(baseUrl).supplierDetail(id);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<SupplierDetailEntity>() {
            @Override
            public void onResponse(Call<SupplierDetailEntity> call, Response<SupplierDetailEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<SupplierDetailEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }


}
