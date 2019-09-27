package com.smartwashr.driver.fcm_services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.smartwashr.driver.utils.Constants;
import com.smartwashr.driver.utils.SessionManager;

/**
 * Created by zeeshan on 7/5/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    private SessionManager sessionManager;

    @Override
    public void onTokenRefresh() {
        sessionManager = new SessionManager(MyFirebaseInstanceIDService.this);
        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sessionManager.put(Constants.DEVICE_TOKEN, refreshedToken);
    }

}
