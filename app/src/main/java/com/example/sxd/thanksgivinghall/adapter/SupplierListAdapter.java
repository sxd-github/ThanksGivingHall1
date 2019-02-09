package com.example.sxd.thanksgivinghall.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.SupplierListEntity;

import java.util.List;

/**
 * Created by 160905 on 2018/2/3.
 */

public class SupplierListAdapter extends BaseQuickAdapter<SupplierListEntity.Data, BaseViewHolder> {

    private SupplierListAdapter myAdapter;

    public SupplierListAdapter(@LayoutRes int layoutResId, @Nullable List<SupplierListEntity.Data> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupplierListEntity.Data item) {

        helper.setText(R.id.supplier_tit,item.getSupName())
                .setText(R.id.leagelPerson,"法人："+item.getLegalPerson())
                .setText(R.id.address,"地址："+item.getSupAddress());

        /**
         * 紧急通知
         */
       /* if(item.getUrgentFlag().equals("1")){
            //设置紧急通知的图片
            helper.setImageResource(R.id.iv_icon,R.mipmap.urgent);

            //通知栏提醒
//            NotificationManager manager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                NotificationChannel channel = manager.getNotificationChannel("notify");
//                if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
//                    Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
//                    intent.putExtra(Settings.EXTRA_APP_PACKAGE, mContext.getPackageName());
//                    intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
//                    mContext.startActivity(intent);
//                    Toast.makeText(mContext, "请手动将通知打开", Toast.LENGTH_SHORT).show();
//                }
//            }
//            //自定义打开的界面
//            Intent resultIntent = new Intent(mContext, ToDoNoticeActivity.class);
//            resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
//                    resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//            Notification notification = new NotificationCompat.Builder(mContext, "notify")
//                    .setContentTitle("您收到一条紧急通知")
//                    .setContentText(item.getTitle())
//                    .setWhen(System.currentTimeMillis())
//                    .setSmallIcon(R.mipmap.urgent)
//                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.notice))
//                    .setAutoCancel(true)
//                    .setContentIntent(contentIntent)
//                    .setNumber(2)    ///在Android系统上实现未读角标的效果
//                    //调用自己提供的铃声，位于 /res/values/raw 目录下
//                  //  .setSound(Uri.parse("android.resource://com.littlejie.notification/" + R.raw.sound))
//                    //ledARGB 表示灯光颜色、 ledOnMS 亮持续时间、ledOffMS 暗的时间
//                    //.setLights(0xFF0000, 3000, 3000)
//
//                    .build();
//            manager.notify(1, notification);
        }*/
    }
}

