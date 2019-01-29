package com.example.sxd.thanksgivinghall.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.TaskDetailEntity;

import java.util.List;

/**
 * Created by 160905 on 2018/2/3.
 */

public class PublishedTaskReplyListAdapter extends BaseQuickAdapter<TaskDetailEntity.Data.Reply_List, BaseViewHolder> {

    private PublishedTaskReplyListAdapter myAdapter;

    public PublishedTaskReplyListAdapter(@LayoutRes int layoutResId, @Nullable List<TaskDetailEntity.Data.Reply_List> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskDetailEntity.Data.Reply_List item) {

        helper.setText(R.id.reply_name,item.getSendUser())
                .setText(R.id.reply_date,"日期："+item.getReplyDate());

        /**
         * 已发布任务（0：未完成，a：任务已完成，2：任务不可能完成）
         */
        if(item.getReplyFlag().equals("0")){
            helper.setImageResource(R.id.iv_task,R.mipmap.ongoing);
        }else if(item.getReplyFlag().equals("1")){
            helper.setImageResource(R.id.iv_task,R.mipmap.finished);
        }else if(item.getReplyFlag().equals("2")){
            helper.setImageResource(R.id.iv_task,R.mipmap.cannotfinished);
        }
    }
}

