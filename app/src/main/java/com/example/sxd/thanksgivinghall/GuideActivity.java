package com.example.sxd.thanksgivinghall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sxd.thanksgivinghall.login.LoginActivity;
import com.example.sxd.thanksgivinghall.utils.CacheUtils;

import java.util.ArrayList;

public class GuideActivity extends Activity implements View.OnClickListener{

    public static final String START_MAIN = "start_main";
    private ViewPager viewpager;
    private Button btn_start_main;
   // private LinearLayout ;
    private ArrayList<ImageView> imageViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        findViews();
        initData();

        //设置页面的监听
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void findViews(){
        viewpager =(ViewPager) findViewById(R.id.viewpager);
        btn_start_main =(Button) findViewById(R.id.btn_start_main);
        btn_start_main.setOnClickListener(this);
    }
    private void initData() {
        //数据的初始化
        int[] ids= new int[]{R.drawable.splash1,R.drawable.splash2,R.drawable.splash3};

        //中间变量
        imageViews =new ArrayList<>();


        for(int i=0;i<ids.length;i++){
            ImageView imageView =new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);
            imageViews.add(imageView);
        }
        //设置适配器
        viewpager.setAdapter(new MyPagerAdapter());
    }

    //实现View.OnClickListener接口
    @Override
    public void onClick(View v) {
        if(v==btn_start_main){
            // Handle clicks for btn_start_main
            //1.保存进入主页面信息
            CacheUtils.putBoolean(this,START_MAIN,true);
            //2.进入主页面
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

            //3.关闭引导页面
            finish();
        }
    }
    /**
     * ViewPager的适配器
     */
    class MyPagerAdapter extends PagerAdapter {

        /**
         * 得到总数据
         * @return
         */
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 相当于ListView的适配器中getView方法
         * 创建每一条item
         * @param container ViewPager (它的父类是ViewGroup)
         * @param position 页面的下标位置
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);//添加ViewPager
            //        return position;
            return imageView;
        }

        /**
         * 比较当前视图和object是否是同一个视图
         * @param view 当前视图View
         * @param object 是instantiateItem返回的值
         * @return
         */

        @Override
        public boolean isViewFromObject(View view, Object object) {
            //        return view==imageViews.get(Integer.parseInt((String) object));
            return view == object;
        }


        /**
         *
         * @param container ViewPager
         * @param position 哪个页面要销毁
         * @param object 要销毁的页面
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //  super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }


    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        /**
         * 当页面滚动了的时候回调
         * @param position 当前哪个页面滚动
         * @param positionOffset 页面滑动的百分比
         * @param positionOffsetPixels 页面滑动多少像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当某个页面被选中的时候回调
         * @param position 被选中页面的下标位置
         */
        @Override
        public void onPageSelected(int position) {

            if(position ==imageViews.size()-1){
                //最后一个页面
                btn_start_main.setVisibility(View.VISIBLE);
            }else{
                //隐藏
                btn_start_main.setVisibility(View.GONE);
            }
        }

        /**
         * 页面滑动的状态改变的时候回调
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }





}
