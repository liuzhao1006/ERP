package com.ycjt.sx.erp.agency.bean;

import com.ycjt.sx.base.BaseBean;

/**
 * Created by liuchao on 2017/6/22.
 * 代办搜索bean
 */

public class AgencySearchBean extends BaseBean {

    /**
     * 主题
     */
    private String theme;

    /**
     * 流程走到哪个位置
     */
    private String node;

    /**
     * 该谁审批
     */
    private String auditName;

    /**
     * 标记状态
     */
    private String state;

    /**
     * 时间戳
     */
    private long time;

    public AgencySearchBean() {
    }

    @Override
    public String toString() {
        return "AgencySearchBean{" +
                "theme='" + theme + '\'' +
                ", node='" + node + '\'' +
                ", auditName='" + auditName + '\'' +
                ", state='" + state + '\'' +
                ", time=" + time +
                '}';
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
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

    public AgencySearchBean(String theme, String node, String auditName, String state, long time) {
        this.theme = theme;
        this.node = node;
        this.auditName = auditName;
        this.state = state;
        this.time = time;
    }
}
