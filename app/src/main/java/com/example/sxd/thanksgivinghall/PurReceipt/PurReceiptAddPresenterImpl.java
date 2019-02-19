package com.example.sxd.thanksgivinghall.PurReceipt;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.Base;


import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by sxd on 2018/3/13.
 */

public class PurReceiptAddPresenterImpl extends BasePresenterImpl implements PurReceiptAddContract.Presenter{
    public PurReceiptAddContract.View mView;
    public PurReceiptAddContract.Model mModel;
    public Context context;

    public PurReceiptAddPresenterImpl(Context context, PurReceiptAddContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new PurReceiptAddModelImpl(getBaseUrl(context));
    }

    @Override
    public void request(String sup, String name, String num, String type, String price, String total, String da, String person, String methord)
    {

        /*if (!StringUtils.notIsBlankAndEmpty(name)) {
            this.mView.showMessage(context.getString(R.string.null_title));
            return;
        }
        if (!StringUtils.notIsBlankAndEmpty(lPerson)) {
            this.mView.showMessage(context.getString(R.string.null_receUserIds));
            return;
        }
        if (!StringUtils.notIsBlankAndEmpty(Nature)) {
            this.mView.showMessage(context.getString(R.string.null_receUserIds));
            return;
        }
        if (!StringUtils.notIsBlankAndEmpty(url)) {
            this.mView.showMessage(context.getString(R.string.null_receUserIds));
            return;
        }*/
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("supplier", sup);
        params.put("goodsName", name);
        params.put("goodsType", type);
        params.put("unitPrice", price);
        params.put("goodsNum",num);
        Double price1=Double.parseDouble(price);
        Double num1 = Double.parseDouble(num);
        Double total1 = price1*num1;
        total = Double.toString(total1);

        params.put("totalPrice",total);
        params.put("recDate",da);
        params.put("purchasePerson",person);
        params.put("payMethod",methord);

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
    public void attachView(PurReceiptAddContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
