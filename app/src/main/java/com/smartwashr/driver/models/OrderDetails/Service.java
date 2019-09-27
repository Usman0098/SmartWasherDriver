
package com.smartwashr.driver.models.OrderDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("service_id")
    @Expose
    private Integer serviceId;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("discount_price")
    @Expose
    private Integer discountPrice;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("laundry_price")
    @Expose
    private Integer laundryPrice;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLaundryPrice() {
        return laundryPrice;
    }

    public void setLaundryPrice(Integer laundryPrice) {
        this.laundryPrice = laundryPrice;
    }

}
