package com.example.sxd.thanksgivinghall.task;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.TaskDetailEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoTaskDetailEntity;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface PublishedTaskDetailContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void requestSuccess(TaskDetailEntity value);

    }

    interface Presenter extends BasePresenter<View> {
        void request(String taskId);
    }

    public interface Model {
        public void request(String taskId, ResultListener<TaskDetailEntity> result);

    }
}
