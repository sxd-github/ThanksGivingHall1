package com.example.sxd.thanksgivinghall.notice;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.ToDoNotifyListEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface ToDoNoticeContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void requestSuccess(ToDoNotifyListEntity value);

    }

    interface Presenter extends BasePresenter<View> {
        void request(String userId);

    }

    public interface Model {
        public void request(String userId, ResultListener<ToDoNotifyListEntity> result);

    }
}
