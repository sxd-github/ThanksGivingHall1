package com.example.sxd.thanksgivinghall.supplier;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.UpLoadFile;

import okhttp3.RequestBody;

/**
 * Created by sxd on 2018/3/13.
 */

public interface SupplierAddContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void uploadFileEnd(UpLoadFile value);
        void requestSuccess(Base value);

    }
//name,address,capital,person,nature,url,time,code,con,email,tel,faxnum,bank,banknum,credit,business
    interface Presenter extends BasePresenter<View> {
        void request(String name,String address, String capital, String person, String nature, String url, String time,String code,String con, String email,String tel,String faxnum,String bank,String banknum,String credit,String business);
    }

    public interface Model {
//        public void request(String title,String content, String files,String createBy, String userIds, ResultListener<Base> result);

         public void upload(RequestBody body, ResultListener<UpLoadFile> result);

        public void request(RequestBody body, ResultListener<Base> result);
    }
}
