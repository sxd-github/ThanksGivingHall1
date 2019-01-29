package com.example.sxd.thanksgivinghall.task;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.TaskListEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoTaskListEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface PublishedTaskContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void requestSuccess(TaskListEntity value);

    }

    interface Presenter extends BasePresenter<View> {
        void request(String userid);

    }

    public interface Model {
        public void request(String userid, ResultListener<TaskListEntity> result);

    }
}
