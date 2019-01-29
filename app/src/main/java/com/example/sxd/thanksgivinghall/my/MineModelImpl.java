package com.example.sxd.thanksgivinghall.my;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.UserInfoEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class MineModelImpl implements MineContract.Model {
    String baseUrl = "";

    public MineModelImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    @Override
    public void userInfo(String userAccount, final ResultListener<UserInfoEntity> result) {
        Call<UserInfoEntity> call = AppMainService.getLoginService(baseUrl).userInfos(userAccount);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<UserInfoEntity>() {
            @Override
            public void onResponse(Call<UserInfoEntity> call, Response<UserInfoEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<UserInfoEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }
}
