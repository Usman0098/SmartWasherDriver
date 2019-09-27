package com.smartwashr.driver.models.detailOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("status_description")
    @Expose
    private String statusDescription;
    @SerializedName("status_description_ar")
    @Expose
    private String statusDescriptionAr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
