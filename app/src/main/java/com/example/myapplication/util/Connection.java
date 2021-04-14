package com.example.myapplication.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class Connection {

    private static Retrofit retgro = null;
    private static  final String URL = "https://jsonplaceholder.typicode.com/";

    public static Retrofit getConnection(){
        if (retgro ==null){
            retgro = new Retrofit.Builder().baseUrl(URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retgro;
    }


}
