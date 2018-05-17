package com.ycjt.sx.erp.personal.presenter;

import android.graphics.Bitmap;

import com.ycjt.sx.base.inter.IView;

/**
 * Created by liuchao on 2017/7/21.
 */

public interface IPersonalPresenter extends IView {
    void getStringSuccessed(String content);

    void getFailed(String message);

    void getBitMapSuccessed(Bitmap bitmap);
}
