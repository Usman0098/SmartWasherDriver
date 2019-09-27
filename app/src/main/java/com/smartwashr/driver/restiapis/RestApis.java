package com.smartwashr.driver.restiapis;

import com.smartwashr.driver.models.DetailResponse;
import com.smartwashr.driver.models.GenericResponse;
import com.smartwashr.driver.models.GetOrders.OrdersResults;
import com.smartwashr.driver.models.categoryResponse.categoryResponse.CategoryResponse;
import com.smartwashr.driver.models.loginresponse.LoginResponse;
import com.smartwashr.driver.models.orderResponse.OrderResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zeeshan on 6/2/17.
 */

public interface RestApis {

//    @FormUrlEncoded
//    @POST("login")
//    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password, @Field("lat") String lat, @Field("lng") String lng, @Field("device_token") String device_token);

//    @GET("price-list")
//    Call<CategoryResponse> category();

    @GET("category")
    Call<CategoryResponse> category(@Header("Device-Type") String decive,
                                    @Query("currency_code") String currencyCode);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password, @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST("password/email")
    Call<GenericResponse> forgetPass(@Field("email") String email);


    @FormUrlEncoded
    @POST("order/update-status")
    Call<GenericResponse> received(@Header("Authorization") String token,
                                   @Field("order_id") String order_id,
                                   @Field("status_id") String status_id);

    @FormUrlEncoded
    @POST("driver-app/order-detail/add")
    Call<GenericResponse> addItem(@Header("Authorization") String token,
                                  @Field("order_id") int order_id,
                                  @Field("product_id") int product_id,
                                  @Field("service_id") int service_id,
                                  @Field("qty") int qty,
                                  @Field("paid_price") String paid_price,
                                  @Field("laundry_price") String paidlaundry_price_price);


    @GET("driver-app/orders")
    Call<OrdersResults> getOrders(@Header("Authorization") String token, @Query("status") String status);


    @FormUrlEncoded
    @POST("driver-app/order-detail/get-form-data")
    Call<DetailResponse> orderDetail(@Header("Authorization") String token, @Field("order_id") String id);

    @GET("driver-app/order/{id}")
    Call<com.smartwashr.driver.models.detailOrder.DetailResponse> getOrder(@Header("Authorization") String auth, @Path("id") String id);


    @FormUrlEncoded
    @POST("driver-app/order/update")
    Call<GenericResponse> updateTime(@Header("Authorization") String token,
                                     @Field("order_id") String order_id,
                                     @Field("pickup_time") String p_time,
                                     @Field("delivery_time") String d_time,
                                     @Field("orderstatus_id") String status);


    @FormUrlEncoded
    @POST("driver-app/order/client-push-message")
    Call<GenericResponse> sendPush(@Header("Authorization") String token,
                                   @Field("order_id") String orderId);




    @FormUrlEncoded
    @POST("driver-app/order/client-invoice-email")
    Call<GenericResponse> sendInvoice(@Header("Authorization") String token,
                                      @Field("order_id") String orderId);

    @FormUrlEncoded
    @POST("driver-app/order-detail/delete")
    Call<OrderResponse> deleteOrder(@Header("Authorization") String token,
                                    @Field("detail_id") String orderId);
}