package com.example.sxd.thanksgivinghall.task;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.treelist.base.BasesActivity;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 160905 on 2018/2/3.
 */

public class TaskAddActivity extends BaseActivity implements TaskAddContract.View {

    @BindView(R.id.logon_bt_logon)
    Button logonBtLogon;
    @BindView(R.id.et_title)
    EditText et_title;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.tv_userids)
    TextView tv_userids;
    @BindView(R.id.rg1)
    RadioGroup rg1;
    @BindView(R.id.caraddView)
    ImageView caraddView;
    @BindView(R.id.caraddPeo)
    ImageView caraddPeo;
    @BindView(R.id.caraddPropaganda)
    ImageView caraddPropaganda;

    String title, content, files, sendUserId, receUserIds, urgentFlag = "0";
    String panoramaImgName, linkmanImgName, publicityImgName;
    private TaskAddContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_task_add);
        ButterKnife.bind(this);
        setTitle("新增任务");
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new TaskAddPresenterImpl(TaskAddActivity.this, this);

        tv_userids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), BasesActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        logonBtLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initValue();
            }
        });
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_normal:
                        urgentFlag = "0";
                        break;
                    case R.id.rb_urgent:
                        urgentFlag = "a";
                        break;
                }
            }
        });
    }

    @Override
    protected void setRightTitleOnClick(View v) {

    }

    public void initValue() {
        title = et_title.getText().toString();
        content = et_content.getText().toString();
        sendUserId = SharedPreUtils.getString(this, Constants.SP_LOGINER_ID);
        mPresenter.request(title, content, files, sendUserId, receUserIds, "0");
    }


    @OnClick({R.id.caraddView, R.id.caraddPeo, R.id.caraddPropaganda})
    public void onViewClicked(final View view) {
                            switch (view.getId()) {
                                case R.id.caraddView:
                                    Matisse.from(TaskAddActivity.this)
                                            .choose(MimeType.ofAll(), false)
                                            .countable(true)
                                            .capture(true) //是否提供拍照功能
                                            .captureStrategy(new CaptureStrategy(true, "com.example.sxd.thanksgivinghall.fileprovider"))//存储到哪里
                                            .maxSelectable(1)
//                                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                            .gridExpectedSize(360) //缩略图展示的大小
                                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                            .thumbnailScale(0.85f)
                                            .imageEngine(new GlideEngine())
                                            .forResult(1);
                                    break;
                                case R.id.caraddPeo:
                                    Matisse.from(TaskAddActivity.this)
                                            .choose(MimeType.ofAll(), false)
                                            .countable(true)
                                            .capture(true) //是否提供拍照功能
                                            .captureStrategy(new CaptureStrategy(true, "com.example.sxd.thanksgivinghall.fileprovider"))//存储到哪里
                                            .maxSelectable(1)
//                                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                            .gridExpectedSize(360)
                                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                            .thumbnailScale(0.85f)
                                            .imageEngine(new GlideEngine())
                                            .forResult(2);
                                    break;
                                case R.id.caraddPropaganda:
                                    Matisse.from(TaskAddActivity.this)
                                            .choose(MimeType.ofAll(), false)
                                            .countable(true)
                                            .capture(true) //是否提供拍照功能
                                            .captureStrategy(new CaptureStrategy(true, "com.example.sxd.thanksgivinghall.fileprovider"))//存储到哪里
                                            .maxSelectable(1)
//                                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                            .gridExpectedSize(360)
                                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                            .thumbnailScale(0.85f)
                                            .imageEngine(new GlideEngine())
                                            .forResult(3);
                                    break;
                            }
    }

    List<String> result = new ArrayList<>();

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取上一界面回传的接收人的id和name
        if (requestCode == 2 && resultCode == 1) {
            receUserIds = data.getStringExtra("receUserIds");
            tv_userids.setText(data.getStringExtra("userNames"));
        }

        //照片选取、拍照返回
        if (resultCode == -1) {
            if (requestCode == 1) {
                result = Matisse.obtainPathResult(data);
                Glide.with(TaskAddActivity.this).load(result.get(0))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade().into(caraddView);
                panoramaImgName = result.get(0);
//                CarAddActivity.this.grantUriPermission("com.android.center.stritl", mSelect, Intent.FLAG_GRANT_READ_URI_PERMISSION
//                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                System.out.println("panoramaImgName:" + panoramaImgName);
            }

            if (requestCode == 2) {
                result = Matisse.obtainPathResult(data);
                Glide.with(TaskAddActivity.this).load(result.get(0))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade().into(caraddPeo);
                linkmanImgName = result.get(0);

                System.out.println("linkmanImgName:" + linkmanImgName);
            }
            if (requestCode == 3) {
                result = Matisse.obtainPathResult(data);
                Glide.with(TaskAddActivity.this).load(result.get(0))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade().into(caraddPropaganda);
                publicityImgName = result.get(0);

                System.out.println("publicityImgName:" + publicityImgName);
            }
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
    public void setPresenter(TaskAddContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


}
