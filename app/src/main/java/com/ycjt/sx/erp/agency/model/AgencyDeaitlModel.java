package com.ycjt.sx.erp.agency.model;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.ycjt.sx.base.BaseModel;

/**
 * Created by liuchao on 2017/6/19.
 */

public class AgencyDeaitlModel extends BaseModel {

    private Request<String> request;

    public AgencyDeaitlModel(String url) {
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
