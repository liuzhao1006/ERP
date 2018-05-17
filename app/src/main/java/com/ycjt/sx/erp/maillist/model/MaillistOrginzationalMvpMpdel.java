package com.ycjt.sx.erp.maillist.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.erp.maillist.presenter.IOrganizationalAndCommon;
import com.ycjt.sx.login.bean.JsonLoginBean;

/**
 * 作者 : 刘朝,
 * on 2017/8/4,
 * GitHub : https://github.com/liuzhao1006
 */

public class MaillistOrginzationalMvpMpdel implements IStringModel {
    private Context context;
    private BaseHttpUtils instance;
    private IOrganizationalAndCommon common;
    private int i;

    public MaillistOrginzationalMvpMpdel(Context context, IOrganizationalAndCommon common) {
        this.context = context;
        this.common = common;
        instance = BaseHttpUtils.getInstance();
    }

    /**
     * 获取数据
     *
     * @param url
     */
    public void getOrganizationalData(String url, int i) {
        instance.getStringRequest(url, i, this);
        this.i = i;
    }


    /**
     * 获取常用联系人
     *
     * @param url
     * @param i
     */
    public void getCommonLinkMan(String url, int i) {
        instance.getStringRequest(url, i, this);
        this.i = i;
    }

    @Override
    public void successed(int what, String data) {
        JsonLoginBean bean = JSON.parseObject(data, JsonLoginBean.class);
        if (bean.Code.equals("1")) {
            common.failedOrganizational(bean.message);
            return;
        }
        if (what == 0)
            common.successedOrganizational(bean.data);
        if (what == 1)
            common.successedCommonLinkMan(bean.data);
    }

    @Override
    public void failed(int what, String message) {

        if (what == 0)
            common.failedOrganizational(message);
        if (what == 1)
            common.failedOrganizational(message);
    }

    @Override
    public void startDialog() {
        common.startDialog();
    }

    @Override
    public void stopDialog() {
        common.stopDialog();
    }
}
