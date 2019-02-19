package com.example.sxd.thanksgivinghall.customer;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.CustomerDetailEntity;
import com.example.sxd.thanksgivinghall.customer.CustomerDetailContract;
import com.example.sxd.thanksgivinghall.customer.CustomerModelImpl;

/**
 * Created by Administrator on 2018/3/13.
 */

public class CustomerDetailPresenterImpl extends BasePresenterImpl implements CustomerDetailContract.Presenter{
    private CustomerDetailContract.View mView;
    private CustomerDetailContract.Model mModel;
    private Context context;

    public CustomerDetailPresenterImpl(Context context, CustomerDetailContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new CustomerDetailModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String companyid) {
        this.mModel.request(companyid, new ResultListener<CustomerDetailEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(CustomerDetailEntity data) {
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
    public void attachView(CustomerDetailContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
