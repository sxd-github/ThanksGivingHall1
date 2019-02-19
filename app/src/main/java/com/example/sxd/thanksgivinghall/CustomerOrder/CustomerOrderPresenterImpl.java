package com.example.sxd.thanksgivinghall.CustomerOrder;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.CustomerOrderListEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public class CustomerOrderPresenterImpl extends BasePresenterImpl implements CustomerOrderContract.Presenter{
    private CustomerOrderContract.View mView;
    private CustomerOrderContract.Model mModel;
    private Context context;

    public CustomerOrderPresenterImpl(Context context, CustomerOrderContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new CustomerOrderModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String userId) {
        this.mModel.request(userId, new ResultListener<CustomerOrderListEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(CustomerOrderListEntity data) {
                if (data != null) {
                    if(data.getStatusMessage().equals("ok")) {
                        mView.requestSuccess(data);
                    }else{
                        mView.showMessage(data.getStatusMessage());
                    }
                } else {
                    mView.showMessage(context.getString(R.string.login_activity_loading_null));
                }
            }

            @Override
            public void onFailure(String message) {
                mView.showMessage(context.getString(R.string.login_activity_loginfail_toast));
            }
        });
    }



    @Override
    public void attachView(CustomerOrderContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
