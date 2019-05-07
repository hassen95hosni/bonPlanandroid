package com.example.bestoption.interfaces;

import com.example.bestoption.entity.Plans;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlanInterface {
    @GET("plans")
    Call<List<Plans>> getallnonauth();

    @GET("plans/auth")
    Call<List<Plans>> getall();

    @GET("plansbycat/{id}")
    Call<List<Plans>> getByCategory(@Path("id") long id);

    @POST("plan/post")
    Call <String> addOne(@Field("plan") Plans plan);

    @GET("delete/{id}")
    Call<Plans> delete(@Path("id") long id);



}
