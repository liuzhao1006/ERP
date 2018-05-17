package com.ycjt.sx.erp.personal.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.R;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.erp.personal.presenter.PersonalSettingMvpPresenter;
import com.ycjt.sx.utils.PrefUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.ycjt.sx.utils.ImageViewUtils.getSmallBitmap;

/**
 * 作者 : 刘朝,
 * on 2017/7/25,
 * GitHub : https://github.com/liuzhao1006
 */

public class PersonalSettingBean {

    private Context context;
    private Map<String, String> map;

    public PersonalSettingBean(Context context) {
        this.context = context;
        map = new HashMap<>();
    }

    public PersonalSettingBean(Context context, String imagesPath) {
        this.context = context;
        this.imagesPath = imagesPath;
        map = new HashMap<>();
    }

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 设备标识符
     */
    private String deviceID;
    /**
     * 用户获取到的图片二进制数据
     */
    private String imagesPath;

    public String getUserId() {
        return PrefUtils.getString(context, GlobalConstants.PERSONAL_USERID, null);
    }

    public String getDeviceID() {
        return PrefUtils.getString(context, GlobalConstants.PERSONAL_DEVICEID, null);
    }

    public String getImagesPath() {
        return imgToBase64(imagesPath);
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    public String getMapToJson() {
        map.put("Images", getImagesPath());
        map.put("deviceID", getDeviceID());
        map.put("userId", getUserId());

        LogUtils.i(JSON.toJSONString(map));
        return JSON.toJSONString(map);

    }

    private Bitmap bitmap;

    /**
     * 将图片转换成二进制数据字符串,用于上传图片
     *
     * @return
     */
    public String imgToBase64(String imgPath) {
        if (imgPath != null && imgPath.length() > 0) {
            bitmap = getSmallBitmap(imgPath, 300, 540);
        }
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ren_icon);
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
