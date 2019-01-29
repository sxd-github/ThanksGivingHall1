package com.example.sxd.thanksgivinghall.task;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.ToDoTaskDetailEntity;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface ToDoTaskDetailContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void requestSuccess(ToDoTaskDetailEntity value);

        void forward(Base value);

    }

    interface Presenter extends BasePresenter<View> {
        void request(String recordId);
        void forward(String title, String content, String files, String sendUserId, String receUserIds, String forwardFlag);

    }

    public interface Model {
        public void request(String recordId, ResultListener<ToDoTaskDetailEntity> result);

        public void forward(RequestBody body, ResultListener<Base> result);

    }
}
