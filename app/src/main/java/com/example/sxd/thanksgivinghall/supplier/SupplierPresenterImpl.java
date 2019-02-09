package com.example.sxd.thanksgivinghall.supplier;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.SupplierListEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public class SupplierPresenterImpl extends BasePresenterImpl implements SupplierContract.Presenter{
    private SupplierContract.View mView;
    private SupplierContract.Model mModel;
    private Context context;

    public SupplierPresenterImpl(Context context, SupplierContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new SupplierModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String userId) {
        this.mModel.request(userId, new ResultListener<SupplierListEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(SupplierListEntity data) {
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
    public void attachView(SupplierContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
