package com.ycjt.sx.login.service;

import com.ycjt.sx.login.bean.UserBean;

import static com.ycjt.sx.app.InterfaceConfig.LOGIN_URL;

/**
 * Created by liuchao on 2017/7/16.
 * <p>
 * //http://192.168.103.121:8011/Service1.svc/Login/userName/passWord/mac/ORG_ID
 * //String LOGIN_URL_T = LOGIN_URL + user + "/" + pwd + "/" + mac + "/" + ORG_ID;
 */

public class UrlConfig {

    public static String getUrl(UserBean user) {
        return LOGIN_URL + user.getUserName() + "/" + user.getPassWord() + "/" + user.getDeviceID() + "/" + user.getOrgID();
    }
}
