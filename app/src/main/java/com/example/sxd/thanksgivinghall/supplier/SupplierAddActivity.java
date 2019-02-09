package com.example.sxd.thanksgivinghall.supplier;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.bean.UpLoadFile;
import com.example.sxd.thanksgivinghall.treelist.base.BasesActivity;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 160905 on 2018/2/3.
 */

public class SupplierAddActivity extends BaseActivity implements SupplierAddContract.View,View.OnTouchListener {

    @BindView(R.id.logon_bt_logon)
    Button logonBtLogon;
    @BindView(R.id.supplier_name)
    EditText et_title;
    @BindView(R.id.et_content)
    EditText et_content;

    String supName,legalPerson,supAddress,sendUserId,receUserIds,urgentFlag="0";
    String panoramaImgName, linkmanImgName, publicityImgName;
    private SupplierAddContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_supplier_add);
        ButterKnife.bind(this);
        setTitle("新增供应商信息");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new SupplierAddPresenterImpl(SupplierAddActivity.this, this);

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
        et_content.setOnTouchListener(SupplierAddActivity.this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //触摸的是EditText而且当前EditText能够滚动则将事件交给EditText处理。否则将事件交由其父类处理
        if ((view.getId() == R.id.et_content && canVerticalScroll(et_content))) {
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

        supName = et_title.getText().toString();
        legalPerson = et_content.getText().toString();
        supAddress = SharedPreUtils.getString(this, Constants.SP_LOGINER_ID);

         mPresenter.request(supName,legalPerson,supAddress,sendUserId,receUserIds,urgentFlag);
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
    public void setPresenter(SupplierAddContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
