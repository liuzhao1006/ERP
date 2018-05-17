package com.ycjt.sx.erp.workbench.bean;

import com.ycjt.sx.base.BaseBean;

/**
 * Created by liuchao on 2017/6/29.
 */

public class WorkItemJson extends BaseBean {


//    public WorkItemJson(String title) {
//        this.title = title;
//    }

    /**
     * ID : 00000000-0000-0000-0000-000000000000
     * Name : 总额
     * BillTotalAmountCount : 188.0
     * Year : 2017
     * Month : 05
     */


//    public String title;
    private String ID;
    private String Name;
    private double BillTotalAmountCount;
    private String Year;
    private String Month;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getBillTotalAmountCount() {
        return BillTotalAmountCount;
    }

    public void setBillTotalAmountCount(double BillTotalAmountCount) {
        this.BillTotalAmountCount = BillTotalAmountCount;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String Month) {
        this.Month = Month;
    }

    @Override
    public String toString() {
        return "WorkItemJson{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", BillTotalAmountCount=" + BillTotalAmountCount +
                ", Year='" + Year + '\'' +
                ", Month='" + Month + '\'' +
                '}';
    }
}
