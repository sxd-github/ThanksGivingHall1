package com.example.sxd.thanksgivinghall.notice;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.bean.ToDoNotifyDetailEntity;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待办任务列表
 */
public class NoticeDetailActivity extends BaseActivity implements NoticeDetailContract.View{


    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_isurgent)
    TextView tv_isurgent;
    @BindView(R.id.tv_sender)
    TextView tv_sender;
    @BindView(R.id.tv_send_date)
    TextView tv_send_date;

    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img3)
    ImageView img3;
    NoticeDetailContract.Presenter mPresenter;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_notice_detail);
        ButterKnife.bind(this);
        setTitle("通知详情");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new NoticeDetailPresenterImpl(NoticeDetailActivity.this, this);
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
    public void requestSuccess(final ToDoNotifyDetailEntity value) {
        tv_title.setText(value.getData().getTitle());
        tv_content.setText(value.getData().getContent());
        if(value.getData().getUrgentFlag().equals("1")) {
            tv_isurgent.setText("紧急通知");
        }else{
            tv_isurgent.setText("普通通知");
        }
        tv_sender.setText(value.getData().getSend_user_name());
        tv_send_date.setText(value.getData().getSend_date());

        String urlBase= String.format("http://%s:%s/", new Object[] { SharedPreUtils.getString(this,"SP_IP"),  SharedPreUtils.getString(this,"SP_PORT") });





        if(StringUtils.notIsBlankAndEmpty(value.getData().getImgPath1())) {
            Glide.with(this).load(urlBase + value.getData().getImgPath1()).crossFade().placeholder(R.mipmap.loading)
                    .diskCacheStrategy(DiskCacheStrategy.NONE) //禁用掉Glide的缓存功能，让占位图能有机会显示出来
                    .into(img1);
        }
        if(StringUtils.notIsBlankAndEmpty(value.getData().getImgPath2())) {
            Glide.with(this).load(urlBase + value.getData().getImgPath2()).crossFade().placeholder(R.mipmap.loading)
                    .diskCacheStrategy(DiskCacheStrategy.NONE) //禁用掉Glide的缓存功能，让占位图能有机会显示出来
                    .into(img2);
//            final ObjectAnimator anim = ObjectAnimator.ofInt(img2, "ImageLevel", 0, 10000);
//            anim.setDuration(800);
//            anim.setRepeatCount(ObjectAnimator.INFINITE);
//            anim.start();
//
//            Glide.with(this)
//                    .load(urlBase + value.getData().getImgPath2())
//                    .placeholder(R.drawable.rotate_pro)
//                    .crossFade()
//                    .listener(new RequestListener<String, GlideDrawable>() {
//                        @Override
//                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                            anim.cancel();
//                            //  Log.d(TAG, "onException: ");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                            anim.cancel();
//                            // Log.d(TAG, "onResourceReady: ");
//                            return false;
//                        }
//                    })
//                    .into(img2);
        }
        if(StringUtils.notIsBlankAndEmpty(value.getData().getImgPath3())) {
            Glide.with(this).load(urlBase + value.getData().getImgPath3()).crossFade().placeholder(R.mipmap.loading)
                    .diskCacheStrategy(DiskCacheStrategy.NONE) //禁用掉Glide的缓存功能，让占位图能有机会显示出来
                    .into(img3);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(NoticeDetailContract.Presenter paramT) {

    }
}
