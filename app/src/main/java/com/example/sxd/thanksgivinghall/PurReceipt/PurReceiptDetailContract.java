package com.example.sxd.thanksgivinghall.PurReceipt;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.PurReceiptDetailEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface PurReceiptDetailContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void requestSuccess(PurReceiptDetailEntity value);

    }

    interface Presenter extends BasePresenter<View> {
        void request(String companyid);

    }

    public interface Model {
        public void request(String companyid, ResultListener<PurReceiptDetailEntity> result);

    }
}
