package com.ycjt.sx.erp.agency.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.erp.agency.presenter.ISearchPresenter;
import com.ycjt.sx.erp.agency.presenter.ISendPresenter;
import com.ycjt.sx.login.bean.JsonLoginBean;

/**
 * Created by liuchao on 2017/7/18.
 */

public class AgencyMvpSearchModel implements IStringModel {

    private Context context;
    private final BaseHttpUtils instance;
    private ISearchPresenter searchPresenter;
    private int i;

    public AgencyMvpSearchModel(Context context, ISearchPresenter searchPresenter) {
        this.context = context;
        this.searchPresenter = searchPresenter;
        instance = BaseHttpUtils.getInstance();
    }

    /**
     * 获取数据
     *
     * @param url
     */
    public void getSearchData(String url, int i) {
        instance.getStringRequest(url,i,  this);
        this.i = i;
    }

    @Override
    public void successed(int what,String data) {
        JsonLoginBean bean = JSON.parseObject(data, JsonLoginBean.class);
        if (bean.Code.equals("1")) {
            searchPresenter.searchFailed(bean.message);
            return;
        }
        if (i == 0) {
            searchPresenter.searchSuccessed(bean.data);
        } else if (i == 1) {
            searchPresenter.searchMoreSuccessed(bean.data);
        }
    }

    @Override
    public void failed(int what,String message) {
        searchPresenter.searchFailed(message);
    }

    @Override
    public void startDialog() {
        searchPresenter.startDialog();
    }

    @Override
    public void stopDialog() {
        searchPresenter.stopDialog();
    }
}
