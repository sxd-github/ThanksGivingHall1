package com.example.sxd.thanksgivinghall.login;

import android.content.Context;

import com.blankj.utilcode.utils.EncryptUtils;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.LoginEntity;
import com.example.sxd.thanksgivinghall.bean.UserInfoEntity;
import com.example.sxd.thanksgivinghall.utils.NetUtil;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

/**
 * Created by Administrator on 2018/3/13.
 */

public class LoginPresenterImpl extends BasePresenterImpl implements LoginContract.Presenter{
    private LoginContract.View mView;
    private LoginContract.Model mModel;
    private Context context;

    public LoginPresenterImpl(Context context, LoginContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new LoginModelImpl(getBaseUrl(context));
    }
    @Override
    public void login(String useraccount, String password) {
        if (!StringUtils.notIsBlankAndEmpty(useraccount)) {
            this.mView.showMessage(context.getString(R.string.login_activity_input_username_toast));
            return;
        }
        if (!StringUtils.notIsBlankAndEmpty(password)) {
            this.mView.showMessage(context.getString(R.string.login_activity_input_password_toast));
            return;
        }

        if (password.length() < 6 && password.length() > 20) {
            this.mView.showMessage(context.getString(R.string.login_activity_input_password_error_toast));
            return;
        }
        //密码MD5加密 或者MD5.md5(password）
        //String passwords = EncryptUtils.encryptMD5ToString(password.getBytes()).toLowerCase();
        //System.out.println(passwords);

        if(!NetUtil.isNetworkAvailable(context)){
            this.mView.showMessage(context.getString(R.string.login_activity_loginfail_network));
            return;
        }
        this.mModel.login(useraccount, new ResultListener<LoginEntity>() {
            @Override
            public void onStart() {
                mView.showMessage(context.getString(R.string.login_activity_logining_toast));
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(LoginEntity data) {
                if (data != null) {
                    if(data.getSuccess().equals("true")){
                        mView.loginSuccess(data);
                    }else {
                        mView.showMessage(data.getStatusMessage());
                    }
                }
            }

            @Override
            public void onFailure(String message) {
                mView.showMessage(context.getString(R.string.login_activity_loginfail_toast));
            }
        });
    }


    @Override
    public void getUserInfos(String username) {
        this.mModel.userInfos(username, new ResultListener<UserInfoEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(UserInfoEntity data) {
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
    public void attachView(LoginContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
