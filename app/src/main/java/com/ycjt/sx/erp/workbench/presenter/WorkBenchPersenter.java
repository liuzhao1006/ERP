package com.ycjt.sx.erp.workbench.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.erp.workbench.bean.WorkBenchUrlBean;
import com.ycjt.sx.erp.workbench.bean.WorkItemJson;
import com.ycjt.sx.erp.workbench.fragment.ISimple;
import com.ycjt.sx.erp.workbench.model.WorkBenchMvpModel;

import java.util.List;

import static com.ycjt.sx.erp.workbench.bean.WorkBenchUrlConfig.getWorkBenchOrderLineUrl;
import static com.ycjt.sx.erp.workbench.bean.WorkBenchUrlConfig.getWorkBenchOrderUrl;
import static com.ycjt.sx.erp.workbench.bean.WorkBenchUrlConfig.getWorkBenchSellLineUrl;
import static com.ycjt.sx.erp.workbench.bean.WorkBenchUrlConfig.getWorkBenchSellUrl;

/**
 * 作者 : 刘朝,
 * on 2017/7/31,
 * GitHub : https://github.com/liuzhao1006
 */

public class WorkBenchPersenter implements IWorkBenchPresenter {

    private Context context;
    private ISimple simple;
    private WorkBenchMvpModel model;

    public WorkBenchPersenter(Context context, ISimple simple) {
        this.context = context;
        this.simple = simple;
        model = new WorkBenchMvpModel(context, this);
    }


    /**
     * 获取销售统计,饼状图
     *
     * @param bean
     */
    public void getWorkBenchSellData(WorkBenchUrlBean bean) {
        model.getWorkBenchData(getWorkBenchSellUrl(bean), 1);
    }

    /**
     * 获取采购统计,饼状图
     *
     * @param bean
     */
    public void getWorkBenchOrderData(WorkBenchUrlBean bean) {
        model.getWorkBenchData(getWorkBenchOrderUrl(bean), 2);
    }

    /**
     * 获取销售统计,线形图
     *
     * @param bean
     */
    public void getWorkBenchSellDataLine(WorkBenchUrlBean bean) {
        model.getWorkBenchData(getWorkBenchSellLineUrl(bean), 3);
    }


    /**
     * 获取采购统计,线形图
     *
     * @param bean
     */
    public void getWorkBenchOrderDataLine(WorkBenchUrlBean bean) {
        model.getWorkBenchData(getWorkBenchOrderLineUrl(bean), 4);
    }

    @Override
    public void startDialog() {
        simple.startDialog();
    }

    @Override
    public void stopDialog() {
        simple.stopDialog();
    }

    @Override
    public void successedSellData(String data) {
        simple.successedSale(JSON.parseArray(data, WorkItemJson.class));
    }

    @Override
    public void successedOrderData(String data) {
        simple.successedSale(JSON.parseArray(data, WorkItemJson.class));
    }

    @Override
    public void successedSellDataLine(String data) {
        simple.successedSale(JSON.parseArray(data, WorkItemJson.class));
    }

    @Override
    public void successedOrderDataLine(String data) {
        simple.successedSale(JSON.parseArray(data, WorkItemJson.class));
    }

    @Override
    public void failedData(int what, String message) {
        if (what == 1 || what == 2) {
            simple.failed(message);
        }
        if (what == 3 || what == 4) {
            simple.failedLine(message);
        }
    }
}
