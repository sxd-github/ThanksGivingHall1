package com.example.sxd.thanksgivinghall.task;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.TaskListEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoTaskListEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public class PublishedTaskPresenterImpl extends BasePresenterImpl implements PublishedTaskContract.Presenter{
    private PublishedTaskContract.View mView;
    private PublishedTaskContract.Model mModel;
    private Context context;

    public PublishedTaskPresenterImpl(Context context, PublishedTaskContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new PublishedTaskModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String userid) {
        this.mModel.request(userid, new ResultListener<TaskListEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(TaskListEntity data) {
                if (data != null) {
                    if(data.getStatusMessage().equals("ok")) {
                        mView.requestSuccess(data);
                    }else{
   //                     mView.showMessage(data.getStatusMessage());
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
    public void attachView(PublishedTaskContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
