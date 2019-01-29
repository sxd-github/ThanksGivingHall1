package com.example.sxd.thanksgivinghall.treelist.officer;

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

        void getOfficerInfos(UserEntity value);

    }

    interface Presenter extends BasePresenter<View> {
        void getOfficerInfos();

    }

    public interface Model {

        public void officerInfos(ResultListener<UserEntity> result);

    }
}
