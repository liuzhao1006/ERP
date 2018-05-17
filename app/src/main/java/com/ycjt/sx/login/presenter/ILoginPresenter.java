package com.ycjt.sx.login.presenter;

import com.ycjt.sx.base.inter.IView;

import java.util.ArrayList;

/**
 * Created by liuchao on 2017/7/16.
 */

public interface ILoginPresenter extends IView {

    void getSuccessed(String content);

    void getFailed(String message);
}
