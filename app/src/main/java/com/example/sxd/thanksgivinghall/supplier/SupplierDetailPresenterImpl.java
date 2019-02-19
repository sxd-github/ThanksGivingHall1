package com.example.sxd.thanksgivinghall.supplier;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.SupplierDetailEntity;


/**
 * Created by Administrator on 2018/3/13.
 */

public class SupplierDetailPresenterImpl extends BasePresenterImpl implements SupplierDetailContract.Presenter{
    private SupplierDetailContract.View mView;
    private SupplierDetailContract.Model mModel;
    private Context context;

    public SupplierDetailPresenterImpl(Context context, SupplierDetailContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new SupplierDetailModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String companyid) {
        this.mModel.request(companyid, new ResultListener<SupplierDetailEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(SupplierDetailEntity data) {
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
    public void attachView(SupplierDetailContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
