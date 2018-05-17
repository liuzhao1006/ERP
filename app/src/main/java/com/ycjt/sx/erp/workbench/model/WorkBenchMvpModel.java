package com.ycjt.sx.erp.workbench.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.erp.workbench.presenter.IWorkBenchPresenter;
import com.ycjt.sx.login.bean.JsonLoginBean;

/**
 * 作者 : 刘朝,
 * on 2017/7/31,
 * GitHub : https://github.com/liuzhao1006
 */

public class WorkBenchMvpModel implements IStringModel {

    private Context context;
    private BaseHttpUtils instance;
    private IWorkBenchPresenter presenter;

    public WorkBenchMvpModel(Context context, IWorkBenchPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        instance = BaseHttpUtils.getInstance();
    }

    public void getWorkBenchData(String url, int i) {
        instance.getStringRequest(url, i, this);
    }

    @Override
    public void successed(int what, String data) {
        JsonLoginBean bean = JSON.parseObject(data, JsonLoginBean.class);
        if (bean.Code.equals("1")) {
            presenter.failedData(what, bean.message);
            return;
        }
        if (what == 1) {//销售
            presenter.successedSellData(bean.data);
        } else if (what == 2) {//采购
            presenter.successedOrderData(bean.data);
        } else if (what == 3) {//销售线形图
            presenter.successedSellDataLine(bean.data);
        } else if (what == 4) {//采购线形图
            presenter.successedOrderDataLine(bean.data);
        }

    }

    @Override
    public void failed(int what, String message) {
        presenter.failedData(what, message);
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
