package com.ycjt.sx.login.view;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.LinearLayout;

import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.widget.animal.AnimationUtils;
import com.ycjt.sx.widget.fonts.FontTextView;


public class SplashActivity extends BaseActivity {
    private LinearLayout splashAnim;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        ((FontTextView) findViewById(R.id.tv_splash_welcome)).setText("亿云, 开启移动办公之旅");
        splashAnim = (LinearLayout) findViewById(R.id.splash_anim);
        AnimationSet set = (new AnimationUtils()).getScaleAlphaAnimation(2000, 2000);
        splashAnim.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //判断有没有展示过引导页
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
