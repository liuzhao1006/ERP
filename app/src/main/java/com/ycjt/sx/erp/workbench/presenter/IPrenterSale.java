package com.ycjt.sx.erp.workbench.presenter;

import com.ycjt.sx.base.inter.IView;

/**
 * 作者 : 刘朝,
 * on 2017/7/28,
 * GitHub : https://github.com/liuzhao1006
 */

public interface IPrenterSale extends IView {

    void successedSale(String data);

    void failedSale(String message);
}
