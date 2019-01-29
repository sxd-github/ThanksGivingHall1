package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.UpLoadFile;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/4/20.
 */

public interface UpLoadService {

    /**
     * 上传文件
     * @param companyId
     * @param meid
     * @param picname
     * @param uploaddt
     * @param uploadfile
     * @return
     */
//    @FormUrlEncoded
//    @POST("appUploadFile")
//    Call<UpLoadFile> appUploadFile(@Field("companyId") String companyId, @Field("meid") String meid,
//                                   @Field("picname") String picname, @Field("uploaddt") String uploaddt,
//                                   @Field("uploadfile") File uploadfile);

    @POST("infc/fileUpload/uploadPhoto")
    Call<UpLoadFile> appUploadFile(@Body RequestBody Body);
}
