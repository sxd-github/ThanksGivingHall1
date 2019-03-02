package com.example.sxd.thanksgivinghall.mybill;

import com.example.sxd.thanksgivinghall.mybill.MineBillContract;
import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.MineBillListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class MineBillModelImpl implements MineBillContract.Model{
    String baseUrl = "";

    public MineBillModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String userId,String dateTextRe, final ResultListener<MineBillListEntity> result) {
        Call<MineBillListEntity> call = AppMainService.getMineBillService(baseUrl).MineBill(userId,dateTextRe);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<MineBillListEntity>() {
            @Override
            public void onResponse(Call<MineBillListEntity> call, Response<MineBillListEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<MineBillListEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }


}
