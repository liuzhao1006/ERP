package com.ycjt.sx.base;

import android.graphics.Bitmap;

import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by liuchao on 2017/6/19.
 */

public class BaseModel {

    /**
     * 接口路径 测试用给url赋值为NoHttpDemo重接口路径Constants.URL_NOHTTP_POSTBODY
     */
//    private String url = Constants.URL_NOHTTP_POSTBODY;
    private String url;
    private String s1;
    private String s2;


    static {
        queue = NoHttp.newRequestQueue();

    }

    public static RequestQueue queue;


    /**
     * 有一个参数的构造器
     *
     * @param url
     */
    public BaseModel(String url) {
        this.url = url;
    }

    /**
     * 有多个参数的构造器
     * 根据业务添加
     * 暂时不实现
     */

    public BaseModel(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        LogUtils.i(BaseModel.class.getSimpleName() + "=========" + s1 + "/*===========*/" + s2);
    }


    public BaseModel(String url, String s1, String s2) {
        this.url = url;
        this.s1 = s1;
        this.s2 = s2;
    }


    /**
     * 请求数据
     *
     * @param request
     */
    protected void getRequest(Request<String> request) {
        queue.add(1, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int i) {
                //请求开始前,可以加载一个dialog
                onStartDialog();

            }

            @Override
            public void onSucceed(int i, Response<String> response) {
                if (response.responseCode() == 200) {
                    String s2 = response.get().replaceAll("\"", "'");//服务器回传回来的数据转换
                    JSONObject jsonObject = JSONObject.parseObject(s2);
                    LogUtils.i("BaseModel" + jsonObject.toJSONString());
                    successed(jsonObject.toJSONString());
                } else if (response.responseCode() == 404) {
                    LogUtils.i("404了");
                    failed("服务器出错, 请稍后进入,或者联系管理员......");
                } else {
                    //访问网络路径发生改变,比如response.responseCode() == 400
                    failed("服务器出错, 请稍后进入,或者联系管理员......");
                }
            }


            @Override
            public void onFailed(int i, Response<String> response) {
                LogUtils.i(response.getException().toString());
                failed("服务器无响应...");
            }

            @Override
            public void onFinish(int i) {
                //请求结束,可以取消dialog
                onStopDialog();
            }
        });
    }

    /**
     * 请求图片
     */
    protected void getBitMapRequest(Request<Bitmap> request) {
        queue.add(1, request, new OnResponseListener<Bitmap>() {

            @Override
            public void onStart(int i) {

            }

            @Override
            public void onSucceed(int i, Response<Bitmap> response) {
                Bitmap bitmap = response.get();
                successedBit(bitmap);
            }

            @Override
            public void onFailed(int i, Response<Bitmap> response) {

            }

            @Override
            public void onFinish(int i) {

            }
        });
    }


    /**
     * post请求
     *
     * @param request
     */
    public void postRequest(Request<String> request) {

        queue.add(1, request, new OnResponseListener<String>() {

            @Override
            public void onStart(int i) {

            }

            @Override
            public void onSucceed(int i, Response<String> response) {
                if (response.responseCode() == 200) {
                    String s2 = response.get().replaceAll("\"", "'");
                    JSONObject jsonObject = JSONObject.parseObject(s2);
                    LogUtils.i("BaseModel" + jsonObject.toJSONString());
                    successed(jsonObject.toJSONString());
                } else if (response.responseCode() == 404) {
                    LogUtils.i("404了");
                    successed("服务器出错, 请稍后进入,或者联系管理员......");
                }

            }

            @Override
            public void onFailed(int i, Response<String> response) {

            }

            @Override
            public void onFinish(int i) {

            }
        });

    }

    /**
     * 提供一个回调方法,添加参数
     */

    public interface OnAddRequest {

        /**
         * 链接成功,拿到数据
         *
         * @param s
         * @return
         */
        void successed(String s);

        /**
         * 链接失败,拿到失败信息
         *
         * @param s
         * @return
         */
        void failed(String s);

        /**
         * 添加开始进度条
         */
        void startDialog();

        /**
         * 停止进度条
         */
        void stopDialog();
    }

    /**
     * 请求图片的接口
     */
    public interface OnAddBitMapRequest {

        /**
         * 链接成功,拿到数据
         *
         * @param bitmap
         * @return
         */
        void successedBit(Bitmap bitmap);

        /**
         * 链接失败,拿到失败信息
         *
         * @param s
         * @return
         */
        void failedBit(String s);

        /**
         * 添加开始进度条
         */
        void startDialogBit();

        /**
         * 停止进度条
         */
        void stopDialogBit();
    }

    private OnAddRequest addRequest;

    public void setOnAddRequest(OnAddRequest addRequest) {
        this.addRequest = addRequest;
    }


    protected void successed(String s) {
        if (addRequest != null) {
            addRequest.successed(s);
        }
    }

    protected void failed(String s) {
        if (addRequest != null) {
            addRequest.failed(s);
        }
    }

    protected void onStartDialog() {
        if (addRequest != null) addRequest.startDialog();
    }

    protected void onStopDialog() {
        if (addRequest != null) addRequest.stopDialog();
    }


    private OnAddBitMapRequest addBitRequest;

    public void setOnAddBitMapRequest(OnAddBitMapRequest addBitRequest) {
        this.addBitRequest = addBitRequest;
    }


    protected void successedBit(Bitmap bitmap) {
        if (addBitRequest != null) {
            addBitRequest.successedBit(bitmap);
        }
    }

    protected void failedBit(String s) {
        if (addBitRequest != null) {
            addBitRequest.failedBit(s);
        }
    }

    protected void onStartDialogBit() {
        if (addBitRequest != null) addBitRequest.startDialogBit();
    }

    protected void onStopDialogBit() {
        if (addBitRequest != null) addBitRequest.stopDialogBit();
    }


}
