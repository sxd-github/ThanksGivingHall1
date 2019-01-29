package com.example.sxd.thanksgivinghall.adapter;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.ToDoTaskDetailEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoTaskListEntity;

import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by 160905 on 2018/2/3.
 */

public class ToDoTaskReplyListAdapter extends BaseQuickAdapter<ToDoTaskDetailEntity.Data.Reply_List, BaseViewHolder> {

    private ToDoTaskReplyListAdapter myAdapter;

    public ToDoTaskReplyListAdapter(@LayoutRes int layoutResId, @Nullable List<ToDoTaskDetailEntity.Data.Reply_List> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToDoTaskDetailEntity.Data.Reply_List item) {

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

//        if(item.getForwardFlag().equals("a"))  //转发任务
//        {
//            helper.setVisible(R.id.iv_isforward,true);
//        }
    }
}

