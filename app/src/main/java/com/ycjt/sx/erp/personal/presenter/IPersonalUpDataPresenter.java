package com.ycjt.sx.erp.personal.presenter;

import com.ycjt.sx.base.inter.IView;

/**
 * Created by liuchao on 2017/7/21.
 */

public interface IPersonalUpDataPresenter extends IView {
    void getStringSuccessed(String content);

    void getFailed(String message);

}
