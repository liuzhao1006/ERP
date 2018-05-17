package com.ycjt.sx.erp.personal.bean;

import android.widget.ImageView;

import com.ycjt.sx.base.BaseBean;

import java.util.Arrays;

/**
 * Created by liuchao on 2017/6/23.
 */

public class PersonalInfoBean extends BaseBean {

    /**
     * 个人图像的二进制
     */
    private byte[] iv;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电话
     */
    private String tel;
    /**
     * 部门
     */
    private String department;

    public PersonalInfoBean(byte[] iv, String name, String tel, String department) {
        this.iv = iv;
        this.name = name;
        this.tel = tel;
        this.department = department;
    }

    public PersonalInfoBean() {
    }

    @Override
    public String toString() {
        return "PersonalInfoBean{" +
                "iv=" + Arrays.toString(iv) +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public byte[] getIv() {
        return iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
