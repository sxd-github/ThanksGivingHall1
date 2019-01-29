package com.example.sxd.thanksgivinghall.task;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.TaskDetailEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public class PublishedTaskDetailPresenterImpl extends BasePresenterImpl implements PublishedTaskDetailContract.Presenter{
    private PublishedTaskDetailContract.View mView;
    private PublishedTaskDetailContract.Model mModel;
    private Context context;

    public PublishedTaskDetailPresenterImpl(Context context, PublishedTaskDetailContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new PublishedTaskDetailModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String recordId) {
        this.mModel.request(recordId, new ResultListener<TaskDetailEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(TaskDetailEntity data) {
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
    public void attachView(PublishedTaskDetailContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
