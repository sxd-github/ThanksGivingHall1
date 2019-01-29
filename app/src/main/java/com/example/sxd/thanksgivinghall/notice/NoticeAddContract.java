package com.example.sxd.thanksgivinghall.notice;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.BasicEntity;
import com.example.sxd.thanksgivinghall.bean.UpLoadFile;

import okhttp3.RequestBody;

/**
 * Created by sxd on 2018/3/13.
 */

public interface NoticeAddContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void uploadFileEnd(UpLoadFile value);
        void requestSuccess(Base value);

    }

    interface Presenter extends BasePresenter<View> {
        void upload(String picname, String uploadfile1, String uploadfile2, String uploadfile3);

        void request(String title, String content,String files, String sendUserId,String receUserIds,String urgentFlag
                , String filePath1, String filePath2, String filePath3);
      //  void request(RequestBody body);
    }

    public interface Model {
 //       public void request(String title,String content, String files,String createBy, String userIds, ResultListener<Base> result);

         public void upload(RequestBody body, ResultListener<UpLoadFile> result);

        public void request(RequestBody body,ResultListener<Base> result);
    }
}
