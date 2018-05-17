package com.ycjt.sx.erp.maillist.presenter;

import com.ycjt.sx.base.inter.IView;

/**
 * 作者 : 刘朝,
 * on 2017/8/4,
 * GitHub : https://github.com/liuzhao1006
 */

public interface IOrganizationalAndCommon extends IView {

    /**
     * 获取组织机构成功
     *
     * @param data
     */
    void successedOrganizational(String data);

    /**
     * 获取组织机构失败
     *
     * @param message
     */
    void failedOrganizational(String message);


    /**
     * 获取常用联系人成功
     *
     * @param data
     */
    void successedCommonLinkMan(String data);

    /**
     * 获取常用联系人失败
     *
     * @param message
     */
    void failedCommonLinkMan(String message);
}
