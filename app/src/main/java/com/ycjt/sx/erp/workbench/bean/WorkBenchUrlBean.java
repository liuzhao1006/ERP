package com.ycjt.sx.erp.workbench.bean;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.utils.PrefUtils;

import static com.ycjt.sx.app.InterfaceConfig.APP_ORG_ID;

/**
 * 作者 : 刘朝,
 * on 2017/7/31,
 * GitHub : https://github.com/liuzhao1006
 */

public class WorkBenchUrlBean {

    /**
     * 用户id
     */
    private String useId;
    /**
     * 设备标识符
     */
    private String deviceID;
    /**
     * 组织机构id
     */
    private String orginzeId;
    /**
     * 年
     */
    private String year;
    /**
     * 月[月份如果是个位数的则需要前面加0]
     */
    private String month;


    private Context context;

    public WorkBenchUrlBean(Context context, String year, String month) {
        this.year = year;
        this.month = month;
        this.context = context;
    }


    public String getUseId() {
        return PrefUtils.getString(context, GlobalConstants.PERSONAL_USERID, null);
    }

    public String getDeviceID() {
        return getLocalMacAddress(context);
    }

    public String getOrginzeId() {
        return PrefUtils.getString(context, APP_ORG_ID, null);
    }

    public String getAllOrginzeId() {
        return PrefUtils.getString(context, GlobalConstants.PERSONAL_ORG, null);
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "WorkBenchUrlBean{" +
                ", useId='" + getUseId() + '\'' +
                ", deviceID='" + getDeviceID() + '\'' +
                ", orginzeId='" + getOrginzeId() + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                '}';
    }

    /**
     * 获取当前设备的MAC地址
     */
    public static String getLocalMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
//        return info.getMacAddress();
        return "DEVICEID";
    }
}
