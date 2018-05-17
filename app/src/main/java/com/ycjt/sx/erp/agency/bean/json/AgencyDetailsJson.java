package com.ycjt.sx.erp.agency.bean.json;

import com.ycjt.sx.base.BaseBean;

import java.util.List;

/**
 * Created by liuchao on 2017/6/27.
 */

public class AgencyDetailsJson extends BaseBean {


    /**
     * Data : [{'StepName':'部门经理审核','ReceiveName':'admin','ReceiveTime':'\/Date(1498570315677+0800)\/','Title':'A01_PB170627002(生产计划审批流程)','Status':1,'Id':'0f6d7689-6019-4f99-9f03-8449e3eb3e71','FlowID':'ed9d7f46-60e3-40c8-bc79-ac363e910f38','InstanceID':'1023'},{'StepName':'制单','ReceiveName':'admin','ReceiveTime':'\/Date(1498570276603+0800)\/','Title':'null(生产计划审批流程)','Status':2,'Id':'23ee00ff-3aea-4c2c-8f73-2aba22657926','FlowID':'ed9d7f46-60e3-40c8-bc79-ac363e910f38','InstanceID':'1023'},{'StepName':'部门经理审核','ReceiveName':'admin','ReceiveTime':'\/Date(1498570246493+0800)\/','Title':'A01_PB170627002(生产计划审批流程)','Status':3,'Id':'643b92fd-1082-4b13-8316-75224ae0f887','FlowID':'ed9d7f46-60e3-40c8-bc79-ac363e910f38','InstanceID':'1023'},{'StepName':'制单','ReceiveName':'admin','ReceiveTime':'\/Date(1498570235100+0800)\/','Title':'null(生产计划审批流程)','Status':2,'Id':'56680a7f-00f1-4d3a-b229-866a8f0ff20f','FlowID':'ed9d7f46-60e3-40c8-bc79-ac363e910f38','InstanceID':'1023'},{'StepName':'部门经理审核','ReceiveName':'admin','ReceiveTime':'\/Date(1498556521633+0800)\/','Title':'A01_PB170627002(生产计划审批流程)','Status':3,'Id':'48706c41-498b-4c53-82b1-ba587b02c2d1','FlowID':'ed9d7f46-60e3-40c8-bc79-ac363e910f38','InstanceID':'1023'},{'StepName':'制单','ReceiveName':'admin','ReceiveTime':'\/Date(1498556497320+0800)\/','Title':'null(生产计划审批流程)','Status':2,'Id':'849e1460-2957-443f-85d9-b57bdafc4b88','FlowID':'ed9d7f46-60e3-40c8-bc79-ac363e910f38','InstanceID':'1023'},{'StepName':'部门经理审核','ReceiveName':'admin','ReceiveTime':'\/Date(1498556289740+0800)\/','Title':'A01_PB170627002(生产计划审批流程)','Status':3,'Id':'84f9a21a-601b-44b6-b44b-e6be10c8fa35','FlowID':'ed9d7f46-60e3-40c8-bc79-ac363e910f38','InstanceID':'1023'},{'StepName':'制单','ReceiveName':'admin','ReceiveTime':'\/Date(1498556276497+0800)\/','Title':'null(生产计划审批流程)','Status':2,'Id':'b9829cc7-ed51-4fd6-a017-24e17e1f875d','FlowID':'ed9d7f46-60e3-40c8-bc79-ac363e910f38','InstanceID':'1023'},{'StepName':'部门经理审核','ReceiveName':'admin','ReceiveTime':'\/Date(1498556261070+0800)\/','Title':'null(生产计划审批流程)','Status':3,'Id':'0d24a69e-ee57-4400-ad49-a39ecfd69afd','FlowID':'ed9d7f46-60e3-40c8-bc79-ac363e910f38','InstanceID':'1023'},{'StepName':'制单','ReceiveName':'admin','ReceiveTime':'\/Date(1498556261037+0800)\/','Title':'null(生产计划审批流程)','Status':2,'Id':'872802e5-1776-4c10-aae7-0cf7e57888d3','FlowID':'ed9d7f46-60e3-40c8-bc79-ac363e910f38','InstanceID':'1023'}]
     * Code : 0
     * message : 获取审批步骤数据成功
     */

    private String Data;
    private String Code;
    private String message;
    private List<AgencyDetailsDataJson> dataList ;

    @Override
    public String toString() {
        return "AgencyDetailsJson{" +
                "Data='" + Data + '\'' +
                ", Code='" + Code + '\'' +
                ", message='" + message + '\'' +
                ", dataList=" + dataList +
                '}';
    }

    public List<AgencyDetailsDataJson> getDataList() {
        return dataList;
    }

    public void setDataList(List<AgencyDetailsDataJson> dataList) {
        this.dataList = dataList;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
