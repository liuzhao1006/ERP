package com.ycjt.sx.erp.workbench.fragment;

import com.ycjt.sx.base.inter.IView;
import com.ycjt.sx.erp.workbench.bean.WorkItemJson;

import java.util.List;

/**
 * Created by liuchao on 2017/6/28.
 */

public interface ISimple extends IView {

    /**
     * 采购,饼状图
     *
     * @param workItemJsons
     */
    void successedShop(List<WorkItemJson> workItemJsons);

    /**
     * 销售,饼状图
     *
     * @param workItemJsons
     */
    void successedSale(List<WorkItemJson> workItemJsons);

    /**
     * 销售,线形图
     *
     * @param workItemJsons
     */
    void successedSaleLine(List<WorkItemJson> workItemJsons);

    /**
     * 采购,线形图
     *
     * @param workItemJsons
     */
    void successedShopLine(List<WorkItemJson> workItemJsons);

    void failed(String s);

    void failedLine(String s);
}
