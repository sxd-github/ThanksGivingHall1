package com.example.sxd.thanksgivinghall.PurReceipt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.PurReceiptDetailEntity;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待办任务列表
 */
public class PurReceiptDetailActivity extends BaseActivity implements PurReceiptDetailContract.View{


    @BindView(R.id.supplier)
    TextView supplier;
    @BindView(R.id.goodsName)
    TextView goodsName;
    @BindView(R.id.goodsType)
    TextView goodsType;
    @BindView(R.id.unitPrice)
    TextView unitPrice;
    @BindView(R.id.goodsNum)
    TextView goodsNum;
    @BindView(R.id.totalPrice)
    TextView totalPrice;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.purchasePerson)
    TextView purchasePerson;
    @BindView(R.id.payMethod)
    TextView payMethod;

    PurReceiptDetailContract.Presenter mPresenter;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.receipt_detail);
        ButterKnife.bind(this);
        setTitle("订单详情");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new PurReceiptDetailPresenterImpl(PurReceiptDetailActivity.this, this);
        initData();
    }

    public void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        mPresenter.request(id);
    }

    @Override
    protected void setRightTitleOnClick(View v) {
    }


    @Override
    public void requestSuccess(final PurReceiptDetailEntity value) {
        supplier.setText(value.getData().getSupplier());
        goodsName.setText(value.getData().getGoodsName());
        goodsType.setText(value.getData().getGoodsType());
        unitPrice.setText(value.getData().getUnitPrice());
        goodsNum.setText(value.getData().getGoodsNum());
        totalPrice.setText(value.getData().getTotalPrice());
        date.setText(value.getData().getDate());
        purchasePerson.setText(value.getData().getPurchasePerson());
        payMethod.setText(value.getData().getPayMethod());

        String urlBase= String.format("http://%s:%s/", new Object[] { SharedPreUtils.getString(this,"SP_IP"),  SharedPreUtils.getString(this,"SP_PORT") });

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PurReceiptDetailContract.Presenter paramT) {

    }
}
