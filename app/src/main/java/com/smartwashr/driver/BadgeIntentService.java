package com.smartwashr.driver;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import com.smartwashr.driver.utils.Constants;
import com.smartwashr.driver.utils.SessionManager;

public class BadgeIntentService extends IntentService {

    private int notificationId = 0;

    public BadgeIntentService() {
        super("BadgeIntentService");
    }

    private NotificationManager mNotificationManager;

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            SessionManager sessionManager = new SessionManager(this);
            int badgeCount = intent.getIntExtra("badgeCount", sessionManager.getInt(Constants.BADGECOUNT));

//            ShortcutBadger.applyNotification(getApplicationContext(), notification, badgeCount);

        }
    }
}