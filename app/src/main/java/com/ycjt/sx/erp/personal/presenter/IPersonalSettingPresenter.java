package com.ycjt.sx.erp.personal.presenter;

import android.graphics.Bitmap;

import com.ycjt.sx.base.inter.IView;

/**
 * 个人设置
 */
public interface IPersonalSettingPresenter extends IView {
    void getStringSuccessed(String content);

    void getFailed(String message);

    /**
     * 获取图片
     *
     * @param bitmap
     */
    void getBitMapSuccessed(Bitmap bitmap);
}
