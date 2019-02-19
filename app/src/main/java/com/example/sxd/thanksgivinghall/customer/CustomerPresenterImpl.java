package com.example.sxd.thanksgivinghall.customer;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.CustomerListEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public class CustomerPresenterImpl extends BasePresenterImpl implements CustomerContract.Presenter{
    private CustomerContract.View mView;
    private CustomerContract.Model mModel;
    private Context context;

    public CustomerPresenterImpl(Context context, CustomerContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new CustomerModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String userId) {
        this.mModel.request(userId, new ResultListener<CustomerListEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(CustomerListEntity data) {
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
    public void attachView(CustomerContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
