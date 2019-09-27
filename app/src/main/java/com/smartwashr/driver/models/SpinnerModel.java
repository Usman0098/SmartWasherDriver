package com.smartwashr.driver.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smartwashr.driver.models.GetOrders.Order;

import java.util.List;

/**
 * Created by zeeshan on 6/25/17.
 */

public class SpinnerModel {

    @SerializedName("orders")
    @Expose
    private List<Order> orders = null;

    public List<Order> getDriverOrder() {
        return orders;
    }

    public void setDriverOrder(List<Order> orders) {
        this.orders = orders;
    }

}