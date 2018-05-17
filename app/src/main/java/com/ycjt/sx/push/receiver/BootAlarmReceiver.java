package com.ycjt.sx.push.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ycjt.sx.push.service.OnlineService;

public class BootAlarmReceiver extends BroadcastReceiver {

	public BootAlarmReceiver() {

	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent startSrv = new Intent(context, OnlineService.class);
		startSrv.putExtra("CMD", "TICK");
		context.startService(startSrv);
	}

}
