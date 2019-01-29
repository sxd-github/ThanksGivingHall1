package com.example.sxd.thanksgivinghall.treelist.officer;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.OfficeUserEntity;
import com.example.sxd.thanksgivinghall.bean.UserEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class BasesModelImpl implements BasesContract.Model {
    String baseUrl = "";

    public BasesModelImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void officerInfos(final ResultListener<UserEntity> result) {
        Call<UserEntity> call = AppMainService.getOfficeUserService(baseUrl).getOfficer();
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }

}
