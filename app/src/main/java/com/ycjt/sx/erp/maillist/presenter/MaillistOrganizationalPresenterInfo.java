package com.ycjt.sx.erp.maillist.presenter;

import android.graphics.Bitmap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.base.BaseModel;
import com.ycjt.sx.erp.maillist.activiy.IMaillistItemPersonalInfo;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;
import com.ycjt.sx.erp.maillist.bean.json.MaillistJson;
import com.ycjt.sx.erp.maillist.model.MaillistItemPersonalInfoModel;

import java.util.List;

/**
 * Created by liuchao on 2017/6/21.
 */

public class MaillistOrganizationalPresenterInfo {

    private IMaillistItemPersonalInfo info;

    public MaillistOrganizationalPresenterInfo(IMaillistItemPersonalInfo info) {
        this.info = info;
    }

    /**
     * 获取组织机构返回
     */
    public void getInfo(String url) {
        MaillistItemPersonalInfoModel maillistModel = new MaillistItemPersonalInfoModel(url);
        maillistModel.getData();
        maillistModel.setOnAddRequest(new BaseModel.OnAddRequest() {

            @Override
            public void successed(String s) {

                //先解析出第一层
                JSONObject jsonObject = JSON.parseObject(s);
                MaillistJson agencyJson = JSON.parseObject(s, MaillistJson.class);
                String code = agencyJson.getCode();
                String data = agencyJson.getData();
                String message = agencyJson.getMessage();
                if (!code.equalsIgnoreCase("0") ||
                        !message.equalsIgnoreCase("获取组织机构成功")) {//登录失败
                    //获取信息不正确
                    return;
                }
                List<MaillistDataJson> dataList = JSON.parseArray(data, MaillistDataJson.class);
                LogUtils.i("code :" + code + ",data : " + dataList.toString() + ",message :" + message);

                info.successed(s);


            }

            @Override
            public void failed(String s) {
                info.failed(s);
            }

            @Override
            public void startDialog() {
            }

            @Override
            public void stopDialog() {
            }
        });
    }


    public void getBitInfo(String s) {
        MaillistItemPersonalInfoModel model = new MaillistItemPersonalInfoModel(s);
        model.getBitMap();
        model.setOnAddBitMapRequest(new BaseModel.OnAddBitMapRequest() {
            @Override
            public void successedBit(Bitmap bitmap) {
                info.bitMap(bitmap);

            }

            @Override
            public void failedBit(String s) {
                info.bitMapFailed(s);
            }

            @Override
            public void startDialogBit() {

            }

            @Override
            public void stopDialogBit() {

            }
        });
    }


}
