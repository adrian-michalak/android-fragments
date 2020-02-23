package com.example.adrianmichalak.fragment_michalak.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {

    private static RestInterface serviceRest;
    private static OkHttpClient okHttpClient; // zapewnia ze dla kazdego zapytania do API bedziemy miec jednego klienta
    private static Gson gson;

    private Rest(){}

    public static RestInterface getRest() {return serviceRest;}

    public static void init()
    {
        gson = new GsonBuilder().create();
        okHttpClient=new OkHttpClient.Builder().build();
// https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
                .client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
        serviceRest = retrofit.create(RestInterface.class);
    }
}
