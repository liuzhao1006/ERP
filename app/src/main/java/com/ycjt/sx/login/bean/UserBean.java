package com.ycjt.sx.login.bean;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.ycjt.sx.base.BaseBean;

import static com.ycjt.sx.app.GlobalConstants.ORG_ID;

/**
 * Created by liuchao on 2017/6/18.
 * 登录
 */

public class UserBean extends BaseBean {

    private String userName;//用户名
    private String passWord;//密码
    private Context context;

    public UserBean() {
    }


    public UserBean(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public UserBean(Context context) {
        this.context = context;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDeviceID() {
        return getLocalMacAddress();
    }


    public String getOrgID() {
        return ORG_ID;
    }


    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", deviceID='" + getDeviceID() + '\'' +
                ", orgID='" + getOrgID() + '\'' +
                '}';
    }

    /**
     * 获取当前设备的MAC地址
     */
    public String getLocalMacAddress() {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
//        return info.getMacAddress();
        return "DEVICEID";
    }

}
