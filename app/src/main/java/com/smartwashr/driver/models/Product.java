
package com.smartwashr.driver.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_ar")
    @Expose
    private String nameAr;
    @SerializedName("dryclean_price")
    @Expose
    private String drycleanPrice;
    @SerializedName("washing_price")
    @Expose
    private String washingPrice;
    @SerializedName("press")
    @Expose
    private String press;
    @SerializedName("sw_dryclean_price")
    @Expose
    private String swDrycleanPrice;
    @SerializedName("sw_washing_price")
    @Expose
    private String swWashingPrice;
    @SerializedName("sw_press")
    @Expose
    private String swPress;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getDrycleanPrice() {
        return drycleanPrice;
    }

    public void setDrycleanPrice(String drycleanPrice) {
        this.drycleanPrice = drycleanPrice;
    }

    public String getWashingPrice() {
        return washingPrice;
    }

    public void setWashingPrice(String washingPrice) {
        this.washingPrice = washingPrice;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getSwDrycleanPrice() {
        return swDrycleanPrice;
    }

    public void setSwDrycleanPrice(String swDrycleanPrice) {
        this.swDrycleanPrice = swDrycleanPrice;
    }

    public String getSwWashingPrice() {
        return swWashingPrice;
    }

    public void setSwWashingPrice(String swWashingPrice) {
        this.swWashingPrice = swWashingPrice;
    }

    public String getSwPress() {
        return swPress;
    }

    public void setSwPress(String swPress) {
        this.swPress = swPress;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
