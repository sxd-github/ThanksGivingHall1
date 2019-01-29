package com.example.sxd.thanksgivinghall.login;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.UserInfoEntity;
import com.example.sxd.thanksgivinghall.utils.NetUtil;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

/**
 * Created by Administrator on 2018/3/13.
 */

public class ChangePwPresenterImpl extends BasePresenterImpl implements ChangePwContract.Presenter{
    private ChangePwContract.View mView;
    private ChangePwContract.Model mModel;
    private Context context;

    public ChangePwPresenterImpl(Context context, ChangePwContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new ChangePwModelImpl(getBaseUrl(context));
    }
    @Override
    public void changePw(String userName, String oldPassword, String newPassword) {

        this.mModel.changePw(userName, oldPassword, newPassword,new ResultListener<Base>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(Base data) {
                if (data != null) {
                    if(data.getSuccess().equals("true")){
                        mView.changePwSuccess(data);
                    }else {
                        mView.showMessage(data.getStatusMessage());
                    }
                } else {
                    mView.showMessage(context.getString(R.string.update_pw_fail));
                }
            }

            @Override
            public void onFailure(String message) {
                mView.showMessage(context.getString(R.string.login_activity_loginfail_toast));
            }
        });
    }


    @Override
    public void attachView(ChangePwContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
