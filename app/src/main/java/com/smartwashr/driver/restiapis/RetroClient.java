package com.smartwashr.driver.restiapis;

import com.smartwashr.driver.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zeeshan on 6/2/17.
 */

public class RetroClient {
    private static RetroClient object;
    private Retrofit retrofit = null;
    private RestApis service;

    private RetroClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY));
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVER_IP)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        service = retrofit.create(RestApis.class);
    }

    public static RetroClient getRetroClient() {
        if (object == null) {
            object = new RetroClient();
        } else if (object != null && !object.getRetrofit().baseUrl().toString().equalsIgnoreCase(Constants.SERVER_IP)) {
            object = new RetroClient();
        }
        return object;
    }

    public RestApis getApiServices() {
        return object.service;
    }

    public Retrofit getRetrofit() {
        return object.retrofit;
    }
}

