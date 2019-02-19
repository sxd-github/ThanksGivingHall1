package com.example.sxd.thanksgivinghall.supplier;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.SupplierDetailEntity;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待办任务列表
 */
public class SupplierDetailActivity extends BaseActivity implements SupplierDetailContract.View{


    @BindView(R.id.sup_name)
    TextView supName;
    @BindView(R.id.reg_capital)
    TextView regCapital;
    @BindView(R.id.legal_person)
    TextView legalPerson;
    @BindView(R.id.sup_nature)
    TextView supNature;
    @BindView(R.id.sup_url)
    TextView supUrl;
    @BindView(R.id.sup_address)
    TextView supAddress;
    @BindView(R.id.set_time)
    TextView setTime;
    @BindView(R.id.zip_code)
    TextView zipCode;
    @BindView(R.id.contact)
    TextView contact;
    @BindView(R.id.sup_email)
    TextView supEmail;
    @BindView(R.id.sup_tel)
    TextView supTel;
    @BindView(R.id.fax_num)
    TextView faxNum;
    @BindView(R.id.bank_name)
    TextView bankName;
    @BindView(R.id.bank_num)
    TextView bankNum;
    @BindView(R.id.credit_rating)
    TextView creditRating;
    @BindView(R.id.business_scope)
    TextView businessScope;
//    @BindView(R.id.bt_delete)
//    FloatingActionButton bt_delete;
    SupplierDetailContract.Presenter mPresenter;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.supplier_detail);
        ButterKnife.bind(this);
        setTitle("供应商详情");//设置标题
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
    public void requestSuccess(final SupplierDetailEntity value) {
        supName.setText(value.getData().getSupName());
        regCapital.setText(value.getData().getRegCapital());
        legalPerson.setText(value.getData().getLegalPerson());
        supAddress.setText(value.getData().getSupAddress());
        supNature.setText(value.getData().getSupNature());
        supUrl.setText(value.getData().getSupUrl());
        setTime.setText(value.getData().getSetTime());
        zipCode.setText(value.getData().getZipCode());
        contact.setText(value.getData().getContact());
        supEmail.setText(value.getData().getSupEmail());
        supTel.setText(value.getData().getSupTel());
        faxNum.setText(value.getData().getFaxNum());
        bankName.setText(value.getData().getBankName());
        bankNum.setText(value.getData().getBankNum());
        creditRating.setText(value.getData().getCreditRating());
        businessScope.setText(value.getData().getBusinessScope());
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
