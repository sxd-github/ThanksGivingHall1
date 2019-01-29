package com.example.sxd.thanksgivinghall.treelist.officeUser;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.OfficeUserEntity;
import com.example.sxd.thanksgivinghall.bean.UserEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public class BasesPresenterImpl extends BasePresenterImpl implements BasesContract.Presenter{
    private BasesContract.View mView;
    private BasesContract.Model mModel;
    private Context context;

    public BasesPresenterImpl(Context context, BasesContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new BasesModelImpl(getBaseUrl(context));
    }

    @Override
    public void getUserInfos(String id) {
        this.mModel.userInfos(id,new ResultListener<UserEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(UserEntity data) {
                if (data != null) {
                    mView.getUserInfos(data);
                }
            }

            @Override
            public void onFailure(String message) {
                mView.showMessage(context.getString(R.string.login_activity_loading_fail_toast));
            }
        });
    }


    @Override
    public void attachView(BasesContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
