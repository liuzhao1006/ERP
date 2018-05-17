package com.ycjt.sx.erp.maillist.presenter;

import com.ycjt.sx.base.inter.IView;

/**
 * 作者 : 刘朝,
 * on 2017/8/3,
 * GitHub : https://github.com/liuzhao1006
 */

public interface IOrganizationalPersenter extends IView {
    void successedOrganizational(String data);

    void failedOrganizational(String messgae);
}
