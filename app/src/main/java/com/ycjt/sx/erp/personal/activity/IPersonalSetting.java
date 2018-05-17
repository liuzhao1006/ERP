package com.ycjt.sx.erp.personal.activity;

import android.graphics.Bitmap;

import com.ycjt.sx.base.inter.IView;

/**
 * Created by liuchao on 2017/6/28.
 */

public interface IPersonalSetting extends IView {
    /**
     * 成功
     *
     * @param s
     */
    void successedInfo(String s);

    /**
     * 失败
     *
     * @param s
     */
    void failedInfo(String s);

    /**
     * 获取图片
     */
    void bitMap(Bitmap bitmap);
}
