package com.example.bestoption;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bestoption.design.MyEditText;
import com.example.bestoption.entity.Global;
import com.example.bestoption.entity.Plans;
import com.example.bestoption.entity.User;
import com.example.bestoption.interfaces.PlanInterface;
import com.example.bestoption.interfaces.UserInetrface;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewPlan extends AppCompatActivity {
    private  static Retrofit retrofit = null;
    public static final String BASE_URL= "http://192.168.43.227:1330/";
    public String CHANNEL_ID="notification";

    private static final int result = 1 ;
    ImageView image;
    EditText name ;
    EditText dc;
    EditText dl;
    PlanInterface planInterface;

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void add(View view){
        name = (EditText) findViewById(R.id.nameplan);
        dc = (EditText) findViewById(R.id.court);
        dl = (EditText) findViewById(R.id.longd);
        Plans plans = new Plans();
        plans.setDescriptionLong(dl.getText().toString());
        plans.setDescriptionCourt(dl.getText().toString());
        plans.setName(name.getText().toString());

        if (isNetworkAvailable()){
            online(plans);
        }
        else offline(plans);


    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void offline (Plans plan){
       try {
           final Global global= (Global) getApplicationContext();
           List<Plans> plans= global.getPlans();
           plan.setUser(global.getUser());
           plans.add(plan);
           global.setPlans(plans);

       }catch (Exception e){
       }
           NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                   .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                   .setContentTitle(plan.getName())
                   .setContentText(plan.getDescriptionCourt())
                   //      .setChannelId("yes")
                   .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(this,mostKnown.class);
        PendingIntent contentInetnt = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentInetnt);
        manager.notify(0,builder.build());
        startActivity(new Intent(NewPlan.this,mostKnown.class));
    }
    public void online (Plans plans){

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        Call<String> call = planInterface.addOne(plans);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getApplicationContext(),"plan a été ajoute "+name.getText().toString(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NewPlan.this,mostKnown.class));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(),name.getText().toString()+"failure",Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void uploadimage(View view){
        startActivityForResult(
                new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                ),
                result
        );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        image = (ImageView) findViewById(R.id.image);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==result && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            image.setImageURI(selectedImage);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
