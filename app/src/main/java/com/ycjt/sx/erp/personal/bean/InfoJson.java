package com.ycjt.sx.erp.personal.bean;

import com.ycjt.sx.base.BaseBean;

/**
 * Created by liuchao on 2017/6/28.
 */

public class InfoJson extends BaseBean {

    /**
     * message : 修改人员信息成功
     * Data : {'Id':1,'UserId':'99D7E378-0BE0-4703-B157-E88C100C5DF1','Position':'管理员','Phone':'ss','Telephone':'gf','Email':'vc','WeChatNO':'cc','QQ':'xd','Photo':'admin.jpg','Sex':' 男','CreateTime':'\/Date(1498060800000+0800)\/','CreatePeople':'管理员创建','DeviceID':'DEVICEID','OrginzeID':null,'OrginzeName':null,'UserName':null}
     * Code : 0
     */

    private String message;
    private String Data;
    private String Code;

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

    public static class InfoData{

        /**
         * Id : 1
         * UserId : 99D7E378-0BE0-4703-B157-E88C100C5DF1
         * Position : 管理员
         * Phone : ss
         * Telephone : gf
         * Email : vc
         * WeChatNO : cc
         * QQ : xd
         * Photo : admin.jpg
         * Sex :  男
         * CreateTime : /Date(1498060800000+0800)/
         * CreatePeople : 管理员创建
         * DeviceID : DEVICEID
         * OrginzeID : null
         * OrginzeName : null
         * UserName : null
         */

        private int Id;
        private String UserId;
        private String Position;
        private String Phone;
        private String Telephone;
        private String Email;
        private String WeChatNO;
        private String QQ;
        private String Photo;
        private String Sex;
        private String CreateTime;
        private String CreatePeople;
        private String DeviceID;
        private Object OrginzeID;
        private Object OrginzeName;
        private Object UserName;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getPosition() {
            return Position;
        }

        public void setPosition(String Position) {
            this.Position = Position;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getTelephone() {
            return Telephone;
        }

        public void setTelephone(String Telephone) {
            this.Telephone = Telephone;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getWeChatNO() {
            return WeChatNO;
        }

        public void setWeChatNO(String WeChatNO) {
            this.WeChatNO = WeChatNO;
        }

        public String getQQ() {
            return QQ;
        }

        public void setQQ(String QQ) {
            this.QQ = QQ;
        }

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getCreatePeople() {
            return CreatePeople;
        }

        public void setCreatePeople(String CreatePeople) {
            this.CreatePeople = CreatePeople;
        }

        public String getDeviceID() {
            return DeviceID;
        }

        public void setDeviceID(String DeviceID) {
            this.DeviceID = DeviceID;
        }

        public Object getOrginzeID() {
            return OrginzeID;
        }

        public void setOrginzeID(Object OrginzeID) {
            this.OrginzeID = OrginzeID;
        }

        public Object getOrginzeName() {
            return OrginzeName;
        }

        public void setOrginzeName(Object OrginzeName) {
            this.OrginzeName = OrginzeName;
        }

        public Object getUserName() {
            return UserName;
        }

        public void setUserName(Object UserName) {
            this.UserName = UserName;
        }
    }
}
