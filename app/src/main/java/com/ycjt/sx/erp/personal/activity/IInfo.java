package com.ycjt.sx.erp.personal.activity;

import com.ycjt.sx.erp.personal.bean.InfoJson;

/**
 * Created by liuchao on 2017/6/28.
 */

public interface IInfo {
    void successed(InfoJson.InfoData infoData);

    void failed(String s);
}
