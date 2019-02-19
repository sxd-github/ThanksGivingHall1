package com.example.sxd.thanksgivinghall.customer;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.CustomerDetailEntity;
import com.example.sxd.thanksgivinghall.bean.CustomerListEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface CustomerDetailContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void requestSuccess(CustomerDetailEntity value);

    }

    interface Presenter extends BasePresenter<View> {
        void request(String companyid);

    }

    public interface Model {
        public void request(String companyid, ResultListener<CustomerDetailEntity> result);

    }
}
