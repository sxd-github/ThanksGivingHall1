package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.OfficeUserEntity;
import com.example.sxd.thanksgivinghall.bean.UserEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sxd on 2018/2/2.
 */

public abstract interface OfficeUserService {

    /**
     * 部门及部门用户信息
     * @param
     * @return
     */
    @GET("infc/infcuser/getOfficeAndUser")
    Call<OfficeUserEntity> getOfficeAndUser();

    /**
     * 获取某一部门的所有员工的信息
     * @param officeId 部门的id
     * @return
     */
    @GET("infc/infcuser/getUserByOffice")
    Call<UserEntity> getUserByOffice(@Query("officeId") String officeId);

    /**
     * 获取整个公司所有部长的信息
     * @param
     * @return
     */
    @GET("infc/infcuser/getOfficer")
    Call<UserEntity> getOfficer();

}
