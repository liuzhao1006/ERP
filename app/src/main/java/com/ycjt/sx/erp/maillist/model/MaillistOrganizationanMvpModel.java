package com.ycjt.sx.erp.maillist.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.erp.agency.presenter.IWaitPresenter;
import com.ycjt.sx.erp.maillist.presenter.IOrganizationalPersenter;
import com.ycjt.sx.login.bean.JsonLoginBean;

/**
 * Created by liuchao on 2017/7/18.
 */

public class MaillistOrganizationanMvpModel implements IStringModel {

    private Context context;
    private BaseHttpUtils instance;
    private IOrganizationalPersenter presenter;
    private int i;

    public MaillistOrganizationanMvpModel(Context context, IOrganizationalPersenter presenter) {
        this.context = context;
        this.presenter = presenter;
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

    @Override
    public void successed(int what, String data) {
        JsonLoginBean bean = JSON.parseObject(data, JsonLoginBean.class);
        if (bean.Code.equals("1")) {
            presenter.failedOrganizational(bean.message);
            return;
        }
        presenter.successedOrganizational(bean.data);
    }

    @Override
    public void failed(int what, String message) {
        presenter.failedOrganizational(message);
    }

    @Override
    public void startDialog() {
        presenter.startDialog();
    }

    @Override
    public void stopDialog() {
        presenter.stopDialog();
    }
}
