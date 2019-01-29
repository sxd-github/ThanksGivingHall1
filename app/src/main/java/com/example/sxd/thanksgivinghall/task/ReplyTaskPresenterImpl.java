package com.example.sxd.thanksgivinghall.task;

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

public class ReplyTaskPresenterImpl extends BasePresenterImpl implements ReplyTaskContract.Presenter{
    public ReplyTaskContract.View mView;
    public ReplyTaskContract.Model mModel;
    public Context context;

    public ReplyTaskPresenterImpl(Context context, ReplyTaskContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new ReplyTaskModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String oaTaskRecordId,String replyFlag, String replyContent,String sendUser) {

        if (!StringUtils.notIsBlankAndEmpty(replyContent)) {
            this.mView.showMessage(context.getString(R.string.null_content));
            return;
        }
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("oaTaskRecordId", oaTaskRecordId);
        params.put("replyFlag", replyFlag);
        params.put("replyContent", replyContent);
        params.put("sendUser", sendUser);
        JSONObject jsonObj = new JSONObject(params);

       // RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new JSONObject(params).toString());
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
                    mView.showMessage(context.getString(R.string.task_reply_fail_toast));
                }
            }

            @Override
            public void onFailure(String message) {
                mView.showMessage(context.getString(R.string.login_activity_loginfail_toast));
            }
        });
    }

    @Override
    public void attachView(ReplyTaskContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
