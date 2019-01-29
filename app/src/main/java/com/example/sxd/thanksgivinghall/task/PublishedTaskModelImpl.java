package com.example.sxd.thanksgivinghall.task;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.TaskListEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoTaskListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class PublishedTaskModelImpl implements PublishedTaskContract.Model{
    String baseUrl = "";

    public PublishedTaskModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String userid, final ResultListener<TaskListEntity> result) {
        Call<TaskListEntity> call = AppMainService.getTaskService(baseUrl).selfList(userid);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<TaskListEntity>() {
            @Override
            public void onResponse(Call<TaskListEntity> call, Response<TaskListEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<TaskListEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }


}
