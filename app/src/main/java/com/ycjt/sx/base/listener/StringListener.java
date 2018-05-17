package com.ycjt.sx.base.listener;

import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;
import com.ycjt.sx.base.inter.IStringModel;

/**
 * Created by liuchao on 2017/7/14.
 */

public class StringListener implements OnResponseListener<String> {
    private IStringModel model;

    public StringListener(IStringModel model) {
        this.model = model;
    }

    @Override
    public void onStart(int what) {
        model.startDialog();
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        if (response.responseCode() == 200) {
//            String s2 = (String) response.get();//服务器回传回来的数据转换
//            s2.replaceAll("\"", "'");
//            JSONObject jsonObject = JSONObject.parseObject(s2);

//            JSONObject jsonObject = JSONObject.parseObject(response.get()).toJSONString();
//            LogUtils.i("BaseModel" + JSONObject.parseObject(response.get()).toJSONString());
            model.successed(what, JSONObject.parseObject(response.get()).toJSONString());
        } else if (response.responseCode() == 404) {
            LogUtils.i("404了");
            model.failed(what, "服务器出错, 请稍后进入,或者联系管理员......");
        } else {
            //访问网络路径发生改变,比如response.responseCode() == 400
            model.failed(what, "服务器给了个:" + response.responseCode() + ",我也不知道怎么了,反正就是找不到您要的资源......");
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {
        LogUtils.i(response.getException().getMessage());
        try {
            if (response.get() == null) {
                model.failed(what, "请注意,服务器赶不上变化...");
                return;
            }
            model.failed(what, response.get().toString());
        } catch (Exception e) {
            e.printStackTrace();
            model.failed(what, "服务器错误,登录失败");
        }
    }

    @Override
    public void onFinish(int what) {
        model.stopDialog();
    }
}
