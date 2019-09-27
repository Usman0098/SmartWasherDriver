package com.smartwashr.driver.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotiModel {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("object")
    @Expose
    private Notigication object;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Notigication getObject() {
        return object;
    }

    public void setObject(Notigication object) {
        this.object = object;
    }
}
