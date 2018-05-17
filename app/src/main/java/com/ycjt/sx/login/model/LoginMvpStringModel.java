package com.ycjt.sx.login.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.login.bean.JsonLoginBean;
import com.ycjt.sx.login.presenter.ILoginPresenter;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by liuchao on 2017/7/16.
 */

public class LoginMvpStringModel implements IStringModel {

    private final BaseHttpUtils instance;
    private Context context;
    private PromptDialog promptDialog;
    private ILoginPresenter login;

    public LoginMvpStringModel(Context context, ILoginPresenter login) {
        this.context = context;
        this.login = login;
        instance = BaseHttpUtils.getInstance();
    }

    /**
     * 登录
     *
     * @param url
     */
    public void getLogin(String url,int i) {
        instance.getStringRequest(url,i,  this);
    }

    @Override
    public void successed(int what,String data) {
        LogUtils.i(data);
        //拿到data数据
        JsonLoginBean bean = JSON.parseObject(data, JsonLoginBean.class);

        if (bean.Code.equals("1")) {
            login.getFailed(bean.message);
            return;
        }
        login.getSuccessed(bean.data);

    }

    @Override
    public void failed(int what,String message) {
        login.getFailed(message);
    }

    @Override
    public void startDialog() {
        login.startDialog();
    }

    @Override
    public void stopDialog() {
        login.stopDialog();
    }
}
