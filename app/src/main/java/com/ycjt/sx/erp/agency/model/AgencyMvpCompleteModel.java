package com.ycjt.sx.erp.agency.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.erp.agency.presenter.ICompletePresenter;
import com.ycjt.sx.erp.agency.presenter.IWaitPresenter;
import com.ycjt.sx.login.bean.JsonLoginBean;

/**
 * Created by liuchao on 2017/7/18.
 */

public class AgencyMvpCompleteModel implements IStringModel {

    private Context context;
    private final BaseHttpUtils instance;
    private ICompletePresenter completePresenter;
    private int i;

    public AgencyMvpCompleteModel(Context context, ICompletePresenter completePresenter) {
        this.context = context;
        this.completePresenter = completePresenter;
        instance = BaseHttpUtils.getInstance();
    }

    /**
     * 获取数据
     *
     * @param url
     */
    public void getCompleteData(String url, int i) {
        instance.getStringRequest(url,i,  this);
        this.i = i;
    }

    @Override
    public void successed(int what,String data) {
        JsonLoginBean bean = JSON.parseObject(data, JsonLoginBean.class);
        if (bean.Code.equals("1")) {
            completePresenter.completeFailed(bean.message);
            return;
        }
        if (i == 0) {
            completePresenter.completeSuccessed(bean.data);
        } else if (i == 1) {
            completePresenter.completeMoreSuccessed(bean.data);
        }
    }

    @Override
    public void failed(int what,String message) {
        completePresenter.completeFailed(message);
    }

    @Override
    public void startDialog() {
        completePresenter.startDialog();
    }

    @Override
    public void stopDialog() {
        completePresenter.stopDialog();
    }
}
