package com.ycjt.sx.erp.workbench.bean;

import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.utils.PrefUtils;

import static com.ycjt.sx.app.InterfaceConfig.APP_IP;
import static com.ycjt.sx.app.InterfaceConfig.APP_POST;
import static com.ycjt.sx.app.InterfaceConfig.GET_SELL_BILL_SALE;
import static com.ycjt.sx.app.InterfaceConfig.HTTP;
import static com.ycjt.sx.app.InterfaceConfig.WORKBENCH_ORDER_URL;
import static com.ycjt.sx.app.InterfaceConfig.WORKBENCH_SELL_URL;

/**
 * 作者 : 刘朝,
 * on 2017/7/31,
 * GitHub : https://github.com/liuzhao1006
 */

public class WorkBenchUrlConfig {

    private WorkBenchUrlConfig() {
    }


    /**
     * 销售统计,饼状图
     *
     * @param bean
     * @return
     */
    public static String getWorkBenchSellUrl(WorkBenchUrlBean bean) {
        return WORKBENCH_SELL_URL +
                bean.getUseId() + "/" +
                bean.getDeviceID() + "/" +
                bean.getOrginzeId() + "/" +
                bean.getYear() + "/" +
                bean.getMonth();
    }

    /**
     * 采购统计,饼状图
     *
     * @param bean
     * @return
     */
    public static String getWorkBenchOrderUrl(WorkBenchUrlBean bean) {
        return WORKBENCH_ORDER_URL +
                bean.getUseId() + "/" +
                bean.getDeviceID() + "/" +
                bean.getOrginzeId() + "/" +
                bean.getYear() + "/" +
                bean.getMonth();
    }

    /**
     * 销售统计,线形图
     *
     * @param bean
     * @return
     */
    public static String getWorkBenchSellLineUrl(WorkBenchUrlBean bean) {
        return WORKBENCH_SELL_URL +
                bean.getUseId() + "/" +
                bean.getDeviceID() + "/" +
                bean.getAllOrginzeId() + "/" +
                bean.getYear() + "/" +
                bean.getMonth();
    }

    /**
     * 采购统计,线形图
     *
     * @param bean
     * @return
     */
    public static String getWorkBenchOrderLineUrl(WorkBenchUrlBean bean) {
        return WORKBENCH_ORDER_URL +
                bean.getUseId() + "/" +
                bean.getDeviceID() + "/" +
                bean.getAllOrginzeId() + "/" +
                bean.getYear() + "/" +
                bean.getMonth();
    }

}
