package com.ycjt.sx.erp.workbench.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycjt.sx.base.BaseModel;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson;
import com.ycjt.sx.erp.workbench.bean.WorkItemJson;
import com.ycjt.sx.erp.workbench.bean.WorkJson;
import com.ycjt.sx.erp.workbench.fragment.ISimple;
import com.ycjt.sx.erp.workbench.model.SimpleModel;

import java.util.List;

/**
 * Created by liuchao on 2017/6/28.
 */

public class WorkDataPresenter {

    private ISimple simple;

    public WorkDataPresenter(ISimple simple) {
        this.simple = simple;

    }


    /**
     * 销售订单
     *
     * @param url
     */
    public void getSimpleSale(String url) {

        SimpleModel model = new SimpleModel(url);
        model.getData();
        model.setOnAddRequest(new BaseModel.OnAddRequest() {
            @Override
            public void successed(String s) {

                JSONObject jsonObject = JSON.parseObject(s);
                WorkJson workJson = JSON.parseObject(s, WorkJson.class);
                String code = workJson.getCode();
                String data = workJson.getData();
                String message = workJson.getMessage();
                if (data == null ||data.equals("[]") || data.length() < 10) {
                    simple.failed("本月份没有销售信息");
                    return;
                }
                List<WorkItemJson> workItemJsons = JSON.parseArray(data, WorkItemJson.class);
                simple.successedSale(workItemJsons);


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
     * 采购订单
     *
     * @param url
     */
    public void getSimpleShop(String url) {

        SimpleModel model = new SimpleModel(url);
        model.getData();
        model.setOnAddRequest(new BaseModel.OnAddRequest() {
            @Override
            public void successed(String s) {
                JSONObject jsonObject = JSON.parseObject(s);
                WorkJson workJson = JSON.parseObject(s, WorkJson.class);
                String code = workJson.getCode();
                String data = workJson.getData();
                String message = workJson.getMessage();
                if (data == null || data.equals("[]") || data.length() < 10) {
                    return;
                }
                List<WorkItemJson> workItemJsons = JSON.parseArray(data, WorkItemJson.class);
                simple.successedShop(workItemJsons);

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
