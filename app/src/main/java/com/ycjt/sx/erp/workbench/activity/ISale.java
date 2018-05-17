package com.ycjt.sx.erp.workbench.activity;

import com.ycjt.sx.erp.workbench.bean.WorkItemJson;

import java.util.List;

/**
 * Created by liuchao on 2017/7/7.
 */

public interface ISale {
    /**
     * 失败
     *
     * @param s
     */
    void failed(String s);

    /**
     * 销售统计成功
     *
     * @param workItemJsons
     */
    void successedSale(List<WorkItemJson> workItemJsons);

    /**
     * 采购统计失败
     *
     * @param s
     */
    void failedShop(String s);

    /**
     * 采购统计成功
     *
     * @param workItemJsons
     */
    void successedShop(List<WorkItemJson> workItemJsons);

    /**
     * 销售线形图失败
     *
     * @param s
     */
    void failedLine(String s);

    /**
     * 销售线形图成功
     *
     * @param workItemJsons
     */
    void successedSaleLine(List<WorkItemJson> workItemJsons);

    /**
     * 销售线形图失败
     *
     * @param s
     */
    void failedShopLine(String s);

    /**
     * 采购线形图成功
     *
     * @param workItemJsons
     */
    void successedShopLine(List<WorkItemJson> workItemJsons);
}
