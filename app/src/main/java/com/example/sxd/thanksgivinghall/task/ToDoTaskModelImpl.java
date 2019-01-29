package com.example.sxd.thanksgivinghall.task;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.ToDoTaskListEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class ToDoTaskModelImpl implements ToDoTaskContract.Model{
    String baseUrl = "";

    public ToDoTaskModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String userId, String completeFlag,final ResultListener<ToDoTaskListEntity> result) {
        Call<ToDoTaskListEntity> call = AppMainService.getTaskService(baseUrl).selfLReceList(userId,completeFlag);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<ToDoTaskListEntity>() {
            @Override
            public void onResponse(Call<ToDoTaskListEntity> call, Response<ToDoTaskListEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<ToDoTaskListEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }


}
