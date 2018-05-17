package com.ycjt.sx.erp.personal.bean;

import android.content.Context;

import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.utils.PrefUtils;

/**
 * Created by liuchao on 2017/7/21.
 */

public class PersonalBean {

    private Context context;

    /**
     * 获取图片路径
     */
    private String imagePath;


    private String servicePath;

    public PersonalBean(String imagePath, String servicePath) {
        this.imagePath = imagePath;
        this.servicePath = servicePath;
    }

    public PersonalBean(Context context, String imagePath) {
        this.imagePath = imagePath;
        this.context = context;
    }

    public PersonalBean(Context context) {
        this.context = context;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getServicePath() {
        return PrefUtils.getString(context, GlobalConstants.PERSONAL_PHOTO, null);
    }


    @Override
    public String toString() {
        return "PersonalBean{" +
                "imagePath='" + imagePath + '\'' +
                ", servicePath='" + PrefUtils.getString(context, GlobalConstants.PERSONAL_PHOTO, null) + '\'' +
                '}';
    }
}
