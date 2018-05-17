package com.ycjt.sx.erp.personal.fragment;

import android.graphics.Bitmap;

/**
 * Created by liuchao on 2017/6/20.
 */

public interface IPersonal {
    /**
     * 请求成功,返回json
     *
     * @param s
     */
    void successed(String s);

    /**
     * 请求失败,返回失败信息
     *
     * @param s
     */
    void failed(String s);

    void startDialog();
    void stopDialog();

    /**
     * 获取图片的方法
     * @param bitmap
     */
    void bitMap(Bitmap bitmap);
}
