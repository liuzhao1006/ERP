package com.ycjt.sx.erp.workbench.bean;

import com.ycjt.sx.base.BaseBean;

/**
 * Created by liuchao on 2017/6/24.
 */

public class WorkBenchChartBean extends BaseBean {

    private String money;
    private String id;
    private String data;

    public WorkBenchChartBean() {
    }

    public WorkBenchChartBean(String money, String id, String data) {
        this.money = money;
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "WorkBenchChartBean{" +
                "money='" + money + '\'' +
                ", id='" + id + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
