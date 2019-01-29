package com.example.sxd.thanksgivinghall.task;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.ToDoTaskDetailEntity;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/3/13.
 */

public class ToDoTaskDetailPresenterImpl extends BasePresenterImpl implements ToDoTaskDetailContract.Presenter{
    private ToDoTaskDetailContract.View mView;
    private ToDoTaskDetailContract.Model mModel;
    private Context context;

    public ToDoTaskDetailPresenterImpl(Context context, ToDoTaskDetailContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new ToDoTaskDetailModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String recordId) {
        this.mModel.request(recordId, new ResultListener<ToDoTaskDetailEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(ToDoTaskDetailEntity data) {
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
    public void forward(String title,String content, String files,String sendUserId, String receUserIds,String forwardFlag) {

//        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("title", title)
//                .addFormDataPart("content", content)
//                .addFormDataPart("files", files)
//                .addFormDataPart("createBy", createBy)
//                .addFormDataPart("userIds", userIds)
//                .build();
        if (!StringUtils.notIsBlankAndEmpty(title)) {
            this.mView.showMessage(context.getString(R.string.null_title));
            return;
        }
        if (!StringUtils.notIsBlankAndEmpty(content)) {
            this.mView.showMessage(context.getString(R.string.null_content));
            return;
        }
        if (!StringUtils.notIsBlankAndEmpty(receUserIds)) {
            this.mView.showMessage(context.getString(R.string.null_receUserIds));
            return;
        }
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("title", title);
        params.put("content", content);
        params.put("files", files);
        params.put("sendUserId", sendUserId);
        params.put("receUserIds", receUserIds);
        params.put("forwardFlag", forwardFlag);
        JSONObject jsonObj = new JSONObject(params);

        // RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new JSONObject(params).toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObj.toString());

        this.mModel.forward(requestBody, new ResultListener<Base>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(Base data) {
                if (data != null) {
                    if(data.getStatusMessage().equals("ok")) {
                        mView.forward(data);
                    }else{
                        mView.showMessage(data.getStatusMessage());
                    }
                } else {
                    mView.showMessage(context.getString(R.string.login_activity_loading_fail_toast));
                }
            }

            @Override
            public void onFailure(String message) {
                mView.showMessage(context.getString(R.string.login_activity_loginfail_toast));
            }
        });
    }



    @Override
    public void attachView(ToDoTaskDetailContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
