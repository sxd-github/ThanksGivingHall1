package com.example.sxd.thanksgivinghall.base;

/**
 * Created by sxd on 2018/2/1.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sxd.thanksgivinghall.utils.HandleBackUtil;

public abstract  class BaseFragment extends Fragment implements HandleBackInterface {


    public BaseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public void onPause() {
        super.onPause();
        clearData();
    }


    /*
    * 在Fragment的 Pause()方法中调用
    * */
    public void clearData(){

    }

    abstract public View initView();

    /*
    * 在Fragment的 onStart()方法中调用
    * */
    public void initData(){

    }
    @Override
    public boolean onBackPressed() {
        return HandleBackUtil.handleBackPress(this);
    }
}
