package com.ycjt.sx.erp.agency.presenter;

import com.ycjt.sx.base.inter.IView;

/**
 * Created by liuchao on 2017/7/18.
 */

public interface ICompletePresenter extends IView {

    void completeSuccessed(String data);

    void completeFailed(String message);

    void completeMoreSuccessed(String data);

}
