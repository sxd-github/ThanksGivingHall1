package com.example.sxd.thanksgivinghall.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.MainFragment;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.LoginEntity;
import com.example.sxd.thanksgivinghall.bean.UserInfoEntity;
import com.example.sxd.thanksgivinghall.utils.Digests;
import com.example.sxd.thanksgivinghall.utils.Encodes;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    @BindView(R.id.logon_et_username)
    EditText tvUsername;
    @BindView(R.id.logon_et_password)
    EditText tvPassword;
    @BindView(R.id.logon_bt_logon)
    Button btLogOn;
    @BindView(R.id.setting)
    TextView setting;
    @BindView(R.id.login_progress)
    LinearLayout loginProgress;

    LoginContract.Presenter mPresenter;
    String userAccount;
    String password;
    public static final String TAG_EXIT = "exit";
    private boolean mIsExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);
        ButterKnife.bind(this);

        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        //将IP和PORT存入SharedPreferences
      //  SharedPreUtils.putString(LoginActivity.this, Constants.SP_IP,"192.168.2.114");
        SharedPreUtils.putString(LoginActivity.this, Constants.SP_PORT,"8080");

        //获取SharedPreferences中存储的登录账号和密码
        tvUsername.setText(SharedPreUtils.getString(LoginActivity.this, Constants.SP_LOGIN_ACCOUNT));
        tvPassword.setText(SharedPreUtils.getString(LoginActivity.this, Constants.SP_LOGIN_PASSWORD));
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SettingActivity.class));
//                View views = getLayoutInflater().inflate(R.layout.half_dialog_view, null);
//                final EditText editText = (EditText) views.findViewById(R.id.dialog_edit);
//                editText.setText(SharedPreUtils.getString(LoginActivity.this,Constants.SP_IP));
//                AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this)
//                        .setIcon(R.mipmap.icon)//设置标题的图片
//                        .setTitle("配置服务器IP地址")//设置对话框的标题
//                        .setView(views)
//                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                String content = editText.getText().toString();
//                                SharedPreUtils.putString(LoginActivity.this, Constants.SP_IP,content);
//                                Toast.makeText(LoginActivity.this, content, Toast.LENGTH_SHORT).show();
//                                dialog.dismiss();
//                            }
//                        }).create();
//                dialog.show();
            }
        });


        //初始化
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //在系统配置成功后再次获取IP地址
        mPresenter = new LoginPresenterImpl(LoginActivity.this, this);
        if(SharedPreUtils.getBoolean(LoginActivity.this, Constants.SP_LOGIN_IS_LOG_IN)){
            returnMainActivity();
            finish();
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

    private void initView() {

        btLogOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userAccount = tvUsername.getText().toString();
                password = tvPassword.getText().toString();
                mPresenter.login(userAccount,password);

            }
        });
    }


    /**
     * 判断是否登录成功
     * @param value
     */
    @Override
    public void loginSuccess(LoginEntity value) {
        if(validatePassword(password,value.getData().getPassword())) {

            //保存用户缓存
            SharedPreUtils.putString(LoginActivity.this, Constants.SP_LOGIN_ACCOUNT, userAccount);
            SharedPreUtils.putString(LoginActivity.this, Constants.SP_LOGIN_PASSWORD, password);
            SharedPreUtils.putBoolean(LoginActivity.this, Constants.SP_LOGIN_IS_LOG_IN, true);

            mPresenter.getUserInfos(SharedPreUtils.getString(LoginActivity.this, Constants.SP_LOGIN_ACCOUNT));
        }else{
            showMessage("密码错误");
        }
    }

    public boolean validatePassword(String plainPassword,String password){
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Encodes.decodeHex(password.substring(0,16));
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, 1024);
        return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
    }


    /**
     * 获取登录用户信息
     * @param value
     */
    @Override
    public void getUserInfos(UserInfoEntity value) {

        SharedPreUtils.putString(this, Constants.SP_LOGINER_ID,value.getData().getUserId());
        SharedPreUtils.putString(this, Constants.SP_LOGINER_NAME,value.getData().getName());
        SharedPreUtils.putString(this, Constants.SP_LOGINER_POSITION,value.getData().getPosition());
        SharedPreUtils.putString(this, Constants.SP_LOGINER_OFFICE_ID,value.getData().getOfficeId());
        SharedPreUtils.putString(this, Constants.SP_LOGINER_OFFICE_NAME,value.getData().getOfficeName());

        returnMainActivity();

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * 跳转到主界面
     */
    private void returnMainActivity() {
        //跳转到MainActivity界面
        Intent intent = new Intent(this, MainFragment.class);
        //跳转到的activity若已在栈中存在，则将其上的activity都销掉
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

}
