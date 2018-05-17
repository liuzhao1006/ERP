package com.ycjt.sx.erp.message.config;

/**
 * Created by liuchao on 2017/7/17.
 * 消息数据接收类
 */

public class MessageUrlBean {
    /**
     * 展示图片路径
     */
    private String image;

    /**
     * 姓名
     */
    private String name;

    /**
     * 展示部分内容
     */
    private String content;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageUrlBean(String image, String name, String content) {
        this.image = image;
        this.name = name;
        this.content = content;
    }

    public MessageUrlBean() {
    }

    @Override
    public String toString() {
        return "MessageUrlBean{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
