package com.example.sxd.thanksgivinghall.notice;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.UpLoadFile;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sxd on 2018/3/13.
 */

public class NoticeAddPresenterImpl extends BasePresenterImpl implements NoticeAddContract.Presenter{
    public NoticeAddContract.View mView;
    public NoticeAddContract.Model mModel;
    public Context context;

    public NoticeAddPresenterImpl(Context context, NoticeAddContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new NoticeAddModelImpl(getBaseUrl(context));
    }
    @Override
    public void upload(String picname, String filePath1, String filePath2, String filePath3) {

        List<File> fileList = new ArrayList<>();
        if(StringUtils.notIsBlankAndEmpty(filePath1)){
            fileList.add(new File(filePath1));
        }if(StringUtils.notIsBlankAndEmpty(filePath2)){
            fileList.add(new File(filePath2));
        }if(StringUtils.notIsBlankAndEmpty(filePath3)){
            fileList.add(new File(filePath3));
        }

        System.out.println("picname:"+picname +":uploadfile:"+filePath1+":uploadfile:"+filePath2+":uploadfile:"+filePath3);

        MultipartBody.Builder builder= new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("title", "asdffw")
                .addFormDataPart("picname", picname);
        //注意，file是后台约定的参数，如果是多图，file[]，如果是单张图片，file就行
        for (File file : fileList) {
            //这里上传的是多图
            builder.addFormDataPart("uploadfile", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }

        RequestBody requestBody = builder.build();
//                .addFormDataPart("uploadfile", file1.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file1))
//                .addFormDataPart("uploadfile", file2.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file2))
//                .addFormDataPart("uploadfile", file3.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file3))
//                .build();

        File newFile = new File(filePath1);
        if (newFile.exists()) {
            this.mModel.upload(requestBody, new ResultListener<UpLoadFile>() {

                @Override
                public void onStart() {

                }

                @Override
                public void onEnd() {

                }

                @Override
                public void onSuccess(UpLoadFile data) {

                    if(data!=null) {
                        mView.showMessage("成功");
                        mView.uploadFileEnd(data);
                    }else{
                        mView.showMessage("失败");
                    }
                }

                @Override
                public void onFailure(String message) {
                    System.out.println("message:"+message);
                    mView.showMessage("失败");
                }
            });

        } else {
//                mView.uploadFileEnd1();
        }
    }
    @Override
    public void request(String title,String content, String files,String sendUserId, String receUserIds,String urgentFlag
            , String imgPath1, String imgPath2, String imgPath3) {

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
        params.put("urgentFlag", urgentFlag);
        params.put("imgPath1", imgPath1);
        params.put("imgPath2", imgPath2);
        params.put("imgPath3", imgPath3);
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
    public void attachView(NoticeAddContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
