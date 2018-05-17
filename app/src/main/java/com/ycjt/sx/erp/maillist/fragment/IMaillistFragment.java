package com.ycjt.sx.erp.maillist.fragment;

import com.ycjt.sx.base.inter.IView;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;

import java.util.List;

/**
 * Created by liuchao on 2017/6/21.
 */

public interface IMaillistFragment extends IView{
    /**
     * 获取组织机构成功
     *
     * @param data
     */
    void successedOrganizational(List<MaillistDataJson> dataList);

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
    void successedCommonLinkMan(List<MaillistDataJson> dataList);

    /**
     * 获取常用联系人失败
     *
     * @param message
     */
    void failedCommonLinkMan(String message);
}
