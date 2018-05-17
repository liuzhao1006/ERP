package com.ycjt.sx.erp.message.bean;

import com.ycjt.sx.base.BaseBean;

import java.io.Serializable;

/**
 * Created by liuchao on 2017/7/19.
 */

public class Messagebean extends BaseBean {

    private String image;//图像
    private String title;//姓名
    private String content;//内容
    private int type;//类型
    private long time;//发送,接收时间
    private int messageCount;//未读消息数量


    public Messagebean(String image, String title, String content, int type, long time, int messageCount) {
        this.messageCount = messageCount;
        this.time = time;
        this.image = image;
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Messagebean() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Messagebean{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", time=" + time +
                ", messageCount=" + messageCount +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
