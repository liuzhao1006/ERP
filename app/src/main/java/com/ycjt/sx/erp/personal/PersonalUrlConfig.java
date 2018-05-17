package com.ycjt.sx.erp.personal;

import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.erp.personal.bean.PersonalBean;
import com.ycjt.sx.erp.personal.bean.PersonalSettingBean;
import com.ycjt.sx.erp.personal.bean.UpPwdBean;

import static com.ycjt.sx.app.InterfaceConfig.UPLOADIMAGE;
import static com.ycjt.sx.app.InterfaceConfig.URL_ADDRESS;

/**
 * Created by liuchao on 2017/7/21.
 */

public class PersonalUrlConfig {
    private PersonalUrlConfig() {

    }

    /**
     * personalfragment 页面..获取用户图像
     *
     * @param bean
     * @return
     */
    public static String getPersonalUrl(PersonalBean bean) {
        return bean != null ? URL_ADDRESS + bean.getImagePath() : URL_ADDRESS + "/" + "admin.jpg";
    }

    /**
     * personalupdatepwdactivity页面..修改密码
     */
    public static String getUpDataUrl(UpPwdBean bean) {
        return bean != null ? URL_ADDRESS + "/" + bean.getUserId() + "/" + bean.getDeviceID() + "/" + bean.getOldPwd() + "/" + bean.getNewPwd() : "";
    }

    /**
     * 个人设置页面 图片上传路径
     *
     * @param bean
     * @return
     */
    public static String getPersonalSettingUrl(PersonalSettingBean bean) {
        return bean != null ? UPLOADIMAGE : UPLOADIMAGE;

    }

}
