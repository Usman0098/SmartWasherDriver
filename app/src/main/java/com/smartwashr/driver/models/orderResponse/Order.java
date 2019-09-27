package com.smartwashr.driver.models.orderResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("invoice_num")
    @Expose
    private String invoiceNum;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("driver_id")
    @Expose
    private Integer driverId;
    @SerializedName("skip_by_client")
    @Expose
    private String skipByClient;
    @SerializedName("orderstatus_id")
    @Expose
    private Integer orderstatusId;
    @SerializedName("user_comments")
    @Expose
    private Object userComments;
    @SerializedName("is_read_laundry")
    @Expose
    private String isReadLaundry;
    @SerializedName("is_read_admin")
    @Expose
    private String isReadAdmin;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("coupon_id")
    @Expose
    private Object couponId;
    @SerializedName("discount")
    @Expose
    private Double discount;
    @SerializedName("discount_type")
    @Expose
    private Object discountType;
    @SerializedName("subtotal")
    @Expose
    private Double subtotal;
    @SerializedName("delivery_charges")
    @Expose
    private Double deliveryCharges;
    @SerializedName("sorting_fee")
    @Expose
    private Integer sortingFee;
    @SerializedName("laundry_total_price")
    @Expose
    private Double laundryTotalPrice;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("paid")
    @Expose
    private Double paid;
    @SerializedName("pickup_time")
    @Expose
    private String pickupTime;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("lat_lng")
    @Expose
    private LatLng latLng;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("detail")
    @Expose
    private List<Object> detail = null;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("driver")
    @Expose
    private Driver driver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getSkipByClient() {
        return skipByClient;
    }

    public void setSkipByClient(String skipByClient) {
        this.skipByClient = skipByClient;
    }

    public Integer getOrderstatusId() {
        return orderstatusId;
    }

    public void setOrderstatusId(Integer orderstatusId) {
        this.orderstatusId = orderstatusId;
    }

    public Object getUserComments() {
        return userComments;
    }

    public void setUserComments(Object userComments) {
        this.userComments = userComments;
    }

    public String getIsReadLaundry() {
        return isReadLaundry;
    }

    public void setIsReadLaundry(String isReadLaundry) {
        this.isReadLaundry = isReadLaundry;
    }

    public String getIsReadAdmin() {
        return isReadAdmin;
    }

    public void setIsReadAdmin(String isReadAdmin) {
        this.isReadAdmin = isReadAdmin;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Object getCouponId() {
        return couponId;
    }

    public void setCouponId(Object couponId) {
        this.couponId = couponId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Object getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Object discountType) {
        this.discountType = discountType;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(Double deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public Integer getSortingFee() {
        return sortingFee;
    }

    public void setSortingFee(Integer sortingFee) {
        this.sortingFee = sortingFee;
    }

    public Double getLaundryTotalPrice() {
        return laundryTotalPrice;
    }

    public void setLaundryTotalPrice(Double laundryTotalPrice) {
        this.laundryTotalPrice = laundryTotalPrice;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
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

    public List<Object> getDetail() {
        return detail;
    }

    public void setDetail(List<Object> detail) {
        this.detail = detail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
