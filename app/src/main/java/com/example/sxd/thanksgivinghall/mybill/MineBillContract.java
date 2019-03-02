package com.example.sxd.thanksgivinghall.mybill;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.MineBillListEntity;
import com.example.sxd.thanksgivinghall.bean.CustomerOrderDetailEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface MineBillContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void requestSuccess(MineBillListEntity value);

    }

    interface Presenter extends BasePresenter<View> {
        void request(String userId,String dateTextRe);

    }

    public interface Model {
        public void request(String userId,String dateTextRe, ResultListener<MineBillListEntity> result);

    }
}
