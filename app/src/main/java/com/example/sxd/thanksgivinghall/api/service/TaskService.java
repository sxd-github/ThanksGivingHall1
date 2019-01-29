package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.TaskDetailEntity;
import com.example.sxd.thanksgivinghall.bean.TaskListEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoTaskDetailEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoTaskListEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sxd on 2018/2/2.
 */

public abstract interface TaskService {


    /**
     * 新增任务
     * @param
     * @return
     */
    @POST("infc/infcAddTask/addTask")
    Call<Base> addOaTask(@Body RequestBody Body);


    /**
     * 新增任务回复
     * @param
     * @return
     */
    @POST("infc/infcAddTask/addTaskReply")
    Call<Base> addOaTaskReply(@Body RequestBody Body);

    /**
     * 获取当前用户的待办/已办任务列表
     * @param userId  当前用户
     * @param completeFlag  待办、已办标志   '0' 待办，‘1’,已办
     * @return
     */
    @GET("infc/infcOaTask/tbdTaskList")
    Call<ToDoTaskListEntity> selfLReceList(@Query("userId") String userId,@Query("completeFlag") String completeFlag);

    /**
     * 获取待办/已办任务详情以及回复列表
     * @param recordId  任务id
     * @return
     */
    @GET("infc/infcOaTask/tbdTaskDetails")
    Call<ToDoTaskDetailEntity> taskReceDetail(@Query("recordId") String recordId);

    /**
     * 获取当前用户已发布的任务列表
     * @param userId  当前用户
     * @return
     */
    @GET("infc/infcOaTask/publishTaskList")
    Call<TaskListEntity> selfList(@Query("userId") String userId);

    /**
     * 获取已发布任务详情以及接收人的回复列表
     * @param taskId  任务id
     * @return
     */
    @GET("infc/infcOaTask/publishTaskDetails")
    Call<TaskDetailEntity> taskDetail(@Query("taskId") String taskId);

}
