package com.ycjt.sx.erp.agency.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.base.BaseModel;
import com.ycjt.sx.erp.agency.activity.IAgencyDetails;
import com.ycjt.sx.erp.agency.bean.json.AgencyDetailsDataJson;
import com.ycjt.sx.erp.agency.bean.json.AgencyDetailsJson;
import com.ycjt.sx.erp.agency.model.AgencyDeaitlModel;

import java.util.List;

/**
 * Created by liuchao on 2017/6/19.
 */

public class AgencyDetailsPresenter {


    private IAgencyDetails wait;
    /**
     * 接口地址
     */
    private String url;
    private String s1;

    public AgencyDetailsPresenter(IAgencyDetails wait, String url, String s1) {
        this.wait = wait;
        this.url = url;
        this.s1 = s1;
    }

    /**
     * 获取待办内容--集合形式返回
     */
    public void getWaitMessage() {
        AgencyDeaitlModel agencyWaitModel = new AgencyDeaitlModel(url);
        agencyWaitModel.getData();
        agencyWaitModel.setOnAddRequest(new BaseModel.OnAddRequest() {

            @Override
            public void successed(String s) {

                if (s.equals("服务器出错, 请稍后进入,或者联系管理员......")) {
                    wait.failed(s);
                    return;
                }

                //先解析出第一层
                LogUtils.i(s);
                JSONObject jsonObject = JSON.parseObject(s);
                AgencyDetailsJson agencyJson = JSON.parseObject(s, AgencyDetailsJson.class);
                String code = agencyJson.getCode();
                String data = agencyJson.getData();
                List<AgencyDetailsDataJson> dataList = JSON.parseArray(data, AgencyDetailsDataJson.class);
                String message = agencyJson.getMessage();
                if (!code.equalsIgnoreCase("0") ||
                        !message.equalsIgnoreCase("获取人员信息成功")) {//登录失败
                    //获取信息不正确
                }

                //数据没问题
//                DetailsParseJson parseJson = new DetailsParseJson(dataList);//这里不需要处理

                wait.successed(dataList);
            }

            @Override
            public void failed(String s) {
//                if (!TextUtils.isEmpty(s)) wait.failed(s);
//                wait.failed("网络异常,或者服务器出错,请联系管理员");
            }

            @Override
            public void startDialog() {
//                wait.startDialog();
            }

            @Override
            public void stopDialog() {
//                wait.stopDialog();
            }
        });
    }
}
