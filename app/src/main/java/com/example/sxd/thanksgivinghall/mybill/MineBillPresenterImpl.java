package com.example.sxd.thanksgivinghall.mybill;

import android.content.Context;

import com.example.sxd.thanksgivinghall.mybill.MineBillContract;
import com.example.sxd.thanksgivinghall.mybill.MineBillModelImpl;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.MineBillListEntity;
import com.example.sxd.thanksgivinghall.bean.CustomerOrderDetailEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public class MineBillPresenterImpl extends BasePresenterImpl implements MineBillContract.Presenter{
    private MineBillContract.View mView;
    private MineBillContract.Model mModel;
    private Context context;

    public MineBillPresenterImpl(Context context, MineBillContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new MineBillModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String userId,String dateTextRe) {
        this.mModel.request(userId,dateTextRe, new ResultListener<MineBillListEntity>() {

            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(MineBillListEntity data) {
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
    public void attachView(MineBillContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
