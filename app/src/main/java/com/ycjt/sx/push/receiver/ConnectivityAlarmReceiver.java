package com.ycjt.sx.push.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ycjt.sx.push.Util;
import com.ycjt.sx.push.service.OnlineService;

public class ConnectivityAlarmReceiver extends BroadcastReceiver {

	public ConnectivityAlarmReceiver() {
		super();
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		if(Util.hasNetwork(context) == false){
			return;
		}
		Intent startSrv = new Intent(context, OnlineService.class);
		startSrv.putExtra("CMD", "RESET");
		context.startService(startSrv);
	}

}
