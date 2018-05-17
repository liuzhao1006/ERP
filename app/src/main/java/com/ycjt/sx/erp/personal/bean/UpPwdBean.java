package com.ycjt.sx.erp.personal.bean;

import android.content.Context;

import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.utils.PrefUtils;

/**
 * 作者 : 刘朝,
 * on 2017/7/25,
 * GitHub : https://github.com/liuzhao1006
 */

public class UpPwdBean {

    /**
     * 操作时间
     */
    private long time;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 设备标识符
     */
    private String deviceID;

    /**
     * old密码
     */
    private String oldPwd;

    /**
     * new密码
     */
    private String newPwd;

    private Context context;

    public UpPwdBean(Context context) {
        this.context = context;
    }

    /**
     * 时间为当前时间戳
     *
     * @return
     */
    public long getTime() {
        return System.currentTimeMillis();
    }

    /**
     * 当前登录账号的userID
     *
     * @return
     */
    public String getUserId() {
        return PrefUtils.getString(context, GlobalConstants.PERSONAL_USERID, null);
    }

    public String getDeviceID() {
        return PrefUtils.getString(context, GlobalConstants.PERSONAL_DEVICEID, null);
    }


    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    @Override
    public String toString() {
        return "UpPwdBean{" +
                "time=" + time +
                ", userId='" + getUserId() + '\'' +
                ", deviceID='" + getDeviceID() + '\'' +
                ", oldPwd='" + oldPwd + '\'' +
                ", newPwd='" + newPwd + '\'' +
                '}';
    }
}
