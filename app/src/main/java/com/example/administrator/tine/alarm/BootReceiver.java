package com.example.administrator.tine.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Toast.makeText(context, "启动了" , Toast.LENGTH_SHORT).show();
            Intent toIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            toIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(toIntent);
        }
    }

}
