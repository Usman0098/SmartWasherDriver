package com.smartwashr.driver;

import android.app.Application;

import com.smartwashr.driver.restiapis.RestApis;
import com.smartwashr.driver.restiapis.RetroClient;

import retrofit2.Retrofit;

/**
 * Created by zeeshan on 6/2/17.
 */

public class SmartWashr extends Application {
    private static SmartWashr _instance;


    public static SmartWashr getAppContext() {
        return _instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
    }


    public static synchronized SmartWashr getInstance() {
        return _instance;
    }

    public static RestApis getRestClient() {
        return RetroClient.getRetroClient().getApiServices();
    }


    public static Retrofit getRetrofit() {
        return RetroClient.getRetroClient().getRetrofit();
    }

}
