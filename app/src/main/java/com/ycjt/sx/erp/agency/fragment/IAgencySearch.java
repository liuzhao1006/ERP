package com.ycjt.sx.erp.agency.fragment;

import com.ycjt.sx.erp.agency.bean.json.HomePageParseJson;

import java.util.List;

/**
 * Created by liuchao on 2017/6/22.
 * 输入框接口
 */

public interface IAgencySearch {
    /**
     * 访问网络失败
     *
     * @param s
     */
    void failed(String s);

    /**
     * 访问网络成功
     *
     * @param mList
     */
    void successedSearch(HomePageParseJson parseJson);

    void startDialog();

    void stopDialog();

    /**
     * 加载更多
     *
     * @param parseJson
     */
    void successedSearchMore(HomePageParseJson parseJson);
}
