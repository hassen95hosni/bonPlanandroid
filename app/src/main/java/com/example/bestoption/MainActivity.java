package com.example.bestoption;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.bestoption.entity.User;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {


    public void signin (View v ){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }
    public void signup (View v ){
        startActivity(new Intent(MainActivity.this,signup.class));
    }
    public void skip (View v ){
        SharedPreferences.Editor sh = getSharedPreferences("login", MODE_PRIVATE).edit();
        sh.putString("login","false");
        sh.commit();
        startActivity(new Intent(MainActivity.this,mostKnown.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);


    }

}
