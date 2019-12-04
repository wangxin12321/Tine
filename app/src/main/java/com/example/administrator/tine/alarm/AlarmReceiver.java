package com.example.administrator.tine.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

/**
 * Created by Jay on 2015/10/25 0025.
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=intent.getStringExtra("message");
        Log.i("123123","12312312312");
        NotificationHelper notificationHelper = new NotificationHelper(context,msg);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());
    }
}

