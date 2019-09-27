package com.smartwashr.driver.restiapis;

import com.smartwashr.driver.models.GenericResponse;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zeeshan on 6/2/17.
 */

public interface iResponseHandler {
    void onSuccess(Call call, Response response, int reqCode);

    void onFailure(Call call, GenericResponse error, int reqCode);

    void onApiCrash(Call call, Throwable t, int reqCode);
}