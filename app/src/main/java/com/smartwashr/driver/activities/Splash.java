package com.smartwashr.driver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.smartwashr.driver.R;
import com.smartwashr.driver.utils.SessionManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Splash extends AppCompatActivity {

    private SessionManager sessionManager;
    private FirebaseAnalytics mFirebaseAnalytics;
    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.getAppInstanceId();
        sessionManager = new SessionManager(Splash.this);
        if (sessionManager.checkLogin()) {
            Runnable task = new Runnable() {
                public void run() {
                    startActivity(new Intent(Splash.this, HomeActivity.class));
                    finish();
                }
            };
            worker.schedule(task, 3000, TimeUnit.MILLISECONDS);
        } else {
            Runnable task = new Runnable() {
                public void run() {
                    startActivity(new Intent(Splash.this, Login.class));
                    finish();
                }
            };
            worker.schedule(task, 3000, TimeUnit.MILLISECONDS);
        }
//

        setContentView(R.layout.activity_splash);

        // Obtain the FirebaseAnalytics instance.

    }

}
