package com.example.bestoption.interfaces;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notification extends Application {
    public static final String channel="channel";
    NotificationChannel channelnot;
    @Override
    public void onCreate(){
        super.onCreate();
        createNotification();
    }
    public void createNotification(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
             channelnot=new NotificationChannel(
              channel,
                    "channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
             channelnot.setDescription("anything");
             NotificationManager  manager = getSystemService(NotificationManager.class);
             manager.createNotificationChannel(channelnot);


        }
    }
}
