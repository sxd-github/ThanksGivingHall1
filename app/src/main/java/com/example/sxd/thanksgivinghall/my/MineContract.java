package com.example.sxd.thanksgivinghall.my;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.UserInfoEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface MineContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void getUserInfo(UserInfoEntity value);

    }

    interface Presenter extends BasePresenter<View> {

        void getUserInfo(String useraccount);

    }

    public interface Model {

        public void userInfo(String useraccount, ResultListener<UserInfoEntity> result);

    }
}
