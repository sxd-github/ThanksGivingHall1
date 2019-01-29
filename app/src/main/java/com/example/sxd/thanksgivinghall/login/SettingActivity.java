package com.example.sxd.thanksgivinghall.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.POST;

public class SettingActivity extends BaseActivity{

    @BindView(R.id.et_ip)
    EditText et_ip;
    @BindView(R.id.et_port)
    EditText et_port;

    String IP;
    String PORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_setting);
        ButterKnife.bind(this);
        setTitle("系统配置");//设置标题
        setRightTitle("保存");//设置标题
        setBackArrow();//设置返回按钮和点击事件

        et_ip.setText(SharedPreUtils.getString(SettingActivity.this,Constants.SP_IP));
        et_port.setText(SharedPreUtils.getString(SettingActivity.this,Constants.SP_PORT));
    }

    @Override
    protected void setRightTitleOnClick(View v) {
        IP =et_ip.getText().toString();
        PORT =et_port.getText().toString();

        if(!StringUtils.notIsBlankAndEmpty(IP)){
            Toast.makeText(SettingActivity.this,"请输入IP",Toast.LENGTH_SHORT).show();
        }
        else if(!StringUtils.notIsBlankAndEmpty(PORT))
        {
            Toast.makeText(SettingActivity.this,"请输入PORT",Toast.LENGTH_SHORT).show();
        }
        else{
            SharedPreUtils.putString(SettingActivity.this,Constants.SP_IP,IP);
            SharedPreUtils.putString(SettingActivity.this,Constants.SP_PORT, PORT);
            finish();
        }

    }
}
