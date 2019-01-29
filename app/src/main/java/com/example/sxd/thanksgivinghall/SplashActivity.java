package com.example.sxd.thanksgivinghall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.sxd.thanksgivinghall.login.LoginActivity;
import com.example.sxd.thanksgivinghall.utils.CacheUtils;

public class SplashActivity extends Activity {

    private RelativeLayout activity_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        activity_splash = (RelativeLayout) findViewById(R.id.activity_splash);

        //旋转动画，渐变动画，缩放动画
        RotateAnimation ra = new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        //     ra.setDuration(2000);//设置播放时长
        ra.setFillAfter(true);//设置停留在播放后的状态

        AlphaAnimation aa = new AlphaAnimation(0,1);
        //     aa.setDuration(2000);
        aa.setFillAfter(true);

        ScaleAnimation sa = new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        //     sa.setDuration(2000);
        sa.setFillAfter(true);

        //一起播放，添加进去没有先后顺序
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(ra);
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.setDuration(2000);
        activity_splash.startAnimation(set);

        //监听动画播放完成
        set.setAnimationListener(new MyAnimationListener());

    }
    class MyAnimationListener implements Animation.AnimationListener{

        /**
         * 当动画播放开始的时候回调
         * @param animation
         */
        @Override
        public void onAnimationStart(Animation animation) {

        }

        /**
         * 当动画播放完成的时候回调
         * @param animation
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this,GuideActivity.START_MAIN);

            if(isStartMain){
                //直接进入主页面
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
                startActivity(intent);
            }

            //关闭当前页面
            finish();
        }

        /**
         * 当动画重复播放的时候回调
         * @param animation
         */
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
