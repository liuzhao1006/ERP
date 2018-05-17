package com.ycjt.sx.erp.maillist.bean;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.utils.PrefUtils;

import static com.ycjt.sx.app.InterfaceConfig.APP_ORG_ID;

/**
 * 作者 : 刘朝,
 * on 2017/8/3,
 * GitHub : https://github.com/liuzhao1006
 */

public class MaillistUrlbean {
    private String userId;

    private String deviceID;

    private String orgId;

    public MaillistUrlbean(Context context) {
        this.context = context;
    }

    private Context context;

    public MaillistUrlbean(Context context, String userId, String deviceID, String orgId) {
        this.userId = userId;
        this.deviceID = deviceID;
        this.context = context;
        this.orgId = orgId;
    }

    public String getUserId() {
        return PrefUtils.getString(context, GlobalConstants.PERSONAL_USERID, null);
    }

    public String getDeviceID() {
        return getLocalMacAddress(context);
    }

    public String getOrgId() {
        return PrefUtils.getString(context, APP_ORG_ID, null);
    }
    public String getAllOrgId(){
        return PrefUtils.getString(context,GlobalConstants.PERSONAL_ORG,null);
    }

    @Override
    public String toString() {
        return "MaillistUrlbean{" +
                ", userId='" + getUserId() + '\'' +
                ", deviceID='" + getDeviceID() + '\'' +
                ", orgId='" + getOrgId() + '\'' +
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
