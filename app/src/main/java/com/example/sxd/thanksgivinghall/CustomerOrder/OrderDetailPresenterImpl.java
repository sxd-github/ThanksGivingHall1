package com.example.sxd.thanksgivinghall.CustomerOrder;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.CustomerOrderDetailEntity;
import com.example.sxd.thanksgivinghall.CustomerOrder.OrderDetailContract;
import com.example.sxd.thanksgivinghall.CustomerOrder.OrderDetailModelImpl;

/**
 * Created by Administrator on 2018/3/13.
 */

public class OrderDetailPresenterImpl extends BasePresenterImpl implements OrderDetailContract.Presenter{
    private OrderDetailContract.View mView;
    private OrderDetailContract.Model mModel;
    private Context context;

    public OrderDetailPresenterImpl(Context context, OrderDetailContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new OrderDetailModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String companyid) {
        this.mModel.request(companyid, new ResultListener<CustomerOrderDetailEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(CustomerOrderDetailEntity data) {
                if (data != null) {
                    if(data.getStatusMessage().equals("ok")) {
                        mView.requestSuccess(data);
                    }else{
                        mView.showMessage(data.getStatusMessage());
                    }
                } else {
                    mView.showMessage(context.getString(R.string.login_activity_loginfail_toast));
                }
            }

            @Override
            public void onFailure(String message) {
                mView.showMessage(context.getString(R.string.login_activity_loginfail_toast));
            }
        });
    }



    @Override
    public void attachView(OrderDetailContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
