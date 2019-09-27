
package com.smartwashr.driver.models.loginresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smartwashr.driver.models.GetOrders.User;


public class LoginResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("active_orders")
    @Expose
    private Integer activeorders;
    @SerializedName("currency_code")
    @Expose
    private String currency;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getActiveorders() {
        return activeorders;
    }

    public void setActiveorders(Integer activeorders) {
        this.activeorders = activeorders;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
