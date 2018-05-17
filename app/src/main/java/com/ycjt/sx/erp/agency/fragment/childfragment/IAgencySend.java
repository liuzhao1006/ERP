package com.ycjt.sx.erp.agency.fragment.childfragment;

import com.ycjt.sx.erp.agency.bean.json.HomePageParseJson;

/**
 * Created by liuchao on 2017/6/19.
 */

public interface IAgencySend {
    /**
     * 获取网络数据成功,返回一个json格式的字符串
     *
     * @param parseJson
     * @return
     */
    void successed(HomePageParseJson parseJson);

    /**
     * 获取网络数据失败,返回一个错误信息
     *
     * @param s
     * @return
     */
    void failed(String s);

    void startDialog();

    void stopDialog();

    /**
     * 加载更多
     *
     * @param parseJson
     */
    void successedMore(HomePageParseJson parseJson);
}
