package com.example.sxd.thanksgivinghall.PurReceipt;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.PurReceiptDetailEntity;


/**
 * Created by Administrator on 2018/3/13.
 */

public class PurReceiptDetailPresenterImpl extends BasePresenterImpl implements PurReceiptDetailContract.Presenter{
    private PurReceiptDetailContract.View mView;
    private PurReceiptDetailContract.Model mModel;
    private Context context;

    public PurReceiptDetailPresenterImpl(Context context, PurReceiptDetailContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new PurReceiptDetailModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String companyid) {
        this.mModel.request(companyid, new ResultListener<PurReceiptDetailEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(PurReceiptDetailEntity data) {
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
    public void attachView(PurReceiptDetailContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
