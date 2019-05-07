package com.example.bestoption.interfaces;

import com.example.bestoption.entity.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryInterface {
    @GET("category")
    Call<List<Category>> getall();
}
