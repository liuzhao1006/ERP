package com.ycjt.sx.erp.agency.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.erp.agency.presenter.ISendPresenter;
import com.ycjt.sx.login.bean.JsonLoginBean;

/**
 * Created by liuchao on 2017/7/18.
 */

public class AgencyMvpSendModel implements IStringModel {

    private Context context;
    private final BaseHttpUtils instance;
    private ISendPresenter sendPresenter;
    private int i;

    public AgencyMvpSendModel(Context context, ISendPresenter sendPresenter) {
        this.context = context;
        this.sendPresenter = sendPresenter;
        instance = BaseHttpUtils.getInstance();
    }

    /**
     * 获取数据
     *
     * @param url
     */
    public void getSendData(String url, int i) {
        instance.getStringRequest(url,i,  this);
        this.i = i;
    }

    @Override
    public void successed(int what,String data) {
        JsonLoginBean bean = JSON.parseObject(data, JsonLoginBean.class);
        if (bean.Code.equals("1")) {
            sendPresenter.sendFailed(bean.message);
            return;
        }
        if (i == 0) {
            sendPresenter.sendSuccessed(bean.data);
        } else if (i == 1) {
            sendPresenter.sendMoreSuccessed(bean.data);
        }
    }

    @Override
    public void failed(int what,String message) {
        sendPresenter.sendFailed(message);
    }

    @Override
    public void startDialog() {
        sendPresenter.startDialog();
    }

    @Override
    public void stopDialog() {
        sendPresenter.stopDialog();
    }
}
