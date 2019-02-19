package com.example.sxd.thanksgivinghall.PurReceipt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.UpLoadFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 160905 on 2018/2/3.
 */

public class PurReceiptAddActivity extends BaseActivity implements PurReceiptAddContract.View,View.OnTouchListener {

    @BindView(R.id.bt_save)
    Button bt_save;
//    @BindView(R.id.down_userids)
//    LinearLayout down_userids;
    @BindView(R.id.supplier)
    EditText supplier;
    @BindView(R.id.goodsName)
    EditText goodsName;
    @BindView(R.id.goodsType)
    EditText goodsType;
    @BindView(R.id.unitPrice)
    EditText unitPrice;
    @BindView(R.id.goodsNum)
    EditText goodsNum;
    @BindView(R.id.totalPrice)
    EditText totalPrice;
    @BindView(R.id.rec_date)
    DatePicker dpRecDate;
    @BindView(R.id.purchasePerson)
    EditText purchasePerson;
    @BindView(R.id.payMethod)
    EditText payMethod;


//    @BindView(R.id.setTime)
//    EditText setTime;


    String sup,name,num,type,price,total,da,person,methord;
    private PurReceiptAddContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.receipt_add);
        ButterKnife.bind(this);
        setTitle("新增供应商");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new PurReceiptAddPresenterImpl(PurReceiptAddActivity.this, this);


        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initValue();
            }
        });
        setRecDate();

        /**
         * 完美解决EditText和ScrollView的滚动冲突
         */
        goodsName.setOnTouchListener(PurReceiptAddActivity.this);
    }

    @SuppressLint("NewApi")
    private void setRecDate() {
        da = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        dpRecDate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Date date = new Date(year - 1900, monthOfYear, dayOfMonth);
                String recDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                da = recDate;
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //触摸的是EditText而且当前EditText能够滚动则将事件交给EditText处理。否则将事件交由其父类处理
        if ((view.getId() == R.id.et_content && canVerticalScroll(goodsName))) {
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
        sup = supplier.getText().toString();
        name = goodsName.getText().toString();
        type= goodsType.getText().toString();
        price = unitPrice.getText().toString();
        num = goodsNum.getText().toString();
        total = totalPrice.getText().toString();
//        da = date.getText().toString();
        person = purchasePerson.getText().toString();
        methord = payMethod.getText().toString();
        mPresenter.request(sup,name,num,type,price,total,da,person,methord);

    }

    List<String> result = new ArrayList<>();

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取上一界面回传的接收人的id和name
        if(requestCode==1&&resultCode==1){
//            receUserIds = data.getStringExtra("receUserIds");
//            tv_userids.setText(data.getStringExtra("userNames"));
        }

    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uploadFileEnd(UpLoadFile value) {
        //调用新增通知接口
        mPresenter.request(sup,name,num,type,price,total,da,person,methord);
    }

    @Override
    public void requestSuccess(Base value) {
        showMessage("发布成功");
        finish();
    }


    @Override
    public void setPresenter(PurReceiptAddContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
