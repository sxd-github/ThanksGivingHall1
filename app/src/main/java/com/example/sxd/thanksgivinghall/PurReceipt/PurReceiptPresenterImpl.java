package com.example.sxd.thanksgivinghall.PurReceipt;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.PurReceiptListEntity;
import com.example.sxd.thanksgivinghall.PurReceipt.PurRecetptContract;
import com.example.sxd.thanksgivinghall.PurReceipt.PurReceiptModelImpl;

/**
 * Created by Administrator on 2018/3/13.
 */

public class PurReceiptPresenterImpl extends BasePresenterImpl implements PurRecetptContract.Presenter{
    private PurRecetptContract.View mView;
    private PurRecetptContract.Model mModel;
    private Context context;

    public PurReceiptPresenterImpl(Context context, PurRecetptContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new PurReceiptModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String userId) {
        this.mModel.request(userId, new ResultListener<PurReceiptListEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(PurReceiptListEntity data) {
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
    public void attachView(PurRecetptContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
