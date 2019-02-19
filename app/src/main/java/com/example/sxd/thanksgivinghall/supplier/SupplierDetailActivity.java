package com.example.sxd.thanksgivinghall.supplier;

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
import com.example.sxd.thanksgivinghall.bean.SupplierEntity;
import com.example.sxd.thanksgivinghall.bean.SupplierListEntity;
import com.example.sxd.thanksgivinghall.supplier.SupplierDetailContract;
import com.example.sxd.thanksgivinghall.supplier.SupplierDetailPresenterImpl;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 供应商详细信息
 */
public class SupplierDetailActivity extends BaseActivity implements SupplierDetailContract.View{


    @BindView(R.id.tv_supplier_title)
    TextView tv_title;
    @BindView(R.id.tv_leagelPerson)
    TextView tv_leagelPerson;
    @BindView(R.id.tv_address)
    TextView tv_address;

    SupplierDetailContract.Presenter mPresenter;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_supplier_detail);
        ButterKnife.bind(this);
        setTitle("供应商详细信息");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new SupplierDetailPresenterImpl(SupplierDetailActivity.this, this);
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
    public void requestSuccess(final SupplierEntity value) {
        tv_title.setText(value.getData().getSupName());
        tv_leagelPerson.setText(value.getData().getLegalPerson());
        tv_address.setText(value.getData().getSupAddress());

        String urlBase= String.format("http://%s:%s/", new Object[] { SharedPreUtils.getString(this,"SP_IP"),  SharedPreUtils.getString(this,"SP_PORT") });

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(SupplierDetailContract.Presenter paramT) {

    }
}
