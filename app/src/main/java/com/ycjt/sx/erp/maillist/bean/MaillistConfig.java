package com.ycjt.sx.erp.maillist.bean;

import android.net.Uri;

import com.ycjt.sx.erp.agency.bean.AgencyUrlBean;

import static com.ycjt.sx.app.InterfaceConfig.MAILLIST_ORGANIZATIONAL;
import static com.ycjt.sx.app.InterfaceConfig.WAIT_URL;

/**
 * 作者 : 刘朝,
 * on 2017/8/3,
 * GitHub : https://github.com/liuzhao1006
 */

public class MaillistConfig {

    /**
     * 获取有我发起的接口路径
     *
     * @param bean
     * @return
     */
    public static String getMaillistOrganizationalUrl(MaillistUrlbean bean) {
        return MAILLIST_ORGANIZATIONAL +//
                bean.getUserId() + "/" + //
                bean.getDeviceID() + "/" +//
                bean.getOrgId() + "/0";
    }

    /**
     * 获取通讯录组织架构接口路径
     */
    public static String getMaillistOandCUrl(MaillistUrlbean bean) {

        return MAILLIST_ORGANIZATIONAL +
                bean.getUserId() + "/" + //
                bean.getDeviceID() + "/" +//
                bean.getAllOrgId() + "/0";
    }


}
