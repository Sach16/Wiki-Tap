package com.skpissay.baseproject.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient {
    private static ApiInterface apiService;

    public static ApiInterface getAPIService() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (apiService == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.interceptors().add(httpLoggingInterceptor);
            Retrofit.Builder builder =
                    new Retrofit.Builder()
                            .baseUrl("http://192.168.1.102:8080")
                            .addConverterFactory(
                                    GsonConverterFactory.create()
                            );
            Retrofit retrofit = builder.client(httpClient.build()).build();
            apiService = retrofit.create(ApiInterface.class);
        }
        return apiService;
    }

}