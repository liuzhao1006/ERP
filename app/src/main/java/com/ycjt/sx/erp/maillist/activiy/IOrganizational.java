package com.ycjt.sx.erp.maillist.activiy;

import com.ycjt.sx.base.inter.IView;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;

import java.util.List;

/**
 * 作者 : 刘朝,
 * on 2017/8/3,
 * GitHub : https://github.com/liuzhao1006
 */

public interface IOrganizational extends IView {

    void successed(List<MaillistDataJson> dataList);

    void failed(String message);
}
