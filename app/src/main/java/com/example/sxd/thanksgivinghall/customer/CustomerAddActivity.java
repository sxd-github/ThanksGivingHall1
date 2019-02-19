package com.example.sxd.thanksgivinghall.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 160905 on 2018/2/3.
 */

public class CustomerAddActivity extends BaseActivity implements CustomerAddContract.View,View.OnTouchListener {

    @BindView(R.id.logon_bt_logon)
    Button logonBtLogon;
    @BindView(R.id.customer_tel)
    EditText et_tel;
    @BindView(R.id.customer_username)
    EditText et_username;
    @BindView(R.id.customer_sex)
    EditText et_sex;
    @BindView(R.id.customer_nation)
    EditText et_nation;
    @BindView(R.id.customer_remarks)
    EditText et_remarks;

    String username,sex,nation,tel,remarks,sendUserId,receUserIds,urgentFlag="0";
    String panoramaImgName, linkmanImgName, publicityImgName;
    private CustomerAddContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_customer_add);
        ButterKnife.bind(this);
        setTitle("新增客户信息");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new CustomerAddPresenterImpl(CustomerAddActivity.this, this);

        //发布通知
        logonBtLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initValue();
            }
        });
        /**
         * 完美解决EditText和ScrollView的滚动冲突
         */
        et_remarks.setOnTouchListener(CustomerAddActivity.this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //触摸的是EditText而且当前EditText能够滚动则将事件交给EditText处理。否则将事件交由其父类处理
        if ((view.getId() == R.id.customer_remarks && canVerticalScroll(et_remarks))) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return false;
    }

    /**
     * EditText竖直方向能否够滚动
     * @param editText  须要推断的EditText
     * @return  true：能够滚动   false：不能够滚动
     */
    private boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() -editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if(scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    @Override
    protected void setRightTitleOnClick(View v) {
    }


    public void initValue() {
//        SimpleDateFormat sdf = new SimpleDateFormatmpleDateFormat("yyyyMMddHHmm", Locale.CHINA);
//        now = sdf.format(new Date());

        username = et_username.getText().toString();
        sex=et_sex.getText().toString();
        nation=et_nation.getText().toString();
        tel = et_tel.getText().toString();
        remarks = et_remarks.getText().toString();
        mPresenter.request(username,sex,nation,tel,remarks,sendUserId,receUserIds,urgentFlag);
    }


    List<String> result = new ArrayList<>();

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取上一界面回传的接收人的id和name
        if(requestCode==1&&resultCode==1){
            receUserIds = data.getStringExtra("receUserIds");
        }

    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(Base value) {
        showMessage("发布成功");
        finish();
    }


    @Override
    public void setPresenter(CustomerAddContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
