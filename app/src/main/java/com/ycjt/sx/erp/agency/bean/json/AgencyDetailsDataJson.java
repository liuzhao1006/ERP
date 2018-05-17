package com.ycjt.sx.erp.agency.bean.json;

import com.ycjt.sx.base.BaseBean;

/**
 * Created by liuchao on 2017/6/27.
 */

public class AgencyDetailsDataJson extends BaseBean {


    /**
     * StepName : 部门经理审核
     * ReceiveName : admin
     * ReceiveTime : /Date(1498570315677+0800)/
     * Title : A01_PB170627002(生产计划审批流程)
     * Status : 1
     * Id : 0f6d7689-6019-4f99-9f03-8449e3eb3e71
     * FlowID : ed9d7f46-60e3-40c8-bc79-ac363e910f38
     * InstanceID : 1023
     */

    private String StepName;
    private String ReceiveName;
    private String ReceiveTime;
    private String Title;
    private int Status;
    private String Id;
    private String FlowID;
    private String InstanceID;

    public String getStepName() {
        return StepName;
    }

    public void setStepName(String StepName) {
        this.StepName = StepName;
    }

    public String getReceiveName() {
        return ReceiveName;
    }

    public void setReceiveName(String ReceiveName) {
        this.ReceiveName = ReceiveName;
    }

    public String getReceiveTime() {
        return ReceiveTime;
    }

    public void setReceiveTime(String ReceiveTime) {
        this.ReceiveTime = ReceiveTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getFlowID() {
        return FlowID;
    }

    public void setFlowID(String FlowID) {
        this.FlowID = FlowID;
    }

    public String getInstanceID() {
        return InstanceID;
    }

    public void setInstanceID(String InstanceID) {
        this.InstanceID = InstanceID;
    }
}
