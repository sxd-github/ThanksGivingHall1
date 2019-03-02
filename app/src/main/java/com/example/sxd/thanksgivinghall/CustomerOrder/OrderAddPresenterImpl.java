package com.example.sxd.thanksgivinghall.CustomerOrder;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.Base;
/*import com.example.sxd.thanksgivinghall.customer.CustomerAddContract;
import com.example.sxd.thanksgivinghall.customer.CustomerAddModelImpl;*/
import com.example.sxd.thanksgivinghall.CustomerOrder.OrderAddContract;
import com.example.sxd.thanksgivinghall.CustomerOrder.OrderAddModelImpl;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by sxd on 2018/3/13.
 */

public class OrderAddPresenterImpl extends BasePresenterImpl implements OrderAddContract.Presenter{
    public OrderAddContract.View mView;
    public OrderAddContract.Model mModel;
    public Context context;
    String sum;
    public OrderAddPresenterImpl(Context context, OrderAddContract.View mView) {
        this.mView = mView;
        this.context = context;
        mModel = new OrderAddModelImpl(getBaseUrl(context));
    }

    @Override
    public void request(String cusname, String goods, String nums, String price, String date,String OrderType, String sendUserId, String receUserIds, String urgentFlag) {
        if (!StringUtils.notIsBlankAndEmpty(cusname)) {
            this.mView.showMessage(context.getString(R.string.null_cusname));
            return;
        }
       /* if (!StringUtils.notIsBlankAndEmpty(content)) {
            this.mView.showMessage(context.getString(R.string.null_content));
            return;
        }*/
       /* if (!StringUtils.notIsBlankAndEmpty(receUserIds)) {
            this.mView.showMessage(context.getString(R.string.null_receUserIds));
            return;
        }*/
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("cusname", cusname);
        params.put("goods",goods);
        params.put("goodsnum",nums);
        params.put("price", price);
        params.put("ordertype",OrderType);
       /* params.put("sumprice",);
        params.put("orderType",);*/
        params.put("date", date);
        Double p=Double.parseDouble(price);
        Double n=Double.parseDouble(nums);
        Double sum1=p*n;
        sum=Double.toString(sum1);
        params.put("sumprice",sum);
        /*params.put("sendUserId", sendUserId);
        params.put("receUserIds", receUserIds);
        params.put("urgentFlag", urgentFlag);*/

        JSONObject jsonObj = new JSONObject(params);

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
    public void attachView(OrderAddContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
