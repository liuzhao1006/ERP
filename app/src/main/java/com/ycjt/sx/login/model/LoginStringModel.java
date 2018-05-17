package com.ycjt.sx.login.model;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.ycjt.sx.base.BaseModel;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.login.bean.UserBean;

/**
 * Created by liuchao on 2017/6/19.
 */

public class LoginStringModel extends BaseModel implements IStringModel {

    private Request<String> request;
    private UserBean user;
    public LoginStringModel(String url, UserBean user) {
        super(url);
        this.user = user;
        request = NoHttp.createStringRequest(url, RequestMethod.GET);
    }

    /**
     * get请求方式直接使用
     */
    public void getData(){
        getRequest(request);
    }

    @Override
    public void successed(int what,String data) {

    }

    @Override
    public void failed(int what,String message) {

    }

    @Override
    public void startDialog() {

    }

    @Override
    public void stopDialog() {

    }
}
