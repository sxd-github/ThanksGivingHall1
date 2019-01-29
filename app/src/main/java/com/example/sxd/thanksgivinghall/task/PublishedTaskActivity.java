package com.example.sxd.thanksgivinghall.task;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.adapter.PublishedTaskListAdapter;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.bean.TaskListEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoTaskListEntity;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 已发布任务列表
 */
public class PublishedTaskActivity extends Fragment implements PublishedTaskContract.View{

    @BindView(R.id.rv_device_list)
    RecyclerView rvDeviceList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    Unbinder unbinder;

    private PublishedTaskListAdapter mAdapter;
    PublishedTaskContract.Presenter mPresenter;
    private int mCurrentCounter = 1;
    private int TOTAL_COUNTER = 1;

    String userid, executeTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_published_task, container, false);
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new PublishedTaskPresenterImpl(getActivity(), this);
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
        userid = SharedPreUtils.getString(getActivity(), Constants.SP_LOGINER_ID);
        mPresenter.request(userid);
    }

    @Override
    public void requestSuccess(final TaskListEntity value) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvDeviceList.setLayoutManager(layoutManager);
     //   rvDeviceList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        TOTAL_COUNTER = value.getData().size();
        if (TOTAL_COUNTER == 0) {
            rvDeviceList.setAdapter(null);
            Toast.makeText(getContext(),"暂无任何设备",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            mAdapter = new PublishedTaskListAdapter(R.layout.send_task_item, value.getData());
            mCurrentCounter = TOTAL_COUNTER;
        }
        rvDeviceList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter mAdapter, View view, int position) {
                Intent intent = new Intent(getActivity(),PublishedTaskDetailActivity.class);
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
    public void setPresenter(PublishedTaskContract.Presenter paramT) {

    }
}
