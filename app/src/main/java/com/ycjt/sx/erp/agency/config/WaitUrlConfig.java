package com.ycjt.sx.erp.agency.config;

import com.ycjt.sx.erp.agency.bean.AgencyUrlBean;

import static com.ycjt.sx.app.InterfaceConfig.SEARCH_URL;
import static com.ycjt.sx.app.InterfaceConfig.WAIT_URL;

/**
 * Created by liuchao on 2017/7/18.
 * 待办URL封装类
 */

public class WaitUrlConfig {
    private WaitUrlConfig() {
    }
//  WAIT_URL =  http://192.168.103.121:8011/Service1.svc/

    /**
     * 获取待办的接口路径
     *
     * @param bean
     * @return
     */
    public static String getWaitUrl(AgencyUrlBean bean) {
        return WAIT_URL + //
                bean.getUserId() + "/" + //
                bean.getOrderState() + "/" + //
                bean.getCurrentSize() + "/" + //
                bean.getQuestSize() + "/" + //
                bean.getDeviceID();
    }

    /**
     * 获取已办的接口路径
     *
     * @param bean
     * @return
     */
    public static String getCompleteUrl(AgencyUrlBean bean) {
        return WAIT_URL +//
                bean.getUserId() + "/" + //
                bean.getOrderState() + "/" + //
                bean.getCurrentSize() + "/" + //
                bean.getQuestSize() + "/" + //
                bean.getDeviceID();
    }

    /**
     * 获取有我发起的接口路径
     *
     * @param bean
     * @return
     */
    public static String getSendUrl(AgencyUrlBean bean) {
        return WAIT_URL +//
                bean.getUserId() + "/" + //
                bean.getOrderState() + "/" + //
                bean.getCurrentSize() + "/" + //
                bean.getQuestSize() + "/" + //
                bean.getDeviceID();
    }

    /**
     * 获取搜索的接口路径
     *
     * @param bean
     * @return
     */
    public static String getSearchUrl(AgencyUrlBean bean) {
        return SEARCH_URL +//
                bean.getUserId() + "/" + //
                bean.getOrderState() + "/" + //
                bean.getCurrentSize() + "/" + //
                bean.getQuestSize() + "/" + //
                bean.getDeviceID() + "/" +
                bean.getContent();
    }


}
