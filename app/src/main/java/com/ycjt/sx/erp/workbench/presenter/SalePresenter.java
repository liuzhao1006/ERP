package com.ycjt.sx.erp.workbench.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycjt.sx.base.BaseModel;
import com.ycjt.sx.erp.workbench.activity.ISale;
import com.ycjt.sx.erp.workbench.bean.WorkItemJson;
import com.ycjt.sx.erp.workbench.bean.WorkJson;
import com.ycjt.sx.erp.workbench.fragment.ISimple;
import com.ycjt.sx.erp.workbench.model.SaleModel;
import com.ycjt.sx.erp.workbench.model.ShopModel;

import java.util.List;

/**
 * Created by liuchao on 2017/7/7.
 */

public class SalePresenter {

    private ISale sale;

    public SalePresenter(ISale sale) {
        this.sale = sale;

    }

    /**
     * 获取销售信息饼状图
     */
    public void getSale(String url) {
        SaleModel saleModel = new SaleModel(url);
        saleModel.getData();
        saleModel.setOnAddRequest(new BaseModel.OnAddRequest() {
            @Override
            public void successed(String s) {
                JSONObject jsonObject = JSON.parseObject(s);
                WorkJson workJson = JSON.parseObject(s, WorkJson.class);
                String code = workJson.getCode();
                String data = workJson.getData();
                String message = workJson.getMessage();
                if (data == null || data.equals("[]") || data.length() < 10) {
                    sale.failed("本月份没有销售信息");
                    return;
                }
                List<WorkItemJson> workItemJsons = JSON.parseArray(data, WorkItemJson.class);
                sale.successedSale(workItemJsons);
            }

            @Override
            public void failed(String s) {

            }

            @Override
            public void startDialog() {

            }

            @Override
            public void stopDialog() {

            }
        });

    }

    /**
     * 获取采购信息饼状图
     */
    public void getShop(String url) {

        ShopModel shopModel = new ShopModel(url);
        shopModel.getData();
        shopModel.setOnAddRequest(new BaseModel.OnAddRequest() {
            @Override
            public void successed(String s) {
                JSONObject jsonObject = JSON.parseObject(s);
                WorkJson workJson = JSON.parseObject(s, WorkJson.class);
                String code = workJson.getCode();
                String data = workJson.getData();
                String message = workJson.getMessage();
                if (data == null || data.equals("[]") || data.length() < 10) {
                    sale.failedShop("本月份没有销售信息");
                    return;
                }
                List<WorkItemJson> workItemJsons = JSON.parseArray(data, WorkItemJson.class);
                sale.successedShop(workItemJsons);
            }

            @Override
            public void failed(String s) {

            }

            @Override
            public void startDialog() {

            }

            @Override
            public void stopDialog() {

            }
        });
    }


    /**
     * 获取线形图销售
     *
     * @param url
     */
    public void getLineSale(String url) {
        SaleModel saleModel = new SaleModel(url);
        saleModel.getData();
        saleModel.setOnAddRequest(new BaseModel.OnAddRequest() {
            @Override
            public void successed(String s) {
                JSONObject jsonObject = JSON.parseObject(s);
                WorkJson workJson = JSON.parseObject(s, WorkJson.class);
                String code = workJson.getCode();
                String data = workJson.getData();
                String message = workJson.getMessage();
                if (data == null || data.equals("[]") || data.length() < 10) {
                    sale.failedLine("本月份没有销售信息");
                    return;
                }
                List<WorkItemJson> workItemJsons = JSON.parseArray(data, WorkItemJson.class);
                sale.successedSaleLine(workItemJsons);
            }

            @Override
            public void failed(String s) {

            }

            @Override
            public void startDialog() {

            }

            @Override
            public void stopDialog() {

            }
        });
    }

    /**
     * 获取线形图 采购
     *
     * @param url
     */
    public void getLineShop(String url) {

        ShopModel shopModel = new ShopModel(url);
        shopModel.getData();
        shopModel.setOnAddRequest(new BaseModel.OnAddRequest() {
            @Override
            public void successed(String s) {
                JSONObject jsonObject = JSON.parseObject(s);
                WorkJson workJson = JSON.parseObject(s, WorkJson.class);
                String code = workJson.getCode();
                String data = workJson.getData();
                String message = workJson.getMessage();
                if (data == null || data.equals("[]") || data.length() < 10) {
                    sale.failedShopLine(message);
                    return;
                }
                List<WorkItemJson> workItemJsons = JSON.parseArray(data, WorkItemJson.class);
                sale.successedShopLine(workItemJsons);
            }

            @Override
            public void failed(String s) {

            }

            @Override
            public void startDialog() {

            }

            @Override
            public void stopDialog() {

            }
        });
    }
}
