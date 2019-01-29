package com.example.sxd.thanksgivinghall.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.MainFragment;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.bean.UserInfoEntity;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePwActivity extends BaseActivity implements ChangePwContract.View{

    @BindView(R.id.et_old_password)
    EditText et_old_password;
    @BindView(R.id.et_new_password)
    EditText et_new_password;
    @BindView(R.id.et_confirm)
    EditText et_confirm;

    String userAccount;
    String password;
    String old_pw,new_pw,confirm_pw;
    public static final String TAG_EXIT = "exit";
    private boolean mIsExit;

    ChangePwContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_changepw);
        ButterKnife.bind(this);
        mPresenter = new ChangePwPresenterImpl(ChangePwActivity.this, this);
        setTitle("更改密码");//设置标题
        setRightTitle("保存");//设置标题
        setBackArrow();//设置返回按钮和点击事件

    }

    @Override
    protected void setRightTitleOnClick(View v) {
        old_pw =et_old_password.getText().toString();
        new_pw =et_new_password.getText().toString();
        confirm_pw=et_confirm.getText().toString();
        if(!StringUtils.notIsBlankAndEmpty(old_pw)){
            Toast.makeText(ChangePwActivity.this,"请输入原密码",Toast.LENGTH_SHORT).show();
        }
        else if(!StringUtils.notIsBlankAndEmpty(new_pw))
        {
            Toast.makeText(ChangePwActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
        }
        else if(!StringUtils.notIsBlankAndEmpty(confirm_pw)){
            Toast.makeText(ChangePwActivity.this,"请输入确认密码",Toast.LENGTH_SHORT).show();
        }
        else if(old_pw.equals(new_pw)){
            Toast.makeText(ChangePwActivity.this,"新密码与原密码相同",Toast.LENGTH_SHORT).show();
        }
        else if(!new_pw.equals(confirm_pw)){
            Toast.makeText(ChangePwActivity.this,"请重新输入修改后密码，两次密码不同",Toast.LENGTH_SHORT).show();
        }else{
            String userName = SharedPreUtils.getString(this,Constants.SP_LOGIN_ACCOUNT);
            mPresenter.changePw(userName,old_pw,confirm_pw);
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
            if (isExit) {
                this.finish();
            }
        }
    }

    @Override
    /**
     * 双击返回键退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void changePwSuccess(Base value) {
        showMessage("密码修改成功");
        SharedPreUtils.putString(this,Constants.SP_LOGIN_PASSWORD,confirm_pw);
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ChangePwContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
