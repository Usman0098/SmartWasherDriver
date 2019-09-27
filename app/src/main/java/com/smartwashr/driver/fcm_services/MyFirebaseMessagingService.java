package com.smartwashr.driver.fcm_services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.smartwashr.driver.BadgeIntentService;
import com.smartwashr.driver.R;
import com.smartwashr.driver.activities.HomeActivity;
import com.smartwashr.driver.utils.Constants;
import com.smartwashr.driver.utils.MyNotificationManager;
import com.smartwashr.driver.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * Created by zeeshan on 7/5/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    //    private static final String TAG = "MyFirebaseMsgService";
    private static final String TAG = "FCM";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                Map<String, String> params = remoteMessage.getData();
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                JSONObject data = json.getJSONObject("data");
                int badgeCount = data.getInt("count");
                ShortcutBadger.applyCount(this, badgeCount);
                startService(
                        new Intent(this, BadgeIntentService.class).putExtra("badgeCount", badgeCount)
                );
                sendPushNotification(json);
                sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle().toString());
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }

    }


    //this method will display the notification
    //We are passing the JSONObject that is received from
    //firebase cloud messaging
    private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data
            JSONObject data = json.getJSONObject("data");
            //parsing json data
            String title = data.getString("title");
            String message = data.getString("message");
            int badgeCount = data.getInt("count");
            SessionManager sessionManager = new SessionManager(this);
            sessionManager.put(Constants.BADGECOUNT, badgeCount);
            ShortcutBadger.applyCount(this, badgeCount);
//            String imageUrl = "https://pbs.twimg.com/profile_images/850467251945910273/o77sszpE_400x400.jpg";

            //creating MyNotificationManager object
            MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());

            //creating an intent for the notification
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("removeBadge", "remove");
            ShortcutBadger.applyCount(this, badgeCount);
            //if there is no image
//            if (imageUrl.equals("null")) {
                //displaying small notification
                mNotificationManager.showSmallNotification(title, message, intent);
//            } else {
                //if there is an image
                //displaying a big notification
//                mNotificationManager.showBigNotification(title, message, imageUrl, intent);
//            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    private void sendNotification(String message, String title) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this/*context*/, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.app_icon);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
        notificationBuilder.setContentText(message);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSound(defaultSoundUri);
        notificationBuilder.setLights(Color.GREEN, 1, 1);
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify((int) System.currentTimeMillis(), notificationBuilder.build());
    }

}
