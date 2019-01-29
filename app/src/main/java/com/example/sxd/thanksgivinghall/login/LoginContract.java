package com.example.sxd.thanksgivinghall.login;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.LoginEntity;
import com.example.sxd.thanksgivinghall.bean.UserInfoEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void loginSuccess(LoginEntity value);

        void getUserInfos(UserInfoEntity value);

    }

    interface Presenter extends BasePresenter<View> {
        void login(String username,String password);

        void getUserInfos(String username);
    }

    public interface Model {
        public void login(String username, ResultListener<LoginEntity> result);

        public void userInfos(String username, ResultListener<UserInfoEntity> result);
    }
}
