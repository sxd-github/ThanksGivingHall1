package com.example.sxd.thanksgivinghall.task;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.TaskDetailEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/3/13.
 */

public class PublishedTaskDetailModelImpl implements PublishedTaskDetailContract.Model{
    String baseUrl = "";

    public PublishedTaskDetailModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String taskId, final ResultListener<TaskDetailEntity> result) {
        Call<TaskDetailEntity> call = AppMainService.getTaskService(baseUrl).taskDetail(taskId);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<TaskDetailEntity>() {
            @Override
            public void onResponse(Call<TaskDetailEntity> call, Response<TaskDetailEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<TaskDetailEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }
}
