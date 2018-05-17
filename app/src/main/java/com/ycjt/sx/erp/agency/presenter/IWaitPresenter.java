package com.ycjt.sx.erp.agency.presenter;

import com.ycjt.sx.base.inter.IView;

/**
 * Created by liuchao on 2017/7/18.
 */

public interface IWaitPresenter extends IView {

    void waitSuccessed(String data);

    void waitFailed(String message);

    void waitMoreSuccessed(String data);

}
