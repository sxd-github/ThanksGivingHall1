package com.example.sxd.thanksgivinghall.mybill;

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
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sxd.thanksgivinghall.mybill.MineBillContract;
import com.example.sxd.thanksgivinghall.mybill.MineBillPresenterImpl;
//import com.example.sxd.thanksgivinghall.CustomerOrder.OrderAddActivity;
//import com.example.sxd.thanksgivinghall.CustomerOrder.OrderDetailActivity;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.MineBillListAdapter;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.bean.MineBillListEntity;
import com.example.sxd.thanksgivinghall.bean.CustomerOrderDetailEntity;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.NOTIFICATION_SERVICE;

/*import com.example.sxd.thanksgivinghall.customer.CustomerAddActivity;*/

/**
 * 账单列表
 */
public class MineBillFragment extends Fragment implements MineBillContract.View{
    @BindView(R.id.rv_device_list3)
    RecyclerView rvDeviceList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.bill_sum)
    TextView bill_sum;
    @BindView(R.id.ly_date)
    Spinner bill_date;
//    @BindView(R.id.fab_customerorder)
//    FloatingActionButton fab;
    Unbinder unbinder;

    String dateText="0";


    private MineBillListAdapter mAdapter;
    MineBillContract.Presenter mPresenter;
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
        View view = inflater.inflate(R.layout.activity_mybill, container, false);
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new MineBillPresenterImpl(getActivity(), this);
        /**
         * Android 8.0 通知栏的适配
         */
        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){
            String channelId="customer";
            String channelName="订单信息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId,channelName,importance);
        }

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),OrderAddActivity.class));
            }
        });*/
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
    }


    public void initData() {
        userId = SharedPreUtils.getString(getActivity(), Constants.SP_LOGINER_ID);
        bill_date.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                // 将所选mySpinner 的值带入myTextView 中
                dateText=bill_date.getItemAtPosition(arg2).toString();//文本说明
                mPresenter.request(userId,dateText);
//                dateText=arg2+"";
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                dateText="全年";
                mPresenter.request(userId,dateText);
            }
        });
//        mPresenter.request(userId,dateText);
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance){
        NotificationChannel channel = new NotificationChannel(channelId,channelName,importance);
        channel.setShowBadge(true);   ///在Android系统上实现未读角标的效果(从8.0系统开始，Google制定了Android系统上的角标规范，也提供了标准的API)
        NotificationManager  notificationManager = (NotificationManager)getContext().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
    /**
     * 获取客户信息
     * @param value
     */
    @Override
    public void requestSuccess(final MineBillListEntity value) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //计算合计
        double sum1 = 0;
        for(int i=0;i<value.getData().size();i++){
//          String dateText=bill_date.getText().toString();
            String dateText=bill_date.getSelectedItem().toString();
            //mPresenter.request(dateText);
            String dateFlag1=value.getData().get(i).getDate();
            String dateFlag=dateFlag1.substring(5,8);
            if(dateText.equals("全年"))
            {
                Double sumeach=Double.parseDouble(value.getData().get(i).getSumprice());
                sum1+=sumeach;
            }
            else if(dateText.equals(dateFlag)){
                Double sumeach=Double.parseDouble(value.getData().get(i).getSumprice());
                sum1+=sumeach;
            }
        }

        String sum=String.valueOf(sum1);
        bill_sum.setText(sum);
        rvDeviceList.setLayoutManager(layoutManager);
        rvDeviceList.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        TOTAL_COUNTER = value.getData().size();
        if (TOTAL_COUNTER == 0) {
            rvDeviceList.setAdapter(null);
            Toast.makeText(getContext(),"暂无任何信息",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            mAdapter = new MineBillListAdapter(R.layout.mybill_item, value.getData());
            mCurrentCounter = TOTAL_COUNTER;
        }
        rvDeviceList.setAdapter(mAdapter);
        //item监听点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter mAdapter, View view, int position) {
                /*Intent intent = new Intent(getActivity(),OrderDetailActivity.class);
                intent.putExtra("id", value.getData().get(position).getId());
                startActivity(intent);*/
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
    public void setPresenter(MineBillContract.Presenter paramT) {

    }
}
