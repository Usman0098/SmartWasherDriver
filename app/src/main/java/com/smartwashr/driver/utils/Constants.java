package com.smartwashr.driver.utils;

import com.smartwashr.driver.models.GetOrders.Order;

/**
 * Created by zeeshan on 5/31/17.
 */

public class Constants {

    public static final String ACCESS_TOKEN = "access_token";
    public static final String ACTIVE_ORDER = "active_order";
    public static final String USER_ID = "user_id";
    public static String LAT = "lat";
    public static String LNG = "lng";
    public static String CURRENCY_app = "currency";
//    public static String CURRENCY = SessionManager.get(CURRENCY_app);
    public static String CURRENCY = "";
    public static String UPADATE_LIST = "";


    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    //    public static final String SERVER_IP = "https://www.smartwashr.com/api/v1/";
    //public static final String SERVER_IP = "http://45.63.83.178/api/v1/";
    //amazon testing server
    public static final String SERVER_IP = "http://3.15.176.216/api/v1/";
    public static final String DEV_IP = "http://development.smartwashr.com/api/v1/";
    public static final String DEVICE_TOKEN = "device_token";


    public static String DBName = "smartwashr";
    public static String ORDERDB = "orders";
    public static int DBVersion = 1;

    public static final String BADGECOUNT = "badgeNo";

    public static Order order = new Order();
}
