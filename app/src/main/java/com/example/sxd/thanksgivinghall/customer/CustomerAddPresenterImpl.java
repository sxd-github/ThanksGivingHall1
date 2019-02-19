package com.example.sxd.thanksgivinghall.customer;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by sxd on 2018/3/13.
 */

public class CustomerAddPresenterImpl extends BasePresenterImpl implements CustomerAddContract.Presenter{
    public CustomerAddContract.View mView;
    public CustomerAddContract.Model mModel;
    public Context context;

    public CustomerAddPresenterImpl(Context context, CustomerAddContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new CustomerAddModelImpl(getBaseUrl(context));
    }

    @Override
    public void request(String title, String sex,String nation,String content, String files, String sendUserId, String receUserIds, String urgentFlag) {

        if (!StringUtils.notIsBlankAndEmpty(title)) {
            this.mView.showMessage(context.getString(R.string.null_title));
            return;
        }
        if (!StringUtils.notIsBlankAndEmpty(content)) {
            this.mView.showMessage(context.getString(R.string.null_content));
            return;
        }
       /* if (!StringUtils.notIsBlankAndEmpty(receUserIds)) {
            this.mView.showMessage(context.getString(R.string.null_receUserIds));
            return;
        }*/
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("username", title);
        params.put("sex",sex);
        params.put("nation",nation);
        params.put("tel", content);
        params.put("remarks", files);
        params.put("sendUserId", sendUserId);
        params.put("receUserIds", receUserIds);
        params.put("urgentFlag", urgentFlag);

        JSONObject jsonObj = new JSONObject(params);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObj.toString());

        this.mModel.request(requestBody, new ResultListener<Base>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(Base data) {
                if (data != null) {
                    if(data.getSuccess().equals("true")) {
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
    public void attachView(CustomerAddContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
