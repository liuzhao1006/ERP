package com.ycjt.sx.erp.workbench.bean;

import com.ycjt.sx.base.BaseBean;

import java.util.List;

/**
 * Created by liuchao on 2017/6/28.
 */

public class WorkJson extends BaseBean {


    /**
     * Code : 0
     * Data : [{"ID":"00000000-0000-0000-0000-000000000000","Name":"总额","BillTotalAmountCount":188.000000,"Year":"2017","Month":"05"}]
     * message : 获取订单数据成功
     */

    private String Code;
    private String Data;
    private String message;

    private List<WorkItemJson> dataList;

    public List<WorkItemJson> getDataList() {
        return dataList;
    }

    public void setDataList(List<WorkItemJson> dataList) {
        this.dataList = dataList;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
