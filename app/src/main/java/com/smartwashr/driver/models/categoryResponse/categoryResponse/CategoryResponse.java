package com.smartwashr.driver.models.categoryResponse.categoryResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("data")
    @Expose
    private List<com.smartwashr.driver.models.categoryResponse.categoryResponse.Datum> data = null;

    public List<com.smartwashr.driver.models.categoryResponse.categoryResponse.Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
