package com.example.myapplication.api;

import com.example.myapplication.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceApi {
    @GET("posts")
    public  abstract Call<List<User>> lista();
}
