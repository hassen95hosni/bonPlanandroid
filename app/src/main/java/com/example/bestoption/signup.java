package com.example.bestoption;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bestoption.entity.User;
import com.example.bestoption.interfaces.UserInetrface;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signup extends AppCompatActivity {
        EditText name ;
        EditText familyname;
        EditText password;
        EditText email;
        EditText repassword;
    private  static Retrofit retrofit = null;
    public static final String BASE_URL= "http://192.168.43.227:1330/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = (EditText) findViewById(R.id.name);
        familyname = (EditText) findViewById(R.id.familyname);
        email = (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repass);
        setContentView(R.layout.activity_signup);
    }




    public void signup (View v ){

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        UserInetrface userInetrface = retrofit.create(UserInetrface.class);
        SharedPreferences.Editor sheditor = getSharedPreferences("user",MODE_PRIVATE).edit();
        User users = new User();

        //  users.setName("");
        users.setLastname(familyname.getText().toString());
        users.setEmail(email.getText().toString());
        users.setPassword(password.getText().toString());
        SharedPreferences.Editor sheditors = getSharedPreferences("user",MODE_PRIVATE).edit();
        sheditors.putString("email",email.getText().toString());
        sheditors.putString("lastname",familyname.getText().toString());
        Gson gson = new Gson();
        String user = gson.toJson(users.toString());
        sheditor.putString("user",user);
        sheditor.commit();

        Call<String> call = userInetrface.isncrit(users);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getApplicationContext(),"you are now logged in "+name.getText().toString(),Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor sh = getSharedPreferences("login",MODE_PRIVATE).edit();
                sh.putString("login","True");
                sh.commit();
                startActivity(new Intent(signup.this,mostKnown.class));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(),name.getText().toString()+"failure",Toast.LENGTH_SHORT).show();

            }
        });

        //  Gson gson = new Gson();
        //if(password.getText().toString().equals(repassword.getText().toString())){
         //   String user = gson.toJson(users);
        //    sheditor.putString("user",user);
        //    sheditor.commit();

        //}
        //else {
          //  Toast.makeText(getApplicationContext(),"password is not confirmed",Toast.LENGTH_SHORT).show();
      //  }
    }
}
