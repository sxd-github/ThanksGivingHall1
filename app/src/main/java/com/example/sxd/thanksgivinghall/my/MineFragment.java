package com.example.sxd.thanksgivinghall.my;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseFragment;
import com.example.sxd.thanksgivinghall.bean.Model;
import com.example.sxd.thanksgivinghall.login.ChangePwActivity;
import com.example.sxd.thanksgivinghall.login.LoginActivity;
import com.example.sxd.thanksgivinghall.adapter.HomeAdapter;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;
import com.blankj.utilcode.utils.AppUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.qiangxi.checkupdatelibrary.Q;
import com.qiangxi.checkupdatelibrary.bean.CheckUpdateInfo;
import com.qiangxi.checkupdatelibrary.callback.CheckUpdateCallback;
import com.qiangxi.checkupdatelibrary.dialog.ForceUpdateDialog;
import com.qiangxi.checkupdatelibrary.dialog.UpdateDialog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.qiangxi.checkupdatelibrary.dialog.ForceUpdateDialog.FORCE_UPDATE_DIALOG_PERMISSION_REQUEST_CODE;
import static com.qiangxi.checkupdatelibrary.dialog.UpdateDialog.UPDATE_DIALOG_PERMISSION_REQUEST_CODE;


public class MineFragment extends BaseFragment {

    ImageView tvImg;
    TextView tvAccount;
    TextView tvCompany;
    TextView tvVersion;
    View mGoneView;// 参照物
    RecyclerView recyclerView;
    Unbinder unbinder;

    private PopupWindow mPopupWindow; // popwindow
    private View mpopview; // 弹出框的布局
    private List<Model> datas;
    private HomeAdapter adapter;
    private boolean mIsExit;
    private int[] icons = {R.mipmap.ic_change_pw, R.mipmap.ic_personal_center_update,
            R.mipmap.ic_personal_center_exit};
    private String[] title = {"修改密码", "自动更新", "注销登录"};

    CheckUpdateInfo mCheckUpdateInfo;
    ForceUpdateDialog mForceUpdateDialog;
    UpdateDialog mUpdateDialog;


    @Override
    public View initView() {
        setHasOptionsMenu(true);  //fragment如果想有menu需要这行代码
        View view = View.inflate(getActivity(), R.layout.fragment_mine, null);
        tvImg = view.findViewById(R.id.iv_img);
        tvAccount = view.findViewById(R.id.tv_name);
        tvCompany = view.findViewById(R.id.tv_company);
        tvVersion = view.findViewById(R.id.tv_version);
        mGoneView = view.findViewById(R.id.gone_view);
        recyclerView = view.findViewById(R.id.recycler_view);

        //获取用户信息展示
        getUserInfo();
        //界面布局
        initValue();

        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initValue(){
        datas = new ArrayList<>();
        Model model;
        for (int i = 0; i < 3; i++) {
            model = new Model();
            model.setTitle(title[i]);
            model.setImgUrl(icons[i]);
            datas.add(model);
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new HomeAdapter(R.layout.item_rv_mine, datas);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(getActivity(), ChangePwActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        initUpdateata();
//                        showPopupWindow(1,"发现新版本，是否进行更新？");
                        break;
                    case 2:
                        showPopupWindow(2,"是否退出用户登录？");
                        break;
                }
            }
        });
    }

    public void getUserInfo() {
        tvAccount.setText(SharedPreUtils.getString(getActivity(), Constants.SP_LOGINER_NAME));
        tvCompany.setText(SharedPreUtils.getString(getActivity(), Constants.SP_LOGINER_OFFICE_NAME));
        Glide.with(this).load(SharedPreUtils.getString(getActivity(), Constants.SP_LOGIN_USER_PORTRAIT)).into(tvImg);
    }

    public Context getThisContext() {
        return getActivity();
    }

    /**
     * 软件更新
     */
    private void initUpdateata() {
        String updateUrl = "http://updateapp.senterxljk.com.cn:8889/update/senterxj_update_2_0.json";
        if (!getResources().getConfiguration().locale.getCountry().equals("CN")) {
            updateUrl = "http://updateapp.senterxljk.com.cn:8889/update/senterxj_update_2_0_en.json";
        }
        Q.checkUpdate(Q.GET, AppUtils.getAppVersionCode(getThisContext()), updateUrl, null, new CheckUpdateCallback() {
            @Override
            public void onCheckUpdateSuccess(String result, boolean hasUpdate) {
                Log.e("tag", result);
                //result:服务端返回的json,解析成自己的实体类,当然您也可以使用checkupdatelibrary中自带的实体类CheckUpdateInfo
                mCheckUpdateInfo = new Gson().fromJson(result, CheckUpdateInfo.class);
                //有更新,显示dialog等
                if (hasUpdate) {
                    //强制更新,这里用0表示强制更新,实际情况中可与服务端商定什么数字代表强制更新即可
                    if (mCheckUpdateInfo.getIsForceUpdate() == 1) {
                        //show ForceUpdateDialog
                        forceUpdateDialogClick();
                    }
                    //非强制更新
                    else {
                        //show UpdateDialog
                        UpdateDialogClick();
                    }
                } else {
                    //无更新,提示已是最新版等
                    Toast.makeText(getActivity(),"当前版本已是最新版", Toast.LENGTH_SHORT).show();
//                    DialogUtils.showDialog(getThisContext(), getString(R.string.main_activity_prompt), getString(R.string.main_activity_prompt_update_maessage), getString(R.string.main_activity_prompt_defined), "", null, null);
                }
            }

            @Override
            public void onCheckUpdateFailure(String failureMessage, int errorCode) {
                //failureMessage:一般为try{}catch(){}捕获的异常信息
                //errorCode:暂时没什么用
            }
        });
        //模拟一些假数据
//        mCheckUpdateInfo = new CheckUpdateInfo();
//        mCheckUpdateInfo.setAppName("android检查更新库")
//                .setIsForceUpdate(0)//设置是否强制更新,该方法的参数只要和服务端商定好什么数字代表强制更新即可
//                .setNewAppReleaseTime("2016-10-14 12:37")//软件发布时间
//                .setNewAppSize(12.3f)//单位为M
//                .setNewAppUrl("http://124.133.240.101:8030/senter/apk/SenterXJ.apk")
//                .setNewAppVersionCode(20)//新app的VersionCode
//                .setNewAppVersionName("1.0.2")
//                .setNewAppUpdateDesc("a,优化下载逻辑\n2,修复一些bug\n3,完全实现强制更新与非强制更新逻辑\n4,非强制更新状态下进行下载,默认在后台进行下载\n5,当下载成功时,会在通知栏显示一个通知,点击该通知,进入安装应用界面\n6,当下载失败时,会在通知栏显示一个通知,点击该通知,会重新下载该应用\n7,当下载中,会在通知栏显示实时下载进度,但前提要dialog.setShowProgress(true).");
    }

    /**
     * 强制更新,checkupdatelibrary中提供的默认强制更新Dialog,您完全可以自定义自己的Dialog,
     */
    public void forceUpdateDialogClick() {

        mForceUpdateDialog = new ForceUpdateDialog(getThisContext(),this);//**在fragment中使用
        mForceUpdateDialog.setAppSize(mCheckUpdateInfo.getNewAppSize())
                .setDownloadUrl(mCheckUpdateInfo.getNewAppUrl())
                .setTitle(mCheckUpdateInfo.getAppName() + "有更新啦")
                .setReleaseTime(mCheckUpdateInfo.getNewAppReleaseTime())
                .setVersionName(mCheckUpdateInfo.getNewAppVersionName())
                .setUpdateDesc(mCheckUpdateInfo.getNewAppUpdateDesc())
                .setFileName("SenterXJ.apk")
                .setFilePath(Environment.getExternalStorageDirectory().getPath() + "/SenterXJ").show();
    }

    public void UpdateDialogClick() {

        mUpdateDialog = new UpdateDialog(getActivity());
        mUpdateDialog.setAppSize(mCheckUpdateInfo.getNewAppSize())
                .setDownloadUrl(mCheckUpdateInfo.getNewAppUrl())
                .setTitle(mCheckUpdateInfo.getAppName() + "有更新啦")
                .setReleaseTime(mCheckUpdateInfo.getNewAppReleaseTime())
                .setVersionName(mCheckUpdateInfo.getNewAppVersionName())
                .setUpdateDesc(mCheckUpdateInfo.getNewAppUpdateDesc())
                .setFileName("SenterXJ.apk")
                .setFilePath(Environment.getExternalStorageDirectory().getPath() + "/SenterXJ").show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //如果用户同意所请求的权限
        if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //UPDATE_DIALOG_PERMISSION_REQUEST_CODE和FORCE_UPDATE_DIALOG_PERMISSION_REQUEST_CODE这两个常量是library中定义好的
            //所以在进行判断时,必须要结合这两个常量进行判断.
            //非强制更新对话框
            if (requestCode == UPDATE_DIALOG_PERMISSION_REQUEST_CODE) {
                //进行下载操作
                mUpdateDialog.download();
            }
            //强制更新对话框
            else if (requestCode == FORCE_UPDATE_DIALOG_PERMISSION_REQUEST_CODE) {
                //进行下载操作
                mForceUpdateDialog.download();
            }
        } else {
            //用户不同意,提示用户,如下载失败,因为您拒绝了相关权限
            Toast.makeText(getActivity(), "未开启升级所需要的相关权限，请进入后台进行相关配置修改！", Toast.LENGTH_SHORT).show();
//            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                Log.e("tag", "false.请开启读写sd卡权限,不然无法正常工作");
//            } else {
//                Log.e("tag", "true.请开启读写sd卡权限,不然无法正常工作");
//            }

        }
    }


    private void showPopupWindow(final int i, String hint) {
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;// px
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        mpopview = inflater.inflate(R.layout.popup_window, null);// 加载动画布局
        TextView tv_content = mpopview.findViewById(R.id.tv_content);
        tv_content.setText(hint);
        mPopupWindow = new PopupWindow(mpopview, width, height - dip2px(50)
                + getStatusBarHeight());// 设置布局在屏幕中显示的位置，并且获取焦点
        // 设置PopupWindow的显示样式
        mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        mPopupWindow.setBackgroundDrawable(dw);
        backgroundAlpha(getActivity(), 0.5f);// 设置半透明0.0-1.0
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(false);// 设置不允许在外点击消失
        // 设置当mPopupWindow取消时，界面恢复原来的颜色 不是可透明的
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(getActivity(), 1f);// 不透明
            }
        });
//        mPopupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        mPopupWindow.showAsDropDown(mGoneView);// 弹出的mPopupWindow左上角正对mGoneView的左下角
        // 偏移量默认为0,0
        Button btn_ok = (Button) mpopview.findViewById(R.id.btn_ok);
        Button btn_cancel = (Button) mpopview.findViewById(R.id.btn_cancel);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==1){

                }else if(i==2){
                    SharedPreUtils.putBoolean(getActivity(), Constants.SP_LOGIN_IS_LOG_IN, false);
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    intent.putExtra(LoginActivity.TAG_EXIT, true);
                    startActivity(intent);
                    getActivity().onBackPressed();
                }
                mPopupWindow.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }

    /**
     * 获取状态栏高速的方法
     *
     * @return
     */
    private int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param context
     * @param bgAlpha
     *            (透明度 取值返回0-1, 0全透明,1不透明)
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {

        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow()
                .addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
