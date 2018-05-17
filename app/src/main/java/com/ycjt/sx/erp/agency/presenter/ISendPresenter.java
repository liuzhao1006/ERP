package com.ycjt.sx.erp.agency.presenter;

import com.ycjt.sx.base.inter.IView;

/**
 * Created by liuchao on 2017/7/18.
 */

public interface ISendPresenter extends IView {

    void sendSuccessed(String data);

    void sendFailed(String message);

    void sendMoreSuccessed(String data);

}
