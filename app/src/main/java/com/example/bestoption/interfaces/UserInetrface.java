package com.example.bestoption.interfaces;

import com.example.bestoption.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserInetrface {

    @GET("allz")
    Call<User> getall();

    @POST("loginin")
    Call<User> login(String email, String password);

    @POST("inscrit")
    Call<String> isncrit(User user);

}
