package com.example.test7_broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

public class BatteryLevelReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);

        float batteryPct = level/(float) scale;
        if (batteryPct <= 0.2){
            Log.d("BatteryLevel","处于低电量状态,当前电量为："+batteryPct);
        }
        else{
            Log.d("BatteryLevel","离开电量低状态，电量为："+batteryPct);
        }
    }
}