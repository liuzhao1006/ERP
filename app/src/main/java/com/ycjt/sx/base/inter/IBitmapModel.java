package com.ycjt.sx.base.inter;

import android.graphics.Bitmap;

/**
 * Created by liuchao on 2017/7/17.
 */

public interface IBitmapModel {

    /**
     * 链接成功,拿到数据
     *
     * @param bitmap
     * @return
     */
    void successedBit(Bitmap bitmap);

    /**
     * 链接失败,拿到失败信息
     *
     * @param s
     * @return
     */
    void failedBit(String s);

    /**
     * 添加开始进度条
     */
    void startDialogBit();

    /**
     * 停止进度条
     */
    void stopDialogBit();
}
