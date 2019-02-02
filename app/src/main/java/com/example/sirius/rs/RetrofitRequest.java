package com.example.sirius.rs;

import android.app.Application;

import Api.API;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest extends Application {

    private static API letsDoThis;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://95.163.180.77")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        letsDoThis = retrofit.create(API.class);
    }

    public static API getApi() {
        return letsDoThis;
    }
}