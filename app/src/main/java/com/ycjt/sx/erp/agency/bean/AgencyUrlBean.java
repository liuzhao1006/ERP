package com.ycjt.sx.erp.agency.bean;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.utils.PrefUtils;


/**
 * Created by liuchao on 2017/7/18.
 * <p>
 * 接口参数封装类, 本类提供了传递参数的方法,其中请求数量为一次20个,设备编号默认为手机Mac地址不能改变.
 */

public class AgencyUrlBean {
//public String HOME_PAGE_DATA_URL = HTTP + APP_IP + ":" +
// APP_POST + HOME_PAGE +
// PrefUtils.getString(mActivity, GlobalConstants.PERSONAL_USERID, null) + "/" +
// HOME_PAGE_ORDERSTATE_AGENCY + "/" + HOME_PAGE_CURRENT_SIZE + "/" +
// HOME_PAGE_QUESTSIZE + "/" +
// PrefUtils.getString(mActivity, GlobalConstants.PERSONAL_DEVICEID, null);


    private Context context;

    public AgencyUrlBean(Context context) {
        this.context = context;

    }

    public AgencyUrlBean(Context context, String orderState, int currentSize) {
        this.context = context;
        this.orderState = orderState;
        this.currentSize = currentSize;
    }

    public AgencyUrlBean(Context context, String orderState, int currentSize, String content) {
        this.context = context;
        this.orderState = orderState;
        this.currentSize = currentSize;
        this.content = content;
    }

//    <param name="userId">用户id</param>
/// <param name="orderState">状态[1:待办2:已办3:有我发起0:返回1、2、3数据]</param>
/// <param name="dataSize">当前数量</param>
/// <param name="questSize">请求数量</param>
/// <param name="deviceID"> deviceID </param>

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 状态值
     */
    private String orderState;
    /**
     * 当前数量
     */
    private int currentSize;
    /**
     * 请求数量
     */
    private int questSize;
    /**
     * 设备标识符, 为手机的Mac地址
     */
    private String deviceID;

    /**
     * 搜索内容
     */
    private String content;

    public String getUserId() {
        return PrefUtils.getString(context, GlobalConstants.PERSONAL_USERID, null);
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public int getQuestSize() {
        return 20;
    }


    public String getDeviceID() {
        return getLocalMacAddress(context);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AgencyUrlBean{" +
                "context=" + context +
                ", userId='" + userId + '\'' +
                ", orderState='" + orderState + '\'' +
                ", currentSize=" + currentSize +
                ", questSize=" + questSize +
                ", deviceID='" + deviceID + '\'' +
                ", content='" + content + '\'' +
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
