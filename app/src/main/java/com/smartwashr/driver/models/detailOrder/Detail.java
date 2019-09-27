package com.smartwashr.driver.models.detailOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("added_by")
    @Expose
    private Integer addedBy;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("service_id")
    @Expose
    private Integer serviceId;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("paid_price")
    @Expose
    private Object paidPrice;
    @SerializedName("total_paid_price")
    @Expose
    private Object totalPaidPrice;
    @SerializedName("total_paid_laundry_price")
    @Expose
    private Object totalPaidLaundryPrice;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("service")
    @Expose
    private Service service;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Integer addedBy) {
        this.addedBy = addedBy;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Object getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(String paidPrice) {
        this.paidPrice = paidPrice;
    }

    public Object getTotalPaidPrice() {
        return totalPaidPrice;
    }

    public void setTotalPaidPrice(String totalPaidPrice) {
        this.totalPaidPrice = totalPaidPrice;
    }

    public Object getTotalPaidLaundryPrice() {
        return totalPaidLaundryPrice;
    }

    public void setTotalPaidLaundryPrice(String totalPaidLaundryPrice) {
        this.totalPaidLaundryPrice = totalPaidLaundryPrice;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
