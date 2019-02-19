package com.example.sxd.thanksgivinghall.PurReceipt;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.PurReceiptDetailEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class PurReceiptDetailModelImpl implements PurReceiptDetailContract.Model{
    String baseUrl = "";

    public PurReceiptDetailModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String id, final ResultListener<PurReceiptDetailEntity> result) {
        Call<PurReceiptDetailEntity> call = AppMainService.getpurReceiptService(baseUrl).purReceiptDetails(id);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<PurReceiptDetailEntity>() {
            @Override
            public void onResponse(Call<PurReceiptDetailEntity> call, Response<PurReceiptDetailEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<PurReceiptDetailEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }


}
