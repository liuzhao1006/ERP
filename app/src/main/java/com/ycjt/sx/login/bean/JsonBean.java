package com.ycjt.sx.login.bean;

import java.util.List;

/**
 * Created by liuchao on 2017/6/26.
 */

public class JsonBean {


    /**
     * OrganizeData : [{"ID":"04f12beb-d99d-43df-ac9a-3042957d6bda","Name":"广州亿程","Number":null,"Type":0,"Status":0,"ParentID":"00000000-0000-0000-0000-000000000000","Sort":0,"Depth":0,"ChildsLength":0,"ChargeLeader":null,"Leader":null,"Note":null},{"ID":"0e566333-70a1-420e-8625-5431386d0c7a","Name":"陕西分公司","Number":null,"Type":0,"Status":0,"ParentID":"00000000-0000-0000-0000-000000000000","Sort":0,"Depth":0,"ChildsLength":0,"ChargeLeader":null,"Leader":null,"Note":null},{"ID":"c0fc5723-e0cb-4ea0-a8dc-b5be3c55a500","Name":"广州公司","Number":null,"Type":0,"Status":0,"ParentID":"00000000-0000-0000-0000-000000000000","Sort":0,"Depth":0,"ChildsLength":0,"ChargeLeader":null,"Leader":null,"Note":null},{"ID":"e7b768ec-7312-49ea-a9df-fe2d1ebbae1a","Name":"河北分公司","Number":null,"Type":0,"Status":0,"ParentID":"00000000-0000-0000-0000-000000000000","Sort":0,"Depth":0,"ChildsLength":0,"ChargeLeader":null,"Leader":null,"Note":null}]
     * Etend : {"Id":1,"UserId":"99D7E378-0BE0-4703-B157-E88C100C5DF1","Position":"管理员","Phone":"17782935980","Telephone":"0915-8909909","Email":"888888@qq.com","WeChatNO":"88888888","QQ":"888888889","Photo":"admin.jpg","Sex":" 男","CreateTime":"\\/Date(1498060800000+0800)\\/","CreatePeople":"管理员创建","DeviceID":"22222"}
     * user : {"ID":"99d7e378-0be0-4703-b157-e88c100c5df1","Name":"admin","Account":"admin","Password":"C4CA4238A0B923820DCC509A6F75849B","Status":0,"StatusText":"正常","Sort":0,"Note":"18681835563","OrganizeName":null,"OrganizeID":"00000000-0000-0000-0000-000000000000"}
     */

    private EtendBean Etend;
    private UserBean user;
    private List<OrganizeDataBean> OrganizeData;

    public EtendBean getEtend() {
        return Etend;
    }

