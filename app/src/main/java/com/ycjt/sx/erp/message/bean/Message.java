package com.ycjt.sx.erp.message.bean;

import com.ycjt.sx.erp.message.utils.MyTime;

import java.io.Serializable;

/**
 * Created by liuchao on 2017/7/20.
 */

public class Message implements Serializable {
    public String type;//
    public String from;//
    public String fromNick;//
    public int fromAvatar;//
    public String to; //
    public String content; //
    public String sendTime = MyTime.geTime(); //
}
