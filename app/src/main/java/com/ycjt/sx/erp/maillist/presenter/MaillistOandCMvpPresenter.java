package com.ycjt.sx.erp.maillist.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.erp.maillist.bean.MaillistUrlbean;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;
import com.ycjt.sx.erp.maillist.fragment.IMaillistFragment;
import com.ycjt.sx.erp.maillist.model.MaillistOrginzationalMvpMpdel;
import com.ycjt.sx.widget.customrecycle.adapter.LogUtils;

import java.util.List;

import static com.ycjt.sx.erp.maillist.bean.MaillistConfig.getMaillistOandCUrl;

/**
 * 作者 : 刘朝,
 * on 2017/8/4,
 * GitHub : https://github.com/liuzhao1006
 */

public class MaillistOandCMvpPresenter implements IOrganizationalAndCommon {


    private Context context;
    private IMaillistFragment fragment;
    private final MaillistOrginzationalMvpMpdel model;

    public MaillistOandCMvpPresenter(Context context, IMaillistFragment fragment) {
        this.context = context;
        this.fragment = fragment;
        model = new MaillistOrginzationalMvpMpdel(context, this);
    }


    /**
     * 获取通讯录组织架构
     *
     * @param maillistUrlbean
     */
    public void getOrganizationalData(MaillistUrlbean maillistUrlbean) {
        com.apkfuns.logutils.LogUtils.i(maillistUrlbean.getAllOrgId());
        model.getOrganizationalData(getMaillistOandCUrl(maillistUrlbean), 0);
    }

    /**
     * 获取通讯录联系人
     *
     * @param maillistUrlbean
     */
    public void getCommonData(MaillistUrlbean maillistUrlbean) {
        com.apkfuns.logutils.LogUtils.i(maillistUrlbean.getAllOrgId());
        model.getCommonLinkMan(getMaillistOandCUrl(maillistUrlbean), 1);
    }

    @Override
    public void startDialog() {
        fragment.startDialog();
    }

    @Override
    public void stopDialog() {
        fragment.stopDialog();
    }

    @Override
    public void successedOrganizational(String data) {
        List<MaillistDataJson> dataList = JSON.parseArray(data, MaillistDataJson.class);
        fragment.successedOrganizational(dataList);
    }

    @Override
    public void failedOrganizational(String message) {
        fragment.failedOrganizational(message);
    }

    @Override
    public void successedCommonLinkMan(String data) {
        List<MaillistDataJson> dataList = JSON.parseArray(data, MaillistDataJson.class);
        fragment.successedCommonLinkMan(dataList);
    }

    @Override
    public void failedCommonLinkMan(String message) {
        fragment.failedCommonLinkMan(message);
    }
}
