package com.example.sxd.thanksgivinghall;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.my.MineFragment;
import com.example.sxd.thanksgivinghall.adapter.FragAdapter;
import com.example.sxd.thanksgivinghall.notice.NoticeFragment;
import com.example.sxd.thanksgivinghall.notice.ToDoNoticeActivity;
import com.example.sxd.thanksgivinghall.task.TaskFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.roughike.bottombar.TabSelectionInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Frament的主控制区
 * Created by sxd on 2018/1/31.
 */

public class MainFragment extends FragmentActivity{
    //存放Fragment
    private List<Fragment> fragmentList;
    //管理Fragment
    private FragmentManager fragmentManager;
    //实现Tab滑动效果
    private ViewPager mViewPager;
    //当前页卡编号
    private int currIndex = 0;

    //获取安卓6.0版本的位置访问权限
    private final int SDK_PERMISSION_REQUEST = 127;
    private String permissionInfo;
    private boolean hasRequestComAuth = false;
    private final static int authComRequestCode = 2;
    private final static String authComArr[] = { Manifest.permission.READ_PHONE_STATE };

    private int flag = 0;

    private Context context;
    private BottomBar bottomBar;
    private BottomBarTab nearby2,nearby3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPersimmions();
        initInfo();
    }

    private boolean hasCompletePhoneAuth() {

        PackageManager pm = this.getPackageManager();
        for (String auth : authComArr) {
            if (pm.checkPermission(auth, this.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void initInfo(){
        InitFragment();
        InitViewPager();
        InitBottomBar();

        if (Build.VERSION.SDK_INT >= 23) {
            // 保证MPUSH功能完备
            if (!hasCompletePhoneAuth()) {
                if (!hasRequestComAuth) {
                    hasRequestComAuth = true;
                    this.requestPermissions(authComArr, authComRequestCode);
                    return;
                } else {
                    Toast.makeText(MainFragment.this, "没有完备的权限!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    /**
     * 初始化BottomBar
     */
    private void InitBottomBar() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        context = this;
        nearby2 = bottomBar.getTabWithId(R.id.tab2);
        nearby3 = bottomBar.getTabWithId(R.id.tab3);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab1) {
                    // 选择指定 id 的标签
                    mViewPager.setCurrentItem(0);
                }
                if (tabId == R.id.tab2) {
                    mViewPager.setCurrentItem(1);
                }
                if (tabId == R.id.tab3) {
                    mViewPager.setCurrentItem(2);
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab2) {
                    // 已经选择了这个标签，又点击一次。即重选。
                }
            }
        });

        bottomBar.setTabSelectionInterceptor(new TabSelectionInterceptor() {
            @Override
            public boolean shouldInterceptTabSelection(@IdRes int oldTabId, @IdRes int newTabId) {
                // 点击无效
                /*if (newTabId == R.id.tab4 ) {
                    // ......
                    // 返回 true 。代表：这里我处理了，你不用管了。
                    return true;
                }*/
                return false;
            }
        });
    }

    /**
     * 初始化Fragment，并添加到List中
     */
    private void InitFragment() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new NoticeFragment());
        fragmentList.add(new TaskFragment());
        fragmentList.add(new MineFragment());
        fragmentManager = getSupportFragmentManager();
    }
    /**
     * 初始化页卡内容区
     */
    private void InitViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.vPager);
        mViewPager.setAdapter(new FragAdapter(fragmentManager, fragmentList));

        //让ViewPager缓存2个页面
        mViewPager.setOffscreenPageLimit(2);//设置向左和向右都缓存limit个页面.下面4个Tab, 只要你设置这个值为3, 那4个Tab永远都会缓存着了.

        //设置默认打开第一页
        mViewPager.setCurrentItem(0);

        //设置viewpager页面滑动监听事件
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }


    /**
     * 页卡切换监听
     * @author sxd
     * @version 1.0
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageSelected(int position) {
            switch (position){
                //当前为页卡1
                case 0:
                    break;
                //当前为页卡2
                case 1:
                    //如果页卡2接收到消息时当前页面为其他页面，点击页卡2圆点消失
                    if(flag == 2){
                        nearby2.removeBadge();
                        flag = 0;
                    }
                    break;
                //当前为页卡3
                case 2:
                    if(flag == 3){
                        nearby3.removeBadge();
                        flag = 0;
                    }
                    break;
            }
            currIndex = position;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 为23版本以上手机申请获得定位等权限
     */
    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();

            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)){
                return true;
            }else{
                permissionsList.add(permission);
                return false;
            }
        }else{
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
