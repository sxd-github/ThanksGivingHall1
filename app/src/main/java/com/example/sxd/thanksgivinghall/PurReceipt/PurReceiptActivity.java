package com.example.sxd.thanksgivinghall.PurReceipt;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.PurReceiptListAdapter;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.bean.PurReceiptListEntity;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 当前用户接收通知列表
 */
public class PurReceiptActivity extends Fragment implements PurRecetptContract.View{
    @BindView(R.id.rv_device_list)
    RecyclerView rvDeviceList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    Unbinder unbinder;

    private PurReceiptListAdapter mAdapter;
    PurRecetptContract.Presenter mPresenter;
    private int mCurrentCounter = 1;
    private int TOTAL_COUNTER = 1;
    String userId, executeTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_receipt, container, false);
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new PurReceiptPresenterImpl(getActivity(), this);
        /**
         * Android 8.0 通知栏的适配
         */
        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){
            String channelId="notify";
            String channelName="进货订单列表";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId,channelName,importance);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),PurReceiptAddActivity.class));
            }
        });
        initView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    public void initView(){
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                swipeLayout.setRefreshing(false);//刷新事件结束，隐藏刷新进度条
            }
        });
        rvDeviceList.setAdapter(mAdapter);
    }


    public void initData() {
        userId = SharedPreUtils.getString(getActivity(), Constants.SP_LOGINER_ID);
        mPresenter.request(userId);
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance){
        NotificationChannel channel = new NotificationChannel(channelId,channelName,importance);
        channel.setShowBadge(true);   ///在Android系统上实现未读角标的效果(从8.0系统开始，Google制定了Android系统上的角标规范，也提供了标准的API)
        NotificationManager  notificationManager = (NotificationManager)getContext().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 获取当前用户的供应商列表
     * @param value
     */
    @Override
    public void requestSuccess(final PurReceiptListEntity value) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvDeviceList.setLayoutManager(layoutManager);
//        rvDeviceList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        TOTAL_COUNTER = value.getData().size();
        if (TOTAL_COUNTER == 0) {
            rvDeviceList.setAdapter(null);
            Toast.makeText(getContext(),"暂无任何设备",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            mAdapter = new PurReceiptListAdapter(R.layout.receipt_item, value.getData());
            mCurrentCounter = TOTAL_COUNTER;
        }


        rvDeviceList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter mAdapter, View view, int position) {
                Intent intent = new Intent(getActivity(),PurReceiptDetailActivity.class);
                intent.putExtra("id", value.getData().get(position).getId());
                startActivity(intent);
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rvDeviceList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mCurrentCounter >= TOTAL_COUNTER) {
                            //数据全部加载完毕
                            mAdapter.loadMoreEnd();
                        }
                    }
                }, 800);
            }
        }, rvDeviceList);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setPresenter(PurRecetptContract.Presenter paramT) {

    }
}
