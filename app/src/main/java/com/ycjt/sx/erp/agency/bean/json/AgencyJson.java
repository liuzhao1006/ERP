package com.ycjt.sx.erp.agency.bean.json;

import com.ycjt.sx.base.BaseBean;

import java.util.List;

/**
 * Created by liuchao on 2017/6/27.
 */

public class AgencyJson extends BaseBean{


    /**
     * Code : 0
     * Data : [{"orderStyle":"待办","Status":"1","Title":"undefined(库存预留审批流程)","SenderTime":"2017/6/27 11:36:16","position":"管理员","SenderName":"admin","Comment":"9","FlowID":"d04a3f71-dc88-41e4-8909-ebf4888e58ee","InstanceID":null},{"orderStyle":"待办","Status":"0","Title":"undefined(库存预留审批流程)","SenderTime":"2017/6/27 11:33:39","position":"管理员","SenderName":"admin","Comment":"8","FlowID":"d04a3f71-dc88-41e4-8909-ebf4888e58ee","InstanceID":null},{"orderStyle":"待办","Status":"1","Title":"A01_CSB170627003 (预约维护)","SenderTime":"2017/6/27 10:45:41","position":"管理员","SenderName":"admin","Comment":"3","FlowID":"d6f76b50-e7b4-4a1b-889c-87b0c8b42a24","InstanceID":null},{"orderStyle":"待办","Status":"1","Title":"预约维护","SenderTime":"2017/6/27 10:23:54","position":"管理员","SenderName":"admin","Comment":"2","FlowID":"d6f76b50-e7b4-4a1b-889c-87b0c8b42a24","InstanceID":null},{"orderStyle":"待办","Status":"1","Title":"undefined(库存预留审批流程)","SenderTime":"2017/6/27 10:14:08","position":"管理员","SenderName":"admin","Comment":"7","FlowID":"d04a3f71-dc88-41e4-8909-ebf4888e58ee","InstanceID":null},{"orderStyle":"已办","Status":"2","Title":"undefined(库存预留审批流程)","SenderTime":"2017/6/27 11:36:16","position":"管理员","SenderName":"admin","Comment":"9","FlowID":"d04a3f71-dc88-41e4-8909-ebf4888e58ee","InstanceID":null},{"orderStyle":"已办","Status":"2","Title":"undefined(库存预留审批流程)","SenderTime":"2017/6/27 11:33:39","position":"管理员","SenderName":"admin","Comment":"8","FlowID":"d04a3f71-dc88-41e4-8909-ebf4888e58ee","InstanceID":null},{"orderStyle":"已办","Status":"2","Title":"undefined(库存预留审批流程)","SenderTime":"2017/6/27 10:14:08","position":"管理员","SenderName":"admin","Comment":"7","FlowID":"d04a3f71-dc88-41e4-8909-ebf4888e58ee","InstanceID":null},{"orderStyle":"已办","Status":"2","Title":"借用出库审批流程","SenderTime":"2017/6/26 22:00:33","position":"管理员","SenderName":"admin","Comment":"1057","FlowID":"88f8a3d2-4d61-4ba2-8e07-63e0b7476493","InstanceID":null},{"orderStyle":"已办","Status":"2","Title":"123_(申配单)","SenderTime":"2017/6/26 21:59:23","position":"管理员","SenderName":"admin","Comment":"2061","FlowID":"46abe8c9-a985-4976-8624-b59696f3485f","InstanceID":null},{"orderStyle":"由我发起","Status":"2","Title":"undefined(库存预留审批流程)","SenderTime":"2017/6/27 11:36:16","position":"管理员","SenderName":"admin","Comment":"9","FlowID":"d04a3f71-dc88-41e4-8909-ebf4888e58ee","InstanceID":null},{"orderStyle":"由我发起","Status":"2","Title":"undefined(库存预留审批流程)","SenderTime":"2017/6/27 11:33:39","position":"管理员","SenderName":"admin","Comment":"8","FlowID":"d04a3f71-dc88-41e4-8909-ebf4888e58ee","InstanceID":null},{"orderStyle":"由我发起","Status":"1","Title":"A01_CSB170627003 (预约维护)","SenderTime":"2017/6/27 10:45:41","position":"管理员","SenderName":"admin","Comment":"3","FlowID":"d6f76b50-e7b4-4a1b-889c-87b0c8b42a24","InstanceID":null},{"orderStyle":"由我发起","Status":"1","Title":"预约维护","SenderTime":"2017/6/27 10:23:54","position":"管理员","SenderName":"admin","Comment":"2","FlowID":"d6f76b50-e7b4-4a1b-889c-87b0c8b42a24","InstanceID":null},{"orderStyle":"由我发起","Status":"2","Title":"undefined(库存预留审批流程)","SenderTime":"2017/6/27 10:14:08","position":"管理员","SenderName":"admin","Comment":"7","FlowID":"d04a3f71-dc88-41e4-8909-ebf4888e58ee","InstanceID":null}]
     * message : 请求主页加载数据成功
     */

    private String Code;
    private List<AgencyDataJson> dataList ;
    private String message;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public List<AgencyDataJson> getDataList() {
        return dataList;
    }

    public void setDataList(List<AgencyDataJson> dataList) {
        this.dataList = dataList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class AgencyDataJson extends BaseBean{

        /**
         * orderStyle : 待办
         * Status : 1
         * Title : undefined(库存预留审批流程)
         * SenderTime : 2017/6/27 11:36:16
         * position : 管理员
         * SenderName : admin
         * Comment : 9
         * FlowID : d04a3f71-dc88-41e4-8909-ebf4888e58ee
         * InstanceID : null
         */


        private String orderStyle;
        private String Status;
        private String Title;
        private String SenderTime;
        private String position;
        private String SenderName;
        private String Comment;
        private String FlowID;
        private Object InstanceID;

        public String getOrderStyle() {
            return orderStyle;
        }

        public void setOrderStyle(String orderStyle) {
            this.orderStyle = orderStyle;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getSenderTime() {
            return SenderTime;
        }

        public void setSenderTime(String SenderTime) {
            this.SenderTime = SenderTime;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getSenderName() {
            return SenderName;
        }

        public void setSenderName(String SenderName) {
            this.SenderName = SenderName;
        }

        public String getComment() {
            return Comment;
        }

        public void setComment(String Comment) {
            this.Comment = Comment;
        }

        public String getFlowID() {
            return FlowID;
        }

        public void setFlowID(String FlowID) {
            this.FlowID = FlowID;
        }

        public Object getInstanceID() {
            return InstanceID;
        }

        public void setInstanceID(Object InstanceID) {
            this.InstanceID = InstanceID;
        }

        @Override
        public String toString() {
            return "AgencyDataJson{" +
                    "orderStyle='" + orderStyle + '\'' +
                    ", Status='" + Status + '\'' +
                    ", Title='" + Title + '\'' +
                    ", SenderTime='" + SenderTime + '\'' +
                    ", position='" + position + '\'' +
                    ", SenderName='" + SenderName + '\'' +
                    ", Comment='" + Comment + '\'' +
                    ", FlowID='" + FlowID + '\'' +
                    ", InstanceID=" + InstanceID +
                    '}';
        }
    }
}
