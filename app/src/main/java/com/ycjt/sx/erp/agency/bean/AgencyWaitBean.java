package com.ycjt.sx.erp.agency.bean;

import com.ycjt.sx.base.BaseBean;

import java.util.Date;

/**
 * Created by liuchao on 2017/6/19.
 */

public class AgencyWaitBean extends BaseBean {

    /**
     * 流程主题
     */
    private String theme;
    /**
     * 流程发起人
     */
    private String state;
    /**
     * 流程处理人处理时间
     */
    private long time;
    /**
     * 流程处理人
     */
    private String auditName;
    /**
     * 流程节点
     */
    private String node;

    @Override
    public String toString() {
        return "AgencyWaitBean{" +
                "theme='" + theme + '\'' +
                ", state='" + state + '\'' +
                ", time=" + time +
                ", auditName='" + auditName + '\'' +
                ", node='" + node + '\'' +
                '}';
    }

    public AgencyWaitBean() {
    }

    public AgencyWaitBean(String theme, String state, long time, String auditName, String node) {
        this.theme = theme;
        this.state = state;
        this.time = time;
        this.auditName = auditName;
        this.node = node;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }
}
