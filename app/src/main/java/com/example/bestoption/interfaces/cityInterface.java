package com.example.bestoption.interfaces;

import com.example.bestoption.entity.City;

import retrofit2.Call;
import retrofit2.http.GET;

public interface cityInterface {
    @GET("city/all")
    Call<City> get();
}
