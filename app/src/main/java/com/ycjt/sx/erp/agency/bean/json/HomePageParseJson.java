package com.ycjt.sx.erp.agency.bean.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson.AgencyDataJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuchao on 2017/6/27.
 * 首页json解析类
 */

public class HomePageParseJson {

    private List<AgencyDataJson> json;

    private ArrayList<AgencyDataJson> agencyList = new ArrayList<>();
    private ArrayList<AgencyDataJson> haveToDoList = new ArrayList<>();
    private ArrayList<AgencyDataJson> yesIDoList = new ArrayList<>();
    private ArrayList<AgencyDataJson> data = new ArrayList<>();

    public HomePageParseJson(List<AgencyDataJson> json) {
        this.json = json;


        //获取到数据,首先按照分类进行处理
        for (int i = 0; i < json.size(); i++) {
            AgencyDataJson dataJson = json.get(i);
            String comment = dataJson.getComment();
            String flowID = dataJson.getFlowID();
            Object instanceID = dataJson.getInstanceID();
            String orderStyle = dataJson.getOrderStyle();
            String position = dataJson.getPosition();
            String senderName = dataJson.getSenderName();
            String senderTime = dataJson.getSenderTime();
            String status = dataJson.getStatus();
            String title = dataJson.getTitle();
            if (orderStyle.equals("待办")) agencyList.add(dataJson);
            if (orderStyle.equals("已办")) haveToDoList.add(dataJson);
            if (orderStyle.equals("由我发起")) yesIDoList.add(dataJson);
            data.add(dataJson);
        }
    }

    @Override
    public String toString() {
        return "HomePageParseJson{" +
                "json=" + json +
                ", agencyList=" + agencyList +
                ", haveToDoList=" + haveToDoList +
                ", yesIDoList=" + yesIDoList +
                ", data=" + data +
                ", i=" + i +
                '}';
    }

    public ArrayList<AgencyDataJson> parseAgency() {
        return data;
    }


    /**
     * 解析代办数据
     *
     * @return
     */
    public ArrayList<AgencyDataJson> parseAgencyData() {
        return agencyList;
    }

    /**
     * 解析已读数据
     *
     * @return
     */
    public ArrayList<AgencyDataJson> parseHaveToDoData() {
        return haveToDoList;
    }

    /**
     * 解析有我发起数据
     *
     * @return
     */
    public ArrayList<AgencyDataJson> parseYesIDoData() {
        return yesIDoList;
    }

    /**
     * 获取待办未读状态
     *
     * @param mList
     * @return
     */
    private int i = 0;

    public int getCountState(ArrayList<AgencyDataJson> mList) {

        for (int i = 0; i < mList.size(); i++) {
            AgencyDataJson dataJson = mList.get(i);
            String status = dataJson.getStatus();
            //展示图标
            if (status.equals("0")) i++;
        }
        return i;
    }
}
