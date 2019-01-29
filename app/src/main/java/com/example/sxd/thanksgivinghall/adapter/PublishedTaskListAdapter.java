package com.example.sxd.thanksgivinghall.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.TaskListEntity;

import java.util.List;

/**
 * Created by 160905 on 2018/2/3.
 */

public class PublishedTaskListAdapter extends BaseQuickAdapter<TaskListEntity.Data, BaseViewHolder> {

    private PublishedTaskListAdapter myAdapter;

    public PublishedTaskListAdapter(@LayoutRes int layoutResId, @Nullable List<TaskListEntity.Data> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskListEntity.Data item) {

        helper.setText(R.id.notice_title,item.getTitle())
                .setText(R.id.send_date,"日期："+item.getSendDate())
                .setImageResource(R.id.iv_icon,R.mipmap.task)//设置任务的图片
                .addOnClickListener(R.id.iv_isforward);

        /**
         * 已发布任务（0：未完成，a：任务已完成，2：任务不可能完成）
         */
        if(item.getCompleteFlag().equals("0")){
            helper.setImageResource(R.id.iv_task,R.mipmap.ongoing);
        }else if(item.getCompleteFlag().equals("1")){
            helper.setImageResource(R.id.iv_task,R.mipmap.finished);
        }else if(item.getCompleteFlag().equals("2")){
            helper.setImageResource(R.id.iv_task,R.mipmap.cannotfinished);
        }

        if(item.getForwardFlag().equals("1"))  //转发任务
        {
            helper.setVisible(R.id.iv_isforward,true);
        }
    }
}

