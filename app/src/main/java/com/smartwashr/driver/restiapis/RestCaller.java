package com.smartwashr.driver.restiapis;

import android.widget.Toast;

import com.smartwashr.driver.SmartWashr;
import com.smartwashr.driver.models.GenericResponse;

import java.lang.annotation.Annotation;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by zeeshan on 6/2/17.
 */

public class RestCaller {


    private int REQ_CODE = 0;
    iResponseHandler handler;

    public RestCaller(iResponseHandler context, Call caller, final int REQUEST_CODE) throws NumberFormatException {
        if (REQUEST_CODE <= 0) {
            NumberFormatException ex = new NumberFormatException();
            throw ex;
        }
        REQ_CODE = REQUEST_CODE;
        handler = context;
        ENQUE(caller);
    }

    private void ENQUE(Call call) {
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() == 200 | response.code() == 201) {
                    handler.onSuccess(call, response, REQ_CODE);
                } else if (response.code() == 403 || response.code() == 422) {
                    Toast.makeText(SmartWashr.getAppContext(), "Server is broken", Toast.LENGTH_SHORT).show();
                    handler.onApiCrash(call, new Throwable(response.raw().message()), REQ_CODE);
                } else {
                    GenericResponse error = null;
                    Converter<ResponseBody, GenericResponse> errorConverter =
                            SmartWashr.getRetrofit().responseBodyConverter(GenericResponse.class, new Annotation[0]);
                    try {
                        error = errorConverter.convert(response.errorBody());
                    } catch (Exception e) {
                    }
                    if (error != null)
                        handler.onFailure(call, error, REQ_CODE);
                    else
                        handler.onApiCrash(call, new Throwable(response.raw().message()), REQ_CODE);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (t instanceof UnknownHostException)
                    handler.onApiCrash(call, new Throwable("Unable to access server. Please check your connection."), REQ_CODE);
                else
                    handler.onApiCrash(call, t, REQ_CODE);
            }
        });
    }

}
