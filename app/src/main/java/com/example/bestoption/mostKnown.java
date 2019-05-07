package com.example.bestoption;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestoption.entity.User;
import com.google.gson.Gson;

public class mostKnown extends AppCompatActivity {

    //private TextView mTextMessage;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_known);
        SharedPreferences sh = getSharedPreferences("login",MODE_PRIVATE);

        //Toast.makeText(getApplicationContext(),sh.getString("user",""),Toast.LENGTH_SHORT).show();
        //Gson gson = new Gson();
        //User user = gson.fromJson(sh.getString("user",""),User.class);
        //Toast.makeText(getApplicationContext(),user.getEmail(),Toast.LENGTH_SHORT).show();
        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //user.setEmail("");
        if (sh.getString("login","false").equals("false")){
            BottomNavigationItemView item = (BottomNavigationItemView) findViewById(R.id.conversation);
            item.setEnabled(false);
            BottomNavigationItemView itemprofile = (BottomNavigationItemView) findViewById(R.id.profile);
            itemprofile.setEnabled(false);
            BottomNavigationItemView itemnav = (BottomNavigationItemView) findViewById(R.id.navigation_dashboard);
            itemnav.setEnabled(false);
            BottomNavigationItemView itemnot = (BottomNavigationItemView) findViewById(R.id.navigation_notifications);
            itemnot.setEnabled(false);

        }else
        {
            BottomNavigationItemView item = (BottomNavigationItemView) findViewById(R.id.conversation);
            item.setEnabled(true);
            BottomNavigationItemView itemprofile = (BottomNavigationItemView) findViewById(R.id.profile);
            itemprofile.setEnabled(true);
            BottomNavigationItemView itemnav = (BottomNavigationItemView) findViewById(R.id.navigation_dashboard);
            itemnav.setEnabled(true);
            BottomNavigationItemView itemnot = (BottomNavigationItemView) findViewById(R.id.navigation_notifications);
            itemnot.setEnabled(true);


            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        }
        Fragment fragment = new mostKnownf();
        loadFragment(fragment);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
      //              mTextMessage.setText("most known");
                    fragment = new mostKnownf();
                    loadFragment(fragment);
                    return true;
                case R.id.conversation:
        //            mTextMessage.setText("conversations");
                    fragment = new Conversation();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
          //          mTextMessage.setText("categories");
                    fragment = new categoriesf();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
            //        mTextMessage.setText("favorites");
                    fragment= new favoritesf();
                    loadFragment(fragment);
                    return true;
                case R.id.profile:
              //      mTextMessage.setText("profile");
                    fragment = new profilef();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener nonauthmOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //              mTextMessage.setText("most known");
                    fragment = new mostKnownf();
                    loadFragment(fragment);
                    return true;
                case R.id.conversation:
                    //            mTextMessage.setText("conversations");
                    Toast.makeText(getApplicationContext(),"not allowed",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    //          mTextMessage.setText("categories");
                    Toast.makeText(getApplicationContext(),"not allowed",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    //        mTextMessage.setText("favorites");
                    Toast.makeText(getApplicationContext(),"not allowed",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.profile:
                    //      mTextMessage.setText("profile");
                    Toast.makeText(getApplicationContext(),"not allowed",Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
