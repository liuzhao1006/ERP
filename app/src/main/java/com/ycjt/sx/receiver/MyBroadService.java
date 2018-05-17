package com.ycjt.sx.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 2017年07月10日22:56:21
 * 发送代办数量的广播接收者
 */
public class MyBroadService extends BroadcastReceiver {
    //广播的程序是自己执行的,不需要我们手动操作,只需要订阅号相关的配置就可以了(使用意图过滤器配置一个Action);
    @Override
    public void onReceive(Context context, Intent intent) {

        //接收intent传送的参数  
        int str = intent.getIntExtra("msg", 0);

    }

}  