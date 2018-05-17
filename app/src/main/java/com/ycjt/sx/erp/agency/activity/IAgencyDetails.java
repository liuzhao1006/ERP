package com.ycjt.sx.erp.agency.activity;

import com.ycjt.sx.erp.agency.bean.json.AgencyDetailsDataJson;
import com.ycjt.sx.erp.agency.bean.json.DetailsParseJson;

import java.util.List;

/**
 * Created by liuchao on 2017/6/27.
 */

public interface IAgencyDetails {
    /**
     * 获取数据成功
     *
     * @param dataList
     */
    void successed(List<AgencyDetailsDataJson> dataList);

    /**
     * 失败
     * @param s
     */
    void failed(String s);
}
