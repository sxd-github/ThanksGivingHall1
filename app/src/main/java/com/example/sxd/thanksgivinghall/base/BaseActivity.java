package com.example.sxd.thanksgivinghall.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sxd.thanksgivinghall.R;

/**
 * Created by sxd on 2018/a/30.
 */

public abstract class BaseActivity extends AppCompatActivity {

    /** * 通用的ToolBar标题 */
    private TextView commonTitleTv;
    /** * 通用的ToolBar右标题 */
    private TextView rightTitleTv;
    /** * 通用的ToolBar */
    private Toolbar commonTitleTb;
    /** * 内容区域 */
    private RelativeLayout content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ViewUtil.initSystemBar(this, R.color.system_bar_bg);
        setContentView(R.layout.activity_base);
        initView();
        rightClick();
        setSupportActionBar(commonTitleTb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initView() {
        commonTitleTv = (TextView) findViewById(R.id.common_title_tv);
        rightTitleTv = (TextView) findViewById(R.id.right_title_tv);
        commonTitleTb = (Toolbar) findViewById(R.id.common_title_tb);
        content = (RelativeLayout) findViewById(R.id.content);
    }

    /** * 子类调用，重新设置Toolbar * * @param layout */
    public void setToolBar(int layout) {
        hidetoolBar();
        commonTitleTb = (Toolbar) content.findViewById(layout);
        setSupportActionBar(commonTitleTb);
        //设置actionBar的标题是否显示，对应ActionBar.DISPLAY_SHOW_TITLE。
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    /**
     * 隐藏ToolBar，通过setToolBar重新定制ToolBar
     */
    public void hidetoolBar() {
        commonTitleTb.setVisibility(View.GONE);
    }

    /** * menu的点击事件 * * @param onclick */
    public void setToolBarMenuOnclick(Toolbar.OnMenuItemClickListener onclick) {
        commonTitleTb.setOnMenuItemClickListener(onclick);
    }
    /** * 设置左上角back按钮 */
    public void setBackArrow() {
        final Drawable upArrow = getResources().getDrawable(R.mipmap.back);
        //给ToolBar设置左侧的图标
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置返回按钮的点击事件
        commonTitleTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /** * 设置toolbar下面内容区域的内容 * * @param layoutId */
    public void setContentLayout(int layoutId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        content.addView(contentView, params);
    }
    /** * 设置标题 * * @param title */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            commonTitleTv.setText(title);
        }
    }

    /** * 设置标题 * * @param resId */
    public void setTitle(int resId) {
        commonTitleTv.setText(resId);
    }

    /** * 设置右标题 * * @param resId */
    public void setRightTitle(int resId) {
        rightTitleTv.setText(resId);
    }

    /** * 设置右标题 * * @param title */
    public void setRightTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            rightTitleTv.setText(title);
        }
    }

    public void setRightTitleSize(int size) {
        rightTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
    }

    private void rightClick(){
        rightTitleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRightTitleOnClick(v);
            }
        });
    }

    protected abstract void setRightTitleOnClick(View v);

}