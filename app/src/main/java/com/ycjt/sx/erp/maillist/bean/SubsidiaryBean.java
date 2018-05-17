package com.ycjt.sx.erp.maillist.bean;

import android.graphics.Bitmap;

import com.ycjt.sx.base.BaseBean;

/**
 * Created by liuchao on 2017/6/21.
 * <p>
 * 子公司联系人对象类
 */

public class SubsidiaryBean extends BaseBean {


    /**
     * 图像字节数组
     */
    private byte[] lv;
    /**
     * 公司名称
     */
    private String name;

    public SubsidiaryBean(byte[] lv, String name) {
        this.lv = lv;
        this.name = name;
    }

    public SubsidiaryBean() {
    }

    public byte[] getLv() {
        return lv;
    }

    public void setLv(byte[] lv) {
        this.lv = lv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubsidiaryBean{" +
                "lv=" + lv +
                ", name='" + name + '\'' +
                '}';
    }
}
