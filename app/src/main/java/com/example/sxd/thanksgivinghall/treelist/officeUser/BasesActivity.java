package com.example.sxd.thanksgivinghall.treelist.officeUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.bean.OfficeUserEntity;
import com.example.sxd.thanksgivinghall.bean.UserEntity;
import com.example.sxd.thanksgivinghall.treelist.adapter.SimpleTreeRecyclerAdapter;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;
import com.multilevel.treelist.Node;
import com.multilevel.treelist.TreeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangke on 2017-a-15.
 */
public class BasesActivity extends BaseActivity implements BasesContract.View{

    protected List<Node> mDatas = new ArrayList<Node>();
    BasesContract.Presenter mPresenter;


    private TreeRecyclerAdapter mAdapter;
    int num = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_demo);
        mPresenter = new BasesPresenterImpl(BasesActivity.this, this);

        setTitle("选择接收人");
        setBackArrow();

        //获取当前用户部门id
        String id  = SharedPreUtils.getString(this,Constants.SP_LOGINER_OFFICE_ID);
        mPresenter.getUserInfos(id);
    }

    @Override
    protected void setRightTitleOnClick(View v) {
    }

    @Override
    public void getUserInfos(UserEntity value) {

        for(int i=0;i<value.getData().size();i++) {
            mDatas.add(new Node(value.getData().get(i).getId(), "","",
                    value.getData().get(i).getName(),""));
        }
        RecyclerView mTree = (RecyclerView) findViewById(R.id.recyclerview);
        mTree.setLayoutManager(new LinearLayoutManager(this));
        //第一个参数  RecyclerView
        //第二个参数  上下文
        //第三个参数  数据集
        //第四个参数  默认展开层级数 0为不展开
        //第五个参数  展开的图标
        //第六个参数  闭合的图标
        mAdapter = new SimpleTreeRecyclerAdapter(mTree, BasesActivity.this,
                mDatas, 1,R.mipmap.tree_ex,R.mipmap.tree_ec);

        mTree.setAdapter(mAdapter);

    }

    /**
     * 确认选中数据，返回选中的接收人的id和name
     */
    public void click(View v){
        StringBuilder ids = new StringBuilder();
        StringBuilder names = new StringBuilder();
        final List<Node> allNodes = mAdapter.getAllNodes();
        for (int i = 0; i < allNodes.size(); i++) {
            if (allNodes.get(i).isChecked()){
                ids.append(allNodes.get(i).getId() + ",");
                names.append(allNodes.get(i).getName() + ",");
            }
        }

        String strNodesIds = ids.toString();

        //选取接收人，不能为空
        if(strNodesIds!=null && strNodesIds!="") {
            strNodesIds = strNodesIds.substring(0, strNodesIds.length() - 1);

            String strNodesNames = names.toString();
            strNodesNames = strNodesNames.substring(0, strNodesNames.length() - 1);

            Intent intent = getIntent();
            intent.putExtra("receUserIds", strNodesIds);
            intent.putExtra("userNames", strNodesNames);
            setResult(1, intent);
            finish();
        }else{
            Toast.makeText(this,"请选择接收人",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setPresenter(BasesContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

}
