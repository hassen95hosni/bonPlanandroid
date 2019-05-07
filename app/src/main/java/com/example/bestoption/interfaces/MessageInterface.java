package com.example.bestoption.interfaces;

import com.example.bestoption.entity.Message;
import com.example.bestoption.entity.Plans;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MessageInterface {
    @GET("message/get")
    Call<List<Message>> getall();


    @POST("message/post")
    Call <String> addOne(@Field("message") String text);

}
