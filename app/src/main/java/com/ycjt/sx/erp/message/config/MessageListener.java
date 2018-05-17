package com.ycjt.sx.erp.message.config;

import com.ycjt.sx.erp.message.bean.Message;

/**
 * Created by liuchao on 2017/7/20.
 */

public interface MessageListener {

    void OnMessageReceive(Message msg);
}
