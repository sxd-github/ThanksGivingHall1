package com.example.sxd.thanksgivinghall.notice;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.ToDoNotifyListEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public class ToDoNoticePresenterImpl extends BasePresenterImpl implements ToDoNoticeContract.Presenter{
    private ToDoNoticeContract.View mView;
    private ToDoNoticeContract.Model mModel;
    private Context context;

    public ToDoNoticePresenterImpl(Context context, ToDoNoticeContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new ToDoNoticeModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String userId) {
        this.mModel.request(userId, new ResultListener<ToDoNotifyListEntity>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {
            }

            @Override
            public void onSuccess(ToDoNotifyListEntity data) {
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
    public void attachView(ToDoNoticeContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
