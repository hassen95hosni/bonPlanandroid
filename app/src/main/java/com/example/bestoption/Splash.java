package com.example.bestoption;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bestoption.entity.User;
import com.example.bestoption.interfaces.Notification;
import com.google.gson.Gson;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
/*
    private void sub (MqttAndroidClient client){
        String topic = "notification";
        int qos = 1;
        try {
            IMqttToken subToken = client.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                   Toast.makeText(getApplicationContext(),asyncActionToken.getResponse().toString(),Toast.LENGTH_SHORT).show();
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                            .setContentTitle("notification")
                            .setContentText("text here")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                    manager.notify(1,builder.build());
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
       //     String clientId = MqttClient.generateClientId();
         //   MqttAndroidClient client =
           //         new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883",
             //               clientId);

           // try {
               // IMqttToken token = client.connect();
                /*token.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                       Toast.makeText(getApplicationContext(),"connected success",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Toast.makeText(getApplicationContext(),"connected failed",Toast.LENGTH_SHORT).show();

                    }
                });
           */// } catch ( MqttException e) {
               // e.printStackTrace();
           // }
           // MqttConnectOptions options = new MqttConnectOptions();
            //options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
            /*try {
                IMqttToken token = client.connect(options);
            } catch (MqttException e) {
                e.printStackTrace();
            }
            */ ///sub(client);
        }catch(Exception e){

        }
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_splash);
        new Timer().schedule(new TimerTask(){
            public void run() {


                 SharedPreferences.Editor sh = getSharedPreferences("login", MODE_PRIVATE).edit();
                SharedPreferences shh = getSharedPreferences("login",MODE_PRIVATE);
                if(shh.contains("login")){
                    sh.putString("login","false");
                    startActivity(new Intent(Splash.this, MainActivity.class));

                }else {
                    startActivity(new Intent(Splash.this, mostKnown.class));
                }


                finish();

                Log.d("MainActivity:", "onCreate: waiting 5 seconds for MainActivity... loading PrimaryActivity.class");
            }
        }, 2000 );

    }
}
