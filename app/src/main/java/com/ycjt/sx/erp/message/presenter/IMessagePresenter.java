package com.ycjt.sx.erp.message.presenter;

import com.ycjt.sx.base.inter.IView;

/**
 * Created by liuchao on 2017/7/17.
 */

public interface IMessagePresenter extends IView {

    void getSuccessed(String content);

    void getFailed(String message);

}
