package com.example.bestoption.interfaces;

import com.example.bestoption.entity.Conversation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConversationInterface {

    @GET("cnv")
    Call<List<Conversation>> getall ();
}
