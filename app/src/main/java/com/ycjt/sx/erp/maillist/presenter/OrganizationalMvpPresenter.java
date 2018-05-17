package com.ycjt.sx.erp.maillist.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.erp.maillist.activiy.IOrganizational;
import com.ycjt.sx.erp.maillist.bean.MaillistUrlbean;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;
import com.ycjt.sx.erp.maillist.model.MaillistOrganizationanMvpModel;

import java.util.List;

import static com.ycjt.sx.erp.maillist.bean.MaillistConfig.getMaillistOrganizationalUrl;

/**
 * Created by liuchao on 2017/7/18.
 */

public class OrganizationalMvpPresenter implements IOrganizationalPersenter {

    private Context context;
    private IOrganizational persenter;
    private MaillistOrganizationanMvpModel model;

    public OrganizationalMvpPresenter(Context context, IOrganizational persenter) {
        this.context = context;
        this.persenter = persenter;
        model = new MaillistOrganizationanMvpModel(context, this);
    }


    /**
     * 首次加载,或者下拉刷新
     *
     * @param bean
     */
    public void getOrganizationalData(MaillistUrlbean bean) {
        model.getOrganizationalData(getMaillistOrganizationalUrl(bean), 1);
    }

    @Override
    public void startDialog() {
        persenter.startDialog();
    }

    @Override
    public void stopDialog() {
        persenter.stopDialog();
    }


    @Override
    public void successedOrganizational(String data) {
        List<MaillistDataJson> dataList = JSON.parseArray(data, MaillistDataJson.class);
        persenter.successed(dataList);
    }

    @Override
    public void failedOrganizational(String messgae) {
        persenter.failed(messgae);
    }
}
