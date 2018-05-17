package com.ycjt.sx.widget.animal;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

/**
 * Created by liuchao on 2017/7/20.
 */

public class AnimationUtils {

    private AnimationSet set;

    public AnimationUtils() {
        set = new AnimationSet(false);
    }


    /**
     * 缩放动画  动画的属性写死了,只设置了动画时间
     */
    public ScaleAnimation setScaleAnimation(long time) {
        //缩放
        ScaleAnimation animScale = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animScale.setDuration(time);
        animScale.setFillAfter(true);
        return animScale;
    }


    /**
     * 渐变动画 动画的属性写死了,只设置了动画时间
     *
     * @return
     */
    public AlphaAnimation setAlphaAnimation(long time) {
        //渐变
        AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
        animAlpha.setDuration(time);
        animAlpha.setFillAfter(true);
        return animAlpha;
    }

    /**
     * 旋转动画 动画的属性写死了,只设置了动画时间
     *
     * @param time
     * @return
     */
    public RotateAnimation setRotateAnimation(long time) {
        //旋转
        RotateAnimation animRotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);//基于自身中心点旋转360度
        animRotate.setDuration(time);//动画时间
        animRotate.setFillAfter(true);//保持住动画结束的状态
        return animRotate;
    }


    public static void setRotateAnimationButton(Button btn,float fromDegrees, float toDegrees,int animationTime) {
        RotateAnimation animRotate = new RotateAnimation(fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);//基于自身中心点旋转360度
        animRotate.setDuration(animationTime);//动画时间
        animRotate.setFillAfter(true);//保持住动画结束的状态
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(animRotate);
        set.setFillAfter(true);
        btn.startAnimation(set);
    }

    /**
     * 旋转
     *
     * @param time
     * @return
     */
    public AnimationSet getRotateAnimation(long time) {
        set.addAnimation(setRotateAnimation(time));
        return set;
    }

    /**
     * 渐变
     */
    public AnimationSet getAlphaAnimation(long time) {
        set.addAnimation(setAlphaAnimation(time));
        return set;
    }

    /**
     * 缩放
     *
     * @param time
     * @return
     */
    public AnimationSet getScaleAnimation(long time) {
        set.addAnimation(setScaleAnimation(time));
        return set;
    }

    /**
     * 缩放+ 旋转
     *
     * @param scaleTime
     * @param rotateTime
     * @return
     */
    public AnimationSet getScaleRotateAnimation(long scaleTime, long rotateTime) {
        set.addAnimation(setScaleAnimation(scaleTime));
        set.addAnimation(setRotateAnimation(rotateTime));
        return set;
    }

    /**
     * 缩放 + 渐变
     *
     * @param scaleTime
     * @param alphaTime
     * @return
     */
    public AnimationSet getScaleAlphaAnimation(long scaleTime, long alphaTime) {
        set.addAnimation(setScaleAnimation(scaleTime));
        set.addAnimation(setAlphaAnimation(alphaTime));
        return set;
    }

    /**
     * 旋转 + 渐变
     *
     * @param rotateTime
     * @param alphaTime
     * @return
     */
    public AnimationSet getRotateAlphaAnimation(long rotateTime, long alphaTime) {
        set.addAnimation(setRotateAnimation(rotateTime));
        set.addAnimation(setAlphaAnimation(alphaTime));
        return set;
    }


    /**
     * 旋转 + 渐变 + 缩放
     *
     * @param rotateTime
     * @param alphaTime
     * @param scaleTime
     * @return
     */
    public AnimationSet getRotateAlphaScaleAnimation(long rotateTime, long alphaTime, long scaleTime) {
        set.addAnimation(setRotateAnimation(rotateTime));
        set.addAnimation(setAlphaAnimation(alphaTime));
        set.addAnimation(setScaleAnimation(scaleTime));
        return set;
    }


}
