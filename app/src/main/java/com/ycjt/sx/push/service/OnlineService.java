package com.ycjt.sx.push.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * 消息推送的服务
 */
public class OnlineService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
