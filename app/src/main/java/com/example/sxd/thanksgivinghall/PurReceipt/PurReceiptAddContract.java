package com.example.sxd.thanksgivinghall.PurReceipt;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.UpLoadFile;

import okhttp3.RequestBody;

/**
 * Created by sxd on 2018/3/13.
 */

public interface PurReceiptAddContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void uploadFileEnd(UpLoadFile value);
        void requestSuccess(Base value);

    }
    //sup,name,num,type,price,total,da,person,methord
    interface Presenter extends BasePresenter<View> {
        void request(String sup, String name, String num, String type, String price, String total, String da, String person, String methord);
    }

    public interface Model {
//        public void request(String title,String content, String files,String createBy, String userIds, ResultListener<Base> result);

         public void upload(RequestBody body, ResultListener<UpLoadFile> result);

        public void request(RequestBody body, ResultListener<Base> result);
    }
}
