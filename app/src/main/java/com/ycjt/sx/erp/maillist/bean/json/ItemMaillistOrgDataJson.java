package com.ycjt.sx.erp.maillist.bean.json;

import com.ycjt.sx.base.BaseBean;

import java.util.List;

/**
 * Created by liuchao on 2017/6/28.
 */

public class ItemMaillistOrgDataJson extends BaseBean {


    /**
     * organizeName : 西安驻点
     * OUserList : [
     * <p>
     * {"userName":"周荣昌",
     *
     * "photo":null,
     *
     * "phone":null,
     *
     * "deptPosition":null,
     *
     * "deptUserId":"251f8bf1-b900-442a-b5ce-85a5e4a082a8",
     *
     * "deptOrganizeId":"4a2663da-92a2-4852-bce6-575cc2025bb5"}
     * <p>
     * <p>
     * ]
     */

    public static final int PARENT_ITEM = 0;//父布局
    public static final int CHILD_ITEM = 1;//子布局

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

    private int type;// 显示类型
    private boolean isExpand;// 是否展开

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }


    private String organizeName;
    private List<OUserListBean> OUserList;

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public List<OUserListBean> getOUserList() {
        return OUserList;
    }

    public void setOUserList(List<OUserListBean> OUserList) {
        this.OUserList = OUserList;
    }

    public static class OUserListBean {
        /**
         * userName : 周荣昌
         * photo : null
         * phone : null
         * deptPosition : null
         * deptUserId : 251f8bf1-b900-442a-b5ce-85a5e4a082a8
         * deptOrganizeId : 4a2663da-92a2-4852-bce6-575cc2025bb5
         */
        private int type;// 显示类型

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        private String userName;
        private Object photo;
        private Object phone;
        private Object deptPosition;
        private String deptUserId;
        private String deptOrganizeId;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getDeptPosition() {
            return deptPosition;
        }

        public void setDeptPosition(Object deptPosition) {
            this.deptPosition = deptPosition;
        }

        public String getDeptUserId() {
            return deptUserId;
        }

        public void setDeptUserId(String deptUserId) {
            this.deptUserId = deptUserId;
        }

        public String getDeptOrganizeId() {
            return deptOrganizeId;
        }

        public void setDeptOrganizeId(String deptOrganizeId) {
            this.deptOrganizeId = deptOrganizeId;
        }
    }
}
