package com.example.sxd.thanksgivinghall.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.Model;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by sxd on 2018/1/30.
 */

public class HomeAdapter extends BaseQuickAdapter<Model, BaseViewHolder> {
    public HomeAdapter(@LayoutRes int layoutResId, @Nullable List<Model> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Model item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setImageResource(R.id.iv_img, item.getImgUrl());
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
}

