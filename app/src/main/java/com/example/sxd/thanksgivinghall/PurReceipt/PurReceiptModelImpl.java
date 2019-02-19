package com.example.sxd.thanksgivinghall.PurReceipt;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.PurReceiptListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class PurReceiptModelImpl implements PurRecetptContract.Model{
    String baseUrl = "";

    public PurReceiptModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String userId, final ResultListener<PurReceiptListEntity> result) {
        Call<PurReceiptListEntity> call = AppMainService.getpurReceiptService(baseUrl).purReceiptList(userId);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<PurReceiptListEntity>() {
            @Override
            public void onResponse(Call<PurReceiptListEntity> call, Response<PurReceiptListEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<PurReceiptListEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }


}
