package com.example.sxd.thanksgivinghall.my;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.UserInfoEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public class MinePresenterImpl extends BasePresenterImpl implements MineContract.Presenter{
    private MineContract.View mView;
    private MineContract.Model mModel;
    private Context context;

    public MinePresenterImpl(Context context, MineContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new MineModelImpl(getBaseUrl(context));
    }
    @Override
    public void getUserInfo(String useraccount) {
        this.mModel.userInfo(useraccount, new ResultListener<UserInfoEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(UserInfoEntity data) {
                if (data != null) {
                    mView.getUserInfo(data);
                }
            }

            @Override
            public void onFailure(String message) {
                mView.showMessage(context.getString(R.string.login_activity_loading_fail_toast));
            }
        });
    }

    @Override
    public void attachView(MineContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
