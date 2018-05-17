package com.ycjt.sx.erp.maillist.bean;

import com.ycjt.sx.base.BaseBean;

/**
 * Created by liuchao on 2017/6/26.
 */

public class ItemSubsidiaryBean extends BaseBean {


    public static final int PARENT_ITEM = 0;//父布局
    public static final int CHILD_ITEM = 1;//子布局

    private int type;// 显示类型
    private ItemSubsidiaryBean childBean;

    /**
     * 设置父控件的数据
     */

    private String parentImageLogo;//父控件图标
    private String dName;//父控件部门名称
    private boolean isExpand;//父控件是否展开

    /**
     * 设置子控件的数据
     */
    private String ID;//孩子个数
    private String childImageLogo;//子控件图标
    private String cName;//子控件名称
    private String cLeader;//子控件负责人
    private String cPosition;//子控件职位
    private String cIcon;//子控件打电话图标


    public ItemSubsidiaryBean() {
    }

    public ItemSubsidiaryBean(int type, ItemSubsidiaryBean childBean, String parentImageLogo, String dName, boolean isExpand, String childImageLogo, String cName, String cLeader, String cPosition, String cIcon, String ID) {
        this.type = type;
        this.childBean = childBean;
        this.parentImageLogo = parentImageLogo;
        this.dName = dName;
        this.isExpand = isExpand;
        this.childImageLogo = childImageLogo;
        this.cName = cName;
        this.cLeader = cLeader;
        this.cPosition = cPosition;
        this.cIcon = cIcon;
        this.ID = ID;

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public static int getParentItem() {
        return PARENT_ITEM;
    }

    public static int getChildItem() {
        return CHILD_ITEM;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ItemSubsidiaryBean getChildBean() {
        return childBean;
    }

    public void setChildBean(ItemSubsidiaryBean childBean) {
        this.childBean = childBean;
    }

    public String getParentImageLogo() {
        return parentImageLogo;
    }

    public void setParentImageLogo(String parentImageLogo) {
        this.parentImageLogo = parentImageLogo;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public String getChildImageLogo() {
        return childImageLogo;
    }

    public void setChildImageLogo(String childImageLogo) {
        this.childImageLogo = childImageLogo;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcLeader() {
        return cLeader;
    }

    public void setcLeader(String cLeader) {
        this.cLeader = cLeader;
    }

    public String getcPosition() {
        return cPosition;
    }

    public void setcPosition(String cPosition) {
        this.cPosition = cPosition;
    }

    public String getcIcon() {
        return cIcon;
    }

    public void setcIcon(String cIcon) {
        this.cIcon = cIcon;
    }

    @Override
    public String toString() {
        return "ItemSubsidiaryBean{" +
                "type=" + type +
                ", childBean=" + childBean +
                ", parentImageLogo='" + parentImageLogo + '\'' +
                ", dName='" + dName + '\'' +
                ", isExpand=" + isExpand +
                ", ID='" + ID + '\'' +
                ", childImageLogo='" + childImageLogo + '\'' +
                ", cName='" + cName + '\'' +
                ", cLeader='" + cLeader + '\'' +
                ", cPosition='" + cPosition + '\'' +
                ", cIcon='" + cIcon + '\'' +
                '}';
    }
}
