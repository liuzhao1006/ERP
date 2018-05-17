package com.ycjt.sx.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

//广播的动态定义
public class MyBroadCastRecever extends BroadcastReceiver {

    private Handler handler;

    public MyBroadCastRecever(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int count = intent.getIntExtra("msg", 0);
        //创建消息对象保存数据 Message对象需要一个what 表示  
        Message msg = handler.obtainMessage();
        msg.what = 100;
        msg.obj = count;
        //使用handler发送数据,需要一个Message对象,创建一个Message对象  
        handler.sendMessage(msg);
    }
}  