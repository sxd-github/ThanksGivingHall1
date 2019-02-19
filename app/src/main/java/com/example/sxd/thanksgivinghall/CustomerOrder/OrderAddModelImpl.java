package com.example.sxd.thanksgivinghall.CustomerOrder;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.UpLoadFile;
import com.example.sxd.thanksgivinghall.CustomerOrder.OrderAddContract;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MJX on 2019/2/09.
 */

public class OrderAddModelImpl implements OrderAddContract.Model{
    public String baseUrl = "";

    public OrderAddModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }

    @Override
    public void upload(RequestBody body, final ResultListener<UpLoadFile> result) {
//        Call<UpLoadFile> call = AppMainService.getUpLoadService(baseUrl).appUploadFile(companyId,meid,picname,uploaddt,uploadfile);
        //执行操作
        Call<UpLoadFile> call = AppMainService.getUpLoadService(baseUrl).appUploadFile(body);
        result.onStart();
        //执行操作
        call.enqueue(new Callback<UpLoadFile>() {
            @Override
            public void onResponse(Call<UpLoadFile> call, Response<UpLoadFile> response) {
                //请求成功
                result.onSuccess(response.body());
                result.onEnd();
            }

            @Override
            public void onFailure(Call<UpLoadFile> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                result.onEnd();
            }
        });
    }
    @Override
    public void request(RequestBody body, final ResultListener<Base> result) {
    //    public void request(String title,String content, String files,String createBy, String userIds, final ResultListener<Base> result) {
        Call<Base> call = AppMainService.getCustomerOrderService(baseUrl).addOrderCustomer(body);
        //请求开始
        result.onStart();
        //执行操作
        call.enqueue(new Callback<Base>() {
            @Override
            public void onResponse(Call<Base> call, Response<Base> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<Base> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }
}
