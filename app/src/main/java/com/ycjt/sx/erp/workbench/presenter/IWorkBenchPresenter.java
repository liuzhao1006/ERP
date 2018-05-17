package com.ycjt.sx.erp.workbench.presenter;

import com.ycjt.sx.base.inter.IView;

/**
 * 作者 : 刘朝,
 * on 2017/7/31,
 * GitHub : https://github.com/liuzhao1006
 */

public interface IWorkBenchPresenter extends IView {

    /**
     * 销售饼状图
     *
     * @param data
     */
    void successedSellData(String data);

    /**
     * 采购饼状图
     *
     * @param data
     */
    void successedOrderData(String data);

    /**
     * 销售线形图
     *
     * @param data
     */
    void successedSellDataLine(String data);

    /**
     * 采购线形图
     *
     * @param data
     */
    void successedOrderDataLine(String data);

    void failedData(int what, String message);
}
