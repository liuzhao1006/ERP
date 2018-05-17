package com.ycjt.sx.login.bean;

/**
 * Created by liuchao on 2017/6/26.
 * 个人信息
 */

public class JsonLogin {

    public String user;
    public String createPeople;
    public String createTime;
    public String deviceID;
    public String email;
    public int id;
    public String phone;
    public String photo;
    public String position;
    public String qq;
    public String sex;
    public String telephone;
    public String userId;
    public String weChatNO;
    public String orgId;

    public JsonLogin(String orgId,String user, String createPeople, String createTime, String deviceID, String email, int id, String phone, String photo, String position, String qq, String sex, String telephone, String userId, String weChatNO) {
        this.user = user;
        this.createPeople = createPeople;
        this.createTime = createTime;
        this.deviceID = deviceID;
        this.email = email;
        this.id = id;
        this.phone = phone;
        this.photo = photo;
        this.position = position;
        this.qq = qq;
        this.sex = sex;
        this.telephone = telephone;
        this.userId = userId;
        this.weChatNO = weChatNO;
        this.orgId = orgId;
    }

    public JsonLogin() {
    }

    @Override
    public String toString() {
        return "JsonLogin{" +
                "user='" + user + '\'' +
                ", createPeople='" + createPeople + '\'' +
                ", createTime='" + createTime + '\'' +
                ", deviceID='" + deviceID + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", position='" + position + '\'' +
                ", qq='" + qq + '\'' +
                ", sex='" + sex + '\'' +
                ", telephone='" + telephone + '\'' +
                ", userId='" + userId + '\'' +
                ", weChatNO='" + weChatNO + '\'' +
                ", orgId='" + orgId + '\'' +
                '}';
    }
}
