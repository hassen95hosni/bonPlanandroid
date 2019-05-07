package com.example.bestoption;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

import com.example.bestoption.interfaces.Notification;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MyBroadcastReciever extends BroadcastReceiver {
    private static Context mContext;
    public NotificationManagerCompat not;
    public static Context getContext() {
        return mContext;
    }

    public MyBroadcastReciever() {
        not = NotificationManagerCompat.from(getContext());
    }


    @Override
    public void onReceive(Context context, Intent intent){
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            String clientId = MqttClient.generateClientId();
            MqttAndroidClient client =
                    new MqttAndroidClient(getContext(), "tcp://broker.hivemq.com:1883",
                            clientId);

            try {
                IMqttToken token = client.connect();
                token.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        // We are connected
                        // Log.d(TAG, "onSuccess");
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        // Something went wrong e.g. connection timeout or firewall problems
                        //Log.d(TAG, "onFailure");

                    }
                });
            } catch ( MqttException e) {
                e.printStackTrace();
            }
            MqttConnectOptions options = new MqttConnectOptions();
            options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
            try {
                IMqttToken token = client.connect(options);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){

        }
    }

    private void sub (MqttAndroidClient client){
        String topic = "notification";
        int qos = 1;
        try {
            IMqttToken subToken = client.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // The message was published
                    addnot(asyncActionToken.getResponse().toString(),asyncActionToken.getResponse().toString());
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // The subscription could not be performed, maybe the user was not
                    // authorized to subscribe on the specified topic e.g. using wildcards

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void addnot(String title , String msg){
        android.app.Notification notification = new NotificationCompat.Builder(getContext(),Notification.channel)
                .setContentTitle(title)
                .setContentText(msg).build();
        not.notify(1,notification);
    }

}
