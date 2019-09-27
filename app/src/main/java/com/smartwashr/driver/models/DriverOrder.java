package com.smartwashr.driver.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zeeshan on 6/25/17.
 */

public class DriverOrder {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("laundry_id")
    @Expose
    private String laundryId;
    @SerializedName("is_read_admin")
    @Expose
    private String isReadAdmin;
    @SerializedName("is_read_laundry")
    @Expose
    private String isReadLaundry;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_email")
    @Expose
    private Object userEmail;
    @SerializedName("payment_method")
    @Expose
    private Object paymentMethod;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("sw_total_price")
    @Expose
    private String swTotalPrice;
    @SerializedName("delivery_charges")
    @Expose
    private String deliveryCharges;
    @SerializedName("commission")
    @Expose
    private String commission;
    @SerializedName("discount")
    @Expose
    private Object discount;
    @SerializedName("discount_type")
    @Expose
    private Object discountType;
    @SerializedName("post_code")
    @Expose
    private String postCode;
    @SerializedName("address_one")
    @Expose
    private String addressOne;
    @SerializedName("address_two")
    @Expose
    private String addressTwo;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("delivery_instruction")
    @Expose
    private String deliveryInstruction;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("collection_date_time")
    @Expose
    private String collectionDateTime;
    @SerializedName("delivery_date_time")
    @Expose
    private String deliveryDateTime;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLaundryId() {
        return laundryId;
    }

    public void setLaundryId(String laundryId) {
        this.laundryId = laundryId;
    }

    public String getIsReadAdmin() {
        return isReadAdmin;
    }

    public void setIsReadAdmin(String isReadAdmin) {
        this.isReadAdmin = isReadAdmin;
    }

    public String getIsReadLaundry() {
        return isReadLaundry;
    }

    public void setIsReadLaundry(String isReadLaundry) {
        this.isReadLaundry = isReadLaundry;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Object getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(Object userEmail) {
        this.userEmail = userEmail;
    }

    public Object getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Object paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSwTotalPrice() {
        return swTotalPrice;
    }

    public void setSwTotalPrice(String swTotalPrice) {
        this.swTotalPrice = swTotalPrice;
    }

    public String getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(String deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public Object getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Object discountType) {
        this.discountType = discountType;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDeliveryInstruction() {
        return deliveryInstruction;
    }

    public void setDeliveryInstruction(String deliveryInstruction) {
        this.deliveryInstruction = deliveryInstruction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCollectionDateTime() {
        return collectionDateTime;
    }

    public void setCollectionDateTime(String collectionDateTime) {
        this.collectionDateTime = collectionDateTime;
    }

    public String getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(String deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }
}
