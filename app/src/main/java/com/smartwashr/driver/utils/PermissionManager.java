package com.smartwashr.driver.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by zeeshan on 6/6/17.
 */

public class  PermissionManager {

    private static PermissionManager object;
    private static Activity context;


    private PermissionManager() {

    }

    public static PermissionManager getInstance(Activity acContext) {
        context = acContext;
        if (object == null) {
            return new PermissionManager();
        } else {
            return object;
        }
    }


    public boolean hasPermission(Activity Currentcontext, String Permission) {
        // Assume thisActivity is the current activity
        int permissionCheck = ContextCompat.checkSelfPermission(Currentcontext,
                Permission);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public void getPermissionifNotAvailble(String[] PERMISSION, int REQ_CODE) {
        // Assume thisActivity is the current activity

//        int permissionCheck = ContextCompat.checkSelfPermission(context,
//                PERMISSION);
//        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//
//        } else {
        ActivityCompat.requestPermissions(context,
                PERMISSION,
                REQ_CODE);

        //}
    }

    public void getPermissionifNotAvailble(String PERMISSION, int REQ_CODE) {
        int permissionCheck = ContextCompat.checkSelfPermission(context,
                PERMISSION);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(context,
                    new String[]{PERMISSION},
                    REQ_CODE);

        }
    }


}
