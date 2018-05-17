package com.ycjt.sx.erp.personal.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.base.BaseModel;
import com.ycjt.sx.erp.maillist.bean.json.MaillistJson;
import com.ycjt.sx.erp.personal.activity.IInfo;
import com.ycjt.sx.erp.personal.bean.InfoJson;
import com.ycjt.sx.erp.personal.model.SettingContactInfomationModel;
import com.ycjt.sx.utils.PrefUtils;

/**
 * Created by liuchao on 2017/6/28.
 */

public class PersonalInfoPresenter {

    private IInfo info;

    public PersonalInfoPresenter(IInfo info) {
        this.info = info;
    }

    public void getInfo(String url) {
        SettingContactInfomationModel model = new SettingContactInfomationModel(url);
        model.getData();
        model.setOnAddRequest(new BaseModel.OnAddRequest() {
            @Override
            public void successed(String s) {
                //服务器出错, 请稍后进入,或者联系管理员......
                if (s.equals("服务器出错, 请稍后进入,或者联系管理员......")) {
                    info.failed(s);
                    return;
                }

                //解析数据
                //先解析出第一层
                JSONObject jsonObject = JSON.parseObject(s);
                InfoJson agencyJson = JSON.parseObject(s, InfoJson.class);
                String code = agencyJson.getCode();
                String data = agencyJson.getData();
                String message = agencyJson.getMessage();

                JSONObject datas = JSON.parseObject(data);
                InfoJson.InfoData infoData = JSON.parseObject(data, InfoJson.InfoData.class);


                info.successed(infoData);
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
}
