package com.ycjt.sx.erp.maillist.bean.json;

import com.ycjt.sx.base.BaseBean;

import java.util.List;

/**
 * Created by liuchao on 2017/6/28.
 */

public class MaillistJson extends BaseBean {

    /**
     * message : 获取组织机构成功
     * Data : [{'ID':'04f12beb-d99d-43df-ac9a-3042957d6bda','Name':'广州亿程','Number':'6','Type':1,'Status':0,'ParentID':'00000000-0000-0000-0000-000000000000','Sort':1,'Depth':0,'ChildsLength':8,'ChargeLeader':null,'Leader':null,'Note':null}]
     * Code : 1
     */

    private String message;
    private String Data;
    private String Code;

    private List<MaillistDataJson> maillistDataJsonList;

    @Override
    public String toString() {
        return "MaillistJson{" +
                "message='" + message + '\'' +
                ", Data='" + Data + '\'' +
                ", Code='" + Code + '\'' +
                ", maillistDataJsonList=" + maillistDataJsonList +
                '}';
    }

    public List<MaillistDataJson> getMaillistDataJsonList() {
        return maillistDataJsonList;
    }

    public void setMaillistDataJsonList(List<MaillistDataJson> maillistDataJsonList) {
        this.maillistDataJsonList = maillistDataJsonList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
