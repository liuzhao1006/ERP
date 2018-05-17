package com.ycjt.sx.erp.personal.model;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.base.BaseModel;
import com.ycjt.sx.utils.PrefUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import Decoder.BASE64Encoder;

/**
 * Created by liuchao on 2017/7/5.
 */

public class FeedBackModel extends BaseModel {

    private Request<String> request;
    private ArrayList<String> mList;
    private Context context;

    public FeedBackModel(Context context, String url, ArrayList<String> mList) {
        super(url);
        this.mList = mList;
        this.context = context;
        request = NoHttp.createStringRequest(url, RequestMethod.POST);

        imageToString(mList);

    }

    /**
     * 将图片转换成字符串
     *
     * @param mList
     */
    private void imageToString(ArrayList<String> mList) {

        // 添加普通参数。
        request.add("userId", PrefUtils.getString(context, GlobalConstants.PERSONAL_USERID, null));
        request.add("deviceID", PrefUtils.getString(context, GlobalConstants.PERSONAL_DEVICEID, null));
        request.add("organizeId", PrefUtils.getString(context, GlobalConstants.PERSONAL_ORG, null));
//        request.add("Title", title);
//        request.add("FeedContent", content);

        for (int i = 0; i < mList.size(); i++) {
            String s = mList.get(i);
            String imageString = GetImageStr(s);
            request.add("Imageslist", imageString);

            LogUtils.i("--------------"+imageString+"----------------------");
            System.out.print("---"+imageString+"---");
        }

    }


    /**
     * POST请求方式直接使用
     */
    public void getData() {
        getRequest(request);

    }

    //图片转化成base64字符串
    public String GetImageStr(String fileName) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(fileName);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

}
