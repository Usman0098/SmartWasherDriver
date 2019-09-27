package com.smartwashr.driver.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

/**
 * Created by zeeshan on 6/2/17.
 */

public class Loading {
    private static ProgressDialog pdialog;

    public static void show(Context context, boolean cancelable, String message) {

        try {
            pdialog = new ProgressDialog(context);
            pdialog.setCancelable(cancelable);
            pdialog.setMessage(message);
            pdialog.show();
        } catch (Exception e) {
            Log.d("Loading", "show");
        }
    }

    public static void cancel() {
        try {
            if (pdialog != null && pdialog.isShowing()) {
                pdialog.dismiss();
                pdialog.cancel();
            }
        } catch (Exception e) {
            Log.d("Loading", "cancel");
        }
    }

    public static void updateMessage(final String message) {
        Runnable changeMessage = new Runnable() {
            @Override
            public void run() {
                pdialog.setMessage(message);
            }
        };
        changeMessage.run();
    }


}
