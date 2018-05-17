package com.ycjt.sx.erp.maillist.bean;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.ycjt.sx.base.BaseBean;

/**
 * Created by liuchao on 2017/6/21.
 * <p>
 * 常用联系人对象类
 */

public class CommonContractBean extends BaseBean {

    /**
     * 常用联系人图像的字节数组
     */
    private byte[] lv;
    /**
     * 常用联系人姓名
     */
    private String name;
    /**
     * 常用联系人职位
     */
    private String position;
    /**
     * 常用联系人电话
     */
    private byte[] tel;
    /**
     * 电话号码图片的字节数组
     */
    private String numTel;

    @Override
    public String toString() {
        return "CommonContractBean{" +
                "lv=" + lv +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", tel=" + tel +
                ", numTel='" + numTel + '\'' +
                '}';
    }

    public CommonContractBean(byte[] lv, String name, String position, byte[] tel, String numTel) {
        this.lv = lv;
        this.name = name;
        this.position = position;
        this.tel = tel;
        this.numTel = numTel;
    }

    public String getNumTel() {

        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public CommonContractBean() {
    }

    public CommonContractBean(byte[] lv, String name, String position, byte[] tel) {
        this.lv = lv;
        this.name = name;
        this.position = position;
        this.tel = tel;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public byte[] getTel() {
        return tel;
    }

    public void setTel(byte[] tel) {
        this.tel = tel;
    }
}
