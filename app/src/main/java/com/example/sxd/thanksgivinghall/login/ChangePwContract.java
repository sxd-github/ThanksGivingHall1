package com.example.sxd.thanksgivinghall.login;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.UserInfoEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface ChangePwContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void changePwSuccess(Base value);
    }

    interface Presenter extends BasePresenter<View> {
        void changePw(String userName, String oldPassword, String newPassword);

    }

    public interface Model {
        public void changePw(String userName, String oldPassword, String newPassword, ResultListener<Base> result);
    }
}
