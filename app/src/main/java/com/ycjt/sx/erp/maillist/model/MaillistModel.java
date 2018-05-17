package com.ycjt.sx.erp.maillist.model;

import android.graphics.Bitmap;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.ycjt.sx.base.BaseModel;

/**
 * Created by liuchao on 2017/6/21.
 */

public class MaillistModel extends BaseModel {
    private Request<String> request;
    private Request<Bitmap> requestBitMap;

    public MaillistModel(String url) {
        super(url);

        request = NoHttp.createStringRequest(url, RequestMethod.GET);
        requestBitMap = NoHttp.createImageRequest(url);
    }

    /**
     * get请求方式直接使用
     */
    public void getData() {
        getRequest(request);

    }

    /**
     * 请求图片
     */
    public void getBitMap(){
        getBitMapRequest(requestBitMap);
    }

}
