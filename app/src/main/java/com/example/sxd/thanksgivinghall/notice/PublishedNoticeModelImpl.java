package com.example.sxd.thanksgivinghall.notice;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.ToDoNotifyListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class PublishedNoticeModelImpl implements PublishedNoticeContract.Model{
    String baseUrl = "";

    public PublishedNoticeModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String userId, final ResultListener<ToDoNotifyListEntity> result) {
        Call<ToDoNotifyListEntity> call = AppMainService.getNotifyService(baseUrl).sendList(userId);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<ToDoNotifyListEntity>() {
            @Override
            public void onResponse(Call<ToDoNotifyListEntity> call, Response<ToDoNotifyListEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<ToDoNotifyListEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }


}
