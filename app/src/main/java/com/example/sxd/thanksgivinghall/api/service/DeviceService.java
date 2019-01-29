package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.BasicEntity;
import com.example.sxd.thanksgivinghall.bean.CaptureDeviceInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sxd on 2018/2/2.
 */

public abstract interface DeviceService {

    /**
     * 获取公司监拍装置列表
     *
     * @param companyId 公司id
     */
    @GET("appDevice/getCompanyCaptureDeviceList")
    Call<BasicEntity<CaptureDeviceInfo>> listCaptureDevice(@Query("companyId") String companyId);


}