    public void setEtend(EtendBean Etend) {
        this.Etend = Etend;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<OrganizeDataBean> getOrganizeData() {
        return OrganizeData;
    }

    public void setOrganizeData(List<OrganizeDataBean> OrganizeData) {
        this.OrganizeData = OrganizeData;
    }

    public static class EtendBean {
        /**
         * Id : 1
         * UserId : 99D7E378-0BE0-4703-B157-E88C100C5DF1
         * Position : 管理员
         * Phone : 17782935980
         * Telephone : 0915-8909909
         * Email : 888888@qq.com
         * WeChatNO : 88888888
         * QQ : 888888889
         * Photo : admin.jpg
         * Sex :  男
         * CreateTime : \/Date(1498060800000+0800)\/
         * CreatePeople : 管理员创建
         * DeviceID : 22222
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

        @Override
        public String toString() {
            return "EtendBean{" +
                    "Id=" + Id +
                    ", UserId='" + UserId + '\'' +
                    ", Position='" + Position + '\'' +
                    ", Phone='" + Phone + '\'' +
                    ", Telephone='" + Telephone + '\'' +
                    ", Email='" + Email + '\'' +
                    ", WeChatNO='" + WeChatNO + '\'' +
                    ", QQ='" + QQ + '\'' +
                    ", Photo='" + Photo + '\'' +
                    ", Sex='" + Sex + '\'' +
                    ", CreateTime='" + CreateTime + '\'' +
                    ", CreatePeople='" + CreatePeople + '\'' +
                    ", DeviceID='" + DeviceID + '\'' +
                    '}';
        }
    }

    public static class UserBean {
        /**
         * ID : 99d7e378-0be0-4703-b157-e88c100c5df1
         * Name : admin
         * Account : admin
         * Password : C4CA4238A0B923820DCC509A6F75849B
         * Status : 0
         * StatusText : 正常
         * Sort : 0
         * Note : 18681835563
         * OrganizeName : null
         * OrganizeID : 00000000-0000-0000-0000-000000000000
         */

        private String ID;
        private String Name;
        private String Account;
        private String Password;
        private int Status;
        private String StatusText;
        private int Sort;
        private String Note;
        private Object OrganizeName;
        private String OrganizeID;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getAccount() {
            return Account;
        }

        public void setAccount(String Account) {
            this.Account = Account;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getStatusText() {
            return StatusText;
        }

        public void setStatusText(String StatusText) {
            this.StatusText = StatusText;
        }

        public int getSort() {
            return Sort;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }

        public String getNote() {
            return Note;
        }

        public void setNote(String Note) {
            this.Note = Note;
        }

        public Object getOrganizeName() {
            return OrganizeName;
        }

        public void setOrganizeName(Object OrganizeName) {
            this.OrganizeName = OrganizeName;
        }

        public String getOrganizeID() {
            return OrganizeID;
        }

        public void setOrganizeID(String OrganizeID) {
            this.OrganizeID = OrganizeID;
        }

        @Override
        public String toString() {
            return "UserBean{" +
                    "ID='" + ID + '\'' +
                    ", Name='" + Name + '\'' +
                    ", Account='" + Account + '\'' +
                    ", Password='" + Password + '\'' +
                    ", Status=" + Status +
                    ", StatusText='" + StatusText + '\'' +
                    ", Sort=" + Sort +
                    ", Note='" + Note + '\'' +
                    ", OrganizeName=" + OrganizeName +
                    ", OrganizeID='" + OrganizeID + '\'' +
                    '}';
        }
    }

    public static class OrganizeDataBean {
        @Override
        public String toString() {
            return "OrganizeDataBean{" +
                    "ID='" + ID + '\'' +
                    ", Name='" + Name + '\'' +
                    ", Number=" + Number +
                    ", Type=" + Type +
                    ", Status=" + Status +
                    ", ParentID='" + ParentID + '\'' +
                    ", Sort=" + Sort +
                    ", Depth=" + Depth +
                    ", ChildsLength=" + ChildsLength +
                    ", ChargeLeader=" + ChargeLeader +
                    ", Leader=" + Leader +
                    ", Note=" + Note +
                    '}';
        }

        /**
         * ID : 04f12beb-d99d-43df-ac9a-3042957d6bda
         * Name : 广州亿程
         * Number : null
         * Type : 0
         * Status : 0
         * ParentID : 00000000-0000-0000-0000-000000000000
         * Sort : 0
         * Depth : 0
         * ChildsLength : 0
         * ChargeLeader : null
         * Leader : null
         * Note : null
         */

        private String ID;
        private String Name;
        private Object Number;
        private int Type;
        private int Status;
        private String ParentID;
        private int Sort;
        private int Depth;
        private int ChildsLength;
        private Object ChargeLeader;
        private Object Leader;
        private Object Note;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public Object getNumber() {
            return Number;
        }

        public void setNumber(Object Number) {
            this.Number = Number;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getParentID() {
            return ParentID;
        }

        public void setParentID(String ParentID) {
            this.ParentID = ParentID;
        }

        public int getSort() {
            return Sort;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }

        public int getDepth() {
            return Depth;
        }

        public void setDepth(int Depth) {
            this.Depth = Depth;
        }

        public int getChildsLength() {
            return ChildsLength;
        }

        public void setChildsLength(int ChildsLength) {
            this.ChildsLength = ChildsLength;
        }

        public Object getChargeLeader() {
            return ChargeLeader;
        }

        public void setChargeLeader(Object ChargeLeader) {
            this.ChargeLeader = ChargeLeader;
        }

        public Object getLeader() {
            return Leader;
        }

        public void setLeader(Object Leader) {
            this.Leader = Leader;
        }

        public Object getNote() {
            return Note;
        }

        public void setNote(Object Note) {
            this.Note = Note;
        }
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "Etend=" + Etend +
                ", user=" + user +
                ", OrganizeData=" + OrganizeData +
                '}';
    }
}
