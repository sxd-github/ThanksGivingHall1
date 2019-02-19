package com.example.sxd.thanksgivinghall.customer;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.UpLoadFile;

import okhttp3.RequestBody;

/**
 * Created by sxd on 2018/3/13.
 */

public interface CustomerAddContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void requestSuccess(Base value);

    }

    interface Presenter extends BasePresenter<View> {

        void request(String title, String sex,String nation,String content, String files, String sendUserId, String receUserIds, String urgentFlag);
      //  void request(RequestBody body);
    }

    public interface Model {
 //       public void request(String title,String content, String files,String createBy, String userIds, ResultListener<Base> result);

         public void upload(RequestBody body, ResultListener<UpLoadFile> result);

        public void request(RequestBody body, ResultListener<Base> result);
    }
}
