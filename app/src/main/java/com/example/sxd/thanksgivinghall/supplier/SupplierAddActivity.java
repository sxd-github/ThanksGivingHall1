package com.example.sxd.thanksgivinghall.supplier;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

public class SupplierAddActivity extends BaseActivity implements SupplierAddContract.View,View.OnTouchListener {

    @BindView(R.id.bt_save)
    Button bt_save;
//    @BindView(R.id.down_userids)
//    LinearLayout down_userids;
    @BindView(R.id.supName)
    EditText supName;
    @BindView(R.id.regCapital)
    EditText regCapital;
    @BindView(R.id.legalPerson)
    EditText legalPerson;
    @BindView(R.id.supNature)
    Spinner supNature;
    @BindView(R.id.supUrl)
    EditText supUrl;
    @BindView(R.id.supAddress)
    EditText supAddress;
    @BindView(R.id.setTime)
    DatePicker dpSetTime;
    @BindView(R.id.zipCode)
    EditText zipCode;
    @BindView(R.id.contact)
    EditText contact;
    @BindView(R.id.supEmail)
    EditText supEmail;
    @BindView(R.id.supTel)
    EditText supTel;
    @BindView(R.id.faxNum)
    EditText faxNum;
    @BindView(R.id.bankName)
    Spinner bankName;
    @BindView(R.id.bankNum)
    EditText bankNum;
    @BindView(R.id.creditRating)
    EditText creditRating;
    @BindView(R.id.businessScope)
    EditText businessScope;

//    @BindView(R.id.setTime)
//    EditText setTime;

    private Spinner met_spinner,natu_spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter_natu,arr_adapter_met;
    String name,capital,person,nature,url,address,time,code,con,email,tel,faxnum,bank,banknum,credit,business="0";
    private SupplierAddContract.Presenter mPresenter;

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.supplier_add);
        ButterKnife.bind(this);
        setTitle("新增供应商");//设置标题
        setBackArrow();//设置返回按钮和点击事件

        /*银行种类选择框数据*/
        met_spinner = (Spinner) findViewById(R.id.bankName);
        data_list = new ArrayList<String>();
        data_list.add("中国农业银行");
        data_list.add("中国工商银行");
        data_list.add("中国建设银行");
        data_list.add("中国银行");
        data_list.add("中国建设银行");
        data_list.add("交通银行");

        //适配器
        arr_adapter_met= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter_met.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        met_spinner.setAdapter(arr_adapter_met);

        /*企业性质选择框数据*/
        natu_spinner = (Spinner) findViewById(R.id.supNature);
        data_list = new ArrayList<String>();
        data_list.add("国有企业");
        data_list.add("集体所有制");
        data_list.add("私营企业");
        data_list.add("股份制企业");
        data_list.add("有限合作企业");
        data_list.add("联营企业");
        data_list.add("外资投商企业");
        data_list.add("个人独资企业");

        //适配器
        arr_adapter_natu= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter_natu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        natu_spinner.setAdapter(arr_adapter_natu);
        mPresenter = new SupplierAddPresenterImpl(SupplierAddActivity.this, this);


        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initValue();
            }
        });


        /**
         * 完美解决EditText和ScrollView的滚动冲突
         */
        supName.setOnTouchListener(SupplierAddActivity.this);
    }

        @SuppressLint("NewApi")
        private void setSupDate() {
            time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            dpSetTime.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Date date=new Date(year-1900,monthOfYear,dayOfMonth);
                    String setTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    time=setTime;
                }
            });
        }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //触摸的是EditText而且当前EditText能够滚动则将事件交给EditText处理。否则将事件交由其父类处理
        if ((view.getId() == R.id.et_content && canVerticalScroll(supName))) {
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initValue() {
//        SimpleDateFormat sdf = new SimpleDateFormatmpleDateFormat("yyyyMMddHHmm", Locale.CHINA);
//        now = sdf.format(new Date());
            time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            dpSetTime.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Date date=new Date(year-1900,monthOfYear,dayOfMonth);
                    String setTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    time=setTime;
                }
            });
        credit = creditRating.getText().toString();
        business = businessScope.getText().toString();
        name = supName.getText().toString();
        address = supAddress.getText().toString();
        capital = regCapital.getText().toString();
        person = legalPerson.getText().toString();
        nature = supNature.getSelectedItem().toString();
        url = supUrl.getText().toString();
//        time = setTime.getText().toString();
        code = zipCode.getText().toString();
        con = contact.getText().toString();
        email = supEmail.getText().toString();
        tel = supTel.getText().toString();
        faxnum = faxNum.getText().toString();
        bank = bankName.getSelectedItem().toString();
        banknum = bankNum.getText().toString();
        credit = creditRating.getText().toString();
        business = businessScope.getText().toString();
        mPresenter.request(name,address,capital,person,nature,url,time,code,con,email,tel,faxnum,bank,banknum,credit,business);

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
        mPresenter.request(name,address,capital,person,nature,url,time,code,con,email,tel,faxnum,bank,banknum,credit,business);
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
