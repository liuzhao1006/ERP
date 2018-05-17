package com.ycjt.sx.erp.workbench.model;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.ycjt.sx.base.BaseModel;

/**
 * Created by liuchao on 2017/7/7.
 */

public class SaleModel extends BaseModel {
    private Request<String> request;

    public SaleModel(String url) {
        super(url);
        request = NoHttp.createStringRequest(url, RequestMethod.GET);
    }


    /**
     * get请求方式直接使用
     */
    public void getData() {
        getRequest(request);

    }
}
