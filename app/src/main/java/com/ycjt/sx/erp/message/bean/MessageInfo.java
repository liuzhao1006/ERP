package com.ycjt.sx.erp.message.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 */
@Entity
public class MessageInfo {
    private int type;//消息类型
    private String content;//消息内容
    private String filepath;//图片路径
    private int sendState;//消息状态
    private String time;//消息时间
    private String header;//消息title
    private String imageUrl;//消息图片路径
    private long voiceTime;//语音通话时间
    private String msgId;//消息ID

    @Generated(hash = 1228616532)
    public MessageInfo(int type, String content, String filepath, int sendState,
                       String time, String header, String imageUrl, long voiceTime,
                       String msgId) {
        this.type = type;
        this.content = content;
        this.filepath = filepath;
        this.sendState = sendState;
        this.time = time;
        this.header = header;
        this.imageUrl = imageUrl;
        this.voiceTime = voiceTime;
        this.msgId = msgId;
    }

    @Generated(hash = 1292770546)
    public MessageInfo() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getSendState() {
        return sendState;
    }

    public void setSendState(int sendState) {
        this.sendState = sendState;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getVoiceTime() {
        return voiceTime;
    }

    public void setVoiceTime(long voiceTime) {
        this.voiceTime = voiceTime;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", filepath='" + filepath + '\'' +
                ", sendState=" + sendState +
                ", time='" + time + '\'' +
                ", header='" + header + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", voiceTime=" + voiceTime +
                ", msgId='" + msgId + '\'' +
                '}';
    }
}
