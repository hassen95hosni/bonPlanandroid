package com.example.bestoption;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestoption.design.MyEditText;
import com.example.bestoption.entity.Global;
import com.example.bestoption.entity.User;
import com.example.bestoption.interfaces.UserInetrface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signup extends AppCompatActivity {
        MyEditText name ;
        MyEditText familyname;
        MyEditText password;
        MyEditText email;
        MyEditText repassword;
    private  static Retrofit retrofit = null;
    public static final String BASE_URL= "http://192.168.43.227:1330/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        name = (MyEditText) findViewById(R.id.name);
        familyname = (MyEditText) findViewById(R.id.familyname);
        email = (MyEditText) findViewById(R.id.emailsignup);
        password= (MyEditText) findViewById(R.id.passwordsignup);
        repassword = (MyEditText) findViewById(R.id.repass);
        setContentView(R.layout.activity_signup);
    }


    public void offline (){
        name = (MyEditText) findViewById(R.id.name);
        familyname=(MyEditText) findViewById(R.id.familyname);
        email = (MyEditText) findViewById(R.id.emailsignup);
        password=(MyEditText) findViewById(R.id.passwordsignup);
        User users = new User();

        //  users.setName("");
        users.setName(name.getText().toString());
        users.setLastname(familyname.getText().toString());
        users.setEmail(email.getText().toString());
        users.setPassword(password.getText().toString());
        final Global global= (Global) getApplicationContext();
        global.setUser(users);
        List<User> users1 = new  ArrayList<User>();
        try{
            users1.addAll( global.getUsers());

        }catch (Exception e){

        }
       users1.add(users);
        global.setUsers(users1);
        Toast.makeText(getApplicationContext(),"you are now logged in "+name.getText().toString(),Toast.LENGTH_SHORT).show();
        SharedPreferences she = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor sh = getSharedPreferences("login",MODE_PRIVATE).edit();
        sh.putString("login","True");
        sh.commit();
        startActivity(new Intent(signup.this,mostKnown.class));
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
    public void online (){

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

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void signup (View v ){
        password = (MyEditText) findViewById(R.id.passwordsignup);
        TextView not = (TextView) findViewById(R.id.not);
        Toast.makeText(getApplicationContext(),password.getText().toString(),Toast.LENGTH_LONG).show();

        if (
        //        !passwordVerification(password.getText().toString())&&sqlInjection(password.getText().toString())
       password.getText().toString().matches("[A-Za-z0-9 ]*")
        ){
            not.setText("email ou password n'est pas valide . email doit etre en format xx@xx.x et password doit avoir un majuscule et minuscule et nombre et au minimum de 8 caracteres" );
        }else
        {

            if (isNetworkAvailable()){
                online();
            }
            else offline();

        }


    }
    public Boolean passwordVerification(String password){
        Pattern pattern = Pattern.compile("[A-Za-z0-9]");
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.find();
        if (password.length()>8 && result){
             pattern = Pattern.compile( "[0-9]" );
             matcher = pattern.matcher( password );
             result = matcher.find();
            return result ;

        }
        else return false;
    }


        public Boolean sqlInjection(String text){
            if(text.contains("'")|| text.contains("Select")||text.contains("drop")||text.contains("from")){
                return false;
            }
            else return true;
        }






















}
