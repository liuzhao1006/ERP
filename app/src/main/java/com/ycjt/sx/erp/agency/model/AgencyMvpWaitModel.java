package com.ycjt.sx.erp.agency.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.erp.agency.presenter.IWaitPresenter;
import com.ycjt.sx.login.bean.JsonLoginBean;

/**
 * Created by liuchao on 2017/7/18.
 */

public class AgencyMvpWaitModel implements IStringModel {

    private Context context;
    private BaseHttpUtils instance;
    private IWaitPresenter waitPresenter;
    private int i;

    public AgencyMvpWaitModel(Context context, IWaitPresenter waitPresenter) {
        this.context = context;
        instance = BaseHttpUtils.getInstance();
        this.waitPresenter = waitPresenter;
    }

    /**
     * 获取数据
     *
     * @param url
     */
    public void getWaitData(String url, int i) {
        instance.getStringRequest(url,i,  this);
        this.i = i;
    }

    @Override
    public void successed(int what,String data) {
        JsonLoginBean bean = JSON.parseObject(data, JsonLoginBean.class);
        if (bean.Code.equals("1")) {
            waitPresenter.waitFailed(bean.message);
            return;
        }
        if (i == 0) {
            waitPresenter.waitSuccessed(bean.data);
        } else if (i == 1) {
            waitPresenter.waitMoreSuccessed(bean.data);
        }
    }

    @Override
    public void failed(int what, String message) {
        waitPresenter.waitFailed(message);
    }

    @Override
    public void startDialog() {
        waitPresenter.startDialog();
    }

    @Override
    public void stopDialog() {
        waitPresenter.stopDialog();
    }
}
