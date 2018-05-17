package com.ycjt.sx.base.mvpbase;

import android.graphics.Bitmap;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.ycjt.sx.base.inter.IBitmapModel;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.listener.BitmapListener;
import com.ycjt.sx.base.listener.StringListener;

import java.util.Map;

/**
 * Created by liuchao on 2017/7/14.
 */

public class BaseHttpUtils {
    private static BaseHttpUtils instance;
    public static RequestQueue queue;

    static {
        queue = NoHttp.newRequestQueue();
    }

    private BaseHttpUtils() {
    }

    public static BaseHttpUtils getInstance() {
        if (instance == null) {
            synchronized (BaseHttpUtils.class) {
                if (instance == null) {
                    instance = new BaseHttpUtils();
                }
            }
        }
        return instance;
    }

    /**
     * get请求内容
     *
     * @param url
     */
    public void getStringRequest(String url, int what, IStringModel model) {
        LogUtils.i(BaseHttpUtils.class.getSimpleName() + "~~" + url);
        Request<String> requestString = NoHttp.createStringRequest(url);
        queue.add(what, requestString, new StringListener(model));
    }

    /**
     * 根据图片名称,下载图片
     *
     * @param url
     * @param model
     */
    public void getBitMapRequest(String url, IBitmapModel model) {
        LogUtils.i(BaseHttpUtils.class.getSimpleName() + "~~" + url);
        Request<Bitmap> requestBitmap = NoHttp.createImageRequest(url);
        requestBitmap.setCacheMode(CacheMode.DEFAULT);
        queue.add(5, requestBitmap, new BitmapListener(url, model));
    }

    /**
     * post方式上传数据,
     *
     * @param url   接口
     * @param json  上传数据
     * @param model 回调
     */
    public void postJsonUpLoad(String url, String json, IStringModel model) {
        Request<String> upLoadRequestJson = NoHttp.createStringRequest(url, RequestMethod.POST);
        upLoadRequestJson.setDefineRequestBody(json, "application/json");
        queue.add(1, upLoadRequestJson, new StringListener(model));
    }

}
