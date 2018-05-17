package com.ycjt.sx.app;

import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.erp.message.bean.Message;
import com.ycjt.sx.erp.message.config.MessageListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by liuchao on 2017/7/20.
 */

public class LongConnect extends Thread {

    private String ip;
    private int port;
    private Socket socket = null;
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;

    private ArrayList<MessageListener> mList = new ArrayList<>();

    public void addListener(MessageListener listener) {
        this.mList.add(listener);
    }

    public void removeListener(MessageListener listener) {
        this.mList.remove(listener);
    }


    public LongConnect(String ip, int port) {
        this.ip = ip;
        this.port = port;

    }

    /**
     * 连接服务器
     */
    public void connet() {
        try {
            LogUtils.i("连接开始");
            socket = new Socket(ip, port);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            flag = true;
            super.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 断开服务器
     */
    public void disConnet() {
        try {
            flag = false;
            if (inputStream != null)
                inputStream.close();
            if (outputStream != null)
                outputStream.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     */
    public void sendMessage(Message msg) {
        if (outputStream != null) {
//            String message = XMLUtils.toXML(msg);
//            try {
////                outputStream.writeUTF(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    private boolean flag;

    /**
     * 接收消息
     */
    @Override
    public void run() {
        super.run();
        while (flag) {
            try {
                String s = inputStream.readUTF();
//                Message message = XMLUtils.fromXML(s, Message.class);

        if (mList != null && mList.size() > 0) {
            for (MessageListener listener : mList) {
//                        listener.OnMessageReceive(message);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    }
}
