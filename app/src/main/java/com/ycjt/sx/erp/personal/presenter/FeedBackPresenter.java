package com.ycjt.sx.erp.personal.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.ycjt.sx.base.BaseModel;
import com.ycjt.sx.erp.personal.activity.IFeedBack;
import com.ycjt.sx.erp.personal.model.FeedBackModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuchao on 2017/7/5.
 * <p>
 * post上传图片
 */

public class FeedBackPresenter {

    private RequestQueue mQueue;
    private IFeedBack feedBack;

    public FeedBackPresenter(IFeedBack feedBack) {
        this.feedBack = feedBack;
        mQueue = NoHttp.newRequestQueue(5);
    }

    /**
     * 图片上传
     *
     * @param url
     * @param mapData
     */
    public void postBitMap(String url, Map<String, String> mapData) {
        String s = JSON.toJSONString(mapData);

        LogUtils.i(s);
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.setDefineRequestBody(s, "application/json");

        mQueue.add(1, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                if (response.responseCode() == 200) {

                    //{"Code":"0","Data":"
                    // {\"Id\":16,\"UserId\":\"99D7E378-0BE0-4703-B157-E88C100C5DF1\",
                    // \"UserName\":\"admin\",
                    // \"Position\":\"管理员\",
                    // \"OrgizeId\":\"04f12beb-d99d-43df-ac9a-3042957d6bda\",
                    // \"OrgizeName\":\"广州亿程总部\",
                    // \"Title\":\"fdd\",
                    // \"FeedContent\":\"fees\",
                    // \"CreateTime\":\"\\\/Date(1499331600196+0800)\\\/\",
                    // \"CreatePesition\":\"admin\"}",
                    // "message":"添加意见反馈主表信息成功"}
                    String s2 = response.get().replaceAll("\"", "'");
                    JSONObject jsonObject = JSONObject.parseObject(s2);
                    LogUtils.i("BaseModel" + jsonObject.toJSONString());
                    feedBack.successed(jsonObject.toJSONString());
                } else if (response.responseCode() == 404) {
                    LogUtils.i("404了");
                    feedBack.successed("服务器出错, 请稍后进入,或者联系管理员......");
                }
                LogUtils.i(response.responseCode());
                String s1 = response.get();
                LogUtils.i(s1);
            }

            @Override
            public void onFailed(int what, Response<String> response) {

                if (response == null) {
                    feedBack.failed("服务器连接错误...");
                } else {
                    feedBack.failed(response.get());
                }
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
}
