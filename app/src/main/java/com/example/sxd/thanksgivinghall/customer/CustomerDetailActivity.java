package com.example.sxd.thanksgivinghall.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.CustomerListEntity;
import com.example.sxd.thanksgivinghall.bean.CustomerDetailEntity;
import com.example.sxd.thanksgivinghall.customer.CustomerDetailContract;
import com.example.sxd.thanksgivinghall.customer.CustomerDetailPresenterImpl;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 客户详细信息
 */
public class CustomerDetailActivity extends BaseActivity implements CustomerDetailContract.View{


    @BindView(R.id.customer_username)
    TextView customer_username;
    @BindView(R.id.customer_nation)
    TextView customer_nation;
    @BindView(R.id.customer_sex)
    TextView customer_sex;
    @BindView(R.id.customer_tel)
    TextView customer_tel;
    @BindView(R.id.customer_source)
    TextView customer_source;

    CustomerDetailContract.Presenter mPresenter;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_customer_detail);
        ButterKnife.bind(this);
        setTitle("客户详细信息");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new CustomerDetailPresenterImpl(CustomerDetailActivity.this, this);
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
    public void requestSuccess(final CustomerDetailEntity value) {
        customer_username.setText(value.getData().getUsername());
        customer_nation.setText(value.getData().getNation());
        customer_sex.setText(value.getData().getSex());
        customer_source.setText(value.getData().getSource());
        customer_tel.setText(value.getData().getTel());
        String urlBase= String.format("http://%s:%s/", new Object[] { SharedPreUtils.getString(this,"SP_IP"),  SharedPreUtils.getString(this,"SP_PORT") });

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(CustomerDetailContract.Presenter paramT) {

    }
}
