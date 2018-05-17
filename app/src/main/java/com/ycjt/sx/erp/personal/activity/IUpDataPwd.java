package com.ycjt.sx.erp.personal.activity;

import com.ycjt.sx.base.inter.IView;

/**
 * Created by liuchao on 2017/6/20.
 */

public interface IUpDataPwd extends IView {
    /**
     * 成功
     *
     * @param s
     */
    void successed(String s);

    /**
     * 失败
     *
     * @param s
     */
    void failed(String s);
}
