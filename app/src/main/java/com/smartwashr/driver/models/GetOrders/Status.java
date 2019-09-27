
package com.smartwashr.driver.models.GetOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status_description")
    @Expose
    private String statusDescription;
    @SerializedName("status_description_ar")
    @Expose
    private String statusDescriptionAr;
    @SerializedName("status_push_message")
    @Expose
    private String statusPushMessage;
    @SerializedName("status_push_message_ar")
    @Expose
    private Object statusPushMessageAr;
    @SerializedName("status_client_email")
    @Expose
    private String statusClientEmail;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

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

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getStatusDescriptionAr() {
        return statusDescriptionAr;
    }

    public void setStatusDescriptionAr(String statusDescriptionAr) {
        this.statusDescriptionAr = statusDescriptionAr;
    }

    public String getStatusPushMessage() {
        return statusPushMessage;
    }

    public void setStatusPushMessage(String statusPushMessage) {
        this.statusPushMessage = statusPushMessage;
    }

    public Object getStatusPushMessageAr() {
        return statusPushMessageAr;
    }

    public void setStatusPushMessageAr(Object statusPushMessageAr) {
        this.statusPushMessageAr = statusPushMessageAr;
    }

    public String getStatusClientEmail() {
        return statusClientEmail;
    }

    public void setStatusClientEmail(String statusClientEmail) {
        this.statusClientEmail = statusClientEmail;
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

}
