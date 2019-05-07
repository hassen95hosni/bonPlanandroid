package com.example.bestoption;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        startActivity(new Intent(MainActivity.this,mostKnown.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void skip(){
        SharedPreferences.Editor sheditor = getSharedPreferences("user",MODE_PRIVATE).edit();
        Gson gson = new Gson();
        User users = new User();
        users.setEmail("");
        String user = gson.toJson(users);
        sheditor.putString("user",user);
        sheditor.commit();
        Toast.makeText(getApplicationContext(),user,Toast.LENGTH_SHORT).show();

        startActivity(new Intent(MainActivity.this,mostKnown.class));
    }
}
