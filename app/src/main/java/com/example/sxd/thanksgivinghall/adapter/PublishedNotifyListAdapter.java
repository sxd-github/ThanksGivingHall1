package com.example.sxd.thanksgivinghall.adapter;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
import com.example.sxd.thanksgivinghall.bean.ToDoNotifyListEntity;
import com.example.sxd.thanksgivinghall.notice.ToDoNoticeActivity;

import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by 160905 on 2018/2/3.
 */

public class PublishedNotifyListAdapter extends BaseQuickAdapter<ToDoNotifyListEntity.Data, BaseViewHolder> {

    private PublishedNotifyListAdapter myAdapter;

    public PublishedNotifyListAdapter(@LayoutRes int layoutResId, @Nullable List<ToDoNotifyListEntity.Data> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToDoNotifyListEntity.Data item) {

        helper.setText(R.id.notice_title,item.getTitle())
                .setText(R.id.sender,"发布人："+item.getSend_user_name())
                .setText(R.id.send_date,"日期："+item.getSend_date());

        /**
         * 紧急通知
         */
        if(item.getUrgentFlag().equals("1")){
            //设置紧急通知的图片
            helper.setImageResource(R.id.iv_icon,R.mipmap.urgent);
        }
    }
}

