package com.example.clockapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by b5025189 on 11/05/2018.
 */

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"ALARM!",Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(alarmUri==null){
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        final Ringtone tone = RingtoneManager.getRingtone(context,alarmUri);
        tone.play();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                tone.stop();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,3000);
    }
}
