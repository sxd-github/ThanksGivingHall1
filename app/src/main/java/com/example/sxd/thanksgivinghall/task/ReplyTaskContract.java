package com.example.sxd.thanksgivinghall.task;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;

import okhttp3.RequestBody;

/**
 * Created by sxd on 2018/3/13.
 */

public interface ReplyTaskContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void requestSuccess(Base value);
    }

    interface Presenter extends BasePresenter<View> {

        void request(String oaTaskRecordId,String replyFlag, String replyContent,String sendUser);
      //  void request(RequestBody body);
    }

    public interface Model {
 //       public void request(String title,String content, String files,String createBy, String userIds, ResultListener<Base> result);
        public void request(RequestBody body, ResultListener<Base> result);
    }
}
