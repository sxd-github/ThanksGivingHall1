package com.example.sxd.thanksgivinghall.treelist.officeUser;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.OfficeUserEntity;
import com.example.sxd.thanksgivinghall.bean.UserEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface BasesContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void getUserInfos(UserEntity value);

    }

    interface Presenter extends BasePresenter<View> {
        void getUserInfos(String id);

    }

    public interface Model {

        public void userInfos(String id,ResultListener<UserEntity> result);

    }
}
