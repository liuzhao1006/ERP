package com.ycjt.sx.erp.workbench.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.erp.workbench.presenter.IPrenterSale;
import com.ycjt.sx.login.bean.JsonLoginBean;

/**
 * 作者 : 刘朝,
 * on 2017/7/28,
 * GitHub : https://github.com/liuzhao1006
 */

public class SaleMvpModel implements IStringModel {
    private BaseHttpUtils instance;
    private Context context;
    private IPrenterSale sale;

    public SaleMvpModel(Context context, IPrenterSale sale) {
        this.context = context;
        this.sale = sale;
        instance = BaseHttpUtils.getInstance();

    }


    public void getSaleData(String url, int i) {
        instance.getStringRequest(url, i, this);
    }

    @Override
    public void successed(int what,String data) {
        //拿到data数据
        JsonLoginBean bean = JSON.parseObject(data, JsonLoginBean.class);
        if (bean.Code == "1") {
            sale.failedSale(bean.message);
            return;
        }
        sale.successedSale(bean.data);
    }

    @Override
    public void failed(int what,String message) {
        sale.failedSale(message);
    }

    @Override
    public void startDialog() {
        sale.startDialog();
    }

    @Override
    public void stopDialog() {
        sale.stopDialog();
    }
}
