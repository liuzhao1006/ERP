package com.ycjt.sx.utils.http.nohttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RestRequest;
import com.yanzhenjie.nohttp.rest.StringRequest;

public class FastJsonRequest extends RestRequest<JSONObject> {

    public FastJsonRequest(String url) {
        this(url, RequestMethod.GET);
    }

    public FastJsonRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
        setAccept(Headers.HEAD_VALUE_ACCEPT_APPLICATION_JSON);
    }

    @Override
    public void onPreExecute() {
        // TODO 这个方法会在真正请求前被调用，在这里可以做一些加密之类的工作。这个方法在子线程被调用。
    }

    @Override
    public JSONObject parseResponse(Headers responseHeaders, byte[] responseBody) {
        String result = StringRequest.parseResponseString(responseHeaders, responseBody);
        return JSON.parseObject(result);
    }
}
