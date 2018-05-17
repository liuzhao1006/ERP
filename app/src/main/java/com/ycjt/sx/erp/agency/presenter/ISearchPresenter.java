package com.ycjt.sx.erp.agency.presenter;

import com.ycjt.sx.base.inter.IView;

/**
 * Created by liuchao on 2017/7/18.
 */

public interface ISearchPresenter extends IView {

    void searchSuccessed(String data);

    void searchFailed(String message);

    void searchMoreSuccessed(String data);

}
