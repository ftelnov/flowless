package com.example.sirius.rs;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest extends Application {

    private static CategoryApi categoryApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://95.163.180.77") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        categoryApi = retrofit.create(CategoryApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static CategoryApi getApi() {
        return categoryApi;
    }
}