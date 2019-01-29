package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.LoginEntity;
import com.example.sxd.thanksgivinghall.bean.UserInfoEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sxd on 2018/2/2.
 */

public abstract interface LoginService {

    /***
     * 用户登录
     * @param userName
     * @return
     */
//    @GET("infc/infclogin/userLogin")
//    Call<Base> userLogin(@Query("userName") String userName, @Query("password") String password);


    /***
     * 用户登录
     * @param userName
     * @return
     */
    @GET("infc/infclogin/userLogin")
    Call<LoginEntity> userLogin(@Query("userName") String userName);


    /**
     * 用户信息
     * @param userName  用户账号
     * @return
     */
    @GET("infc/infcuser/getUserInfo")
    Call<UserInfoEntity> userInfos(@Query("userName") String userName);

    /**
     * 更新密码
     * @param userName  用户账号
     * @return
     */
    @GET("infc/infcuser/updatePassword")
    Call<Base> updatePassword(@Query("userName") String userName,@Query("oldPassword") String oldPassword,@Query("newPassword") String newPassword);

}
