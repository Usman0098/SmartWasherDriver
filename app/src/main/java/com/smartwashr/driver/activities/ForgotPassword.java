package com.smartwashr.driver.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.smartwashr.driver.R;
import com.smartwashr.driver.SmartWashr;
import com.smartwashr.driver.models.GenericResponse;
import com.smartwashr.driver.restiapis.RestCaller;
import com.smartwashr.driver.restiapis.iResponseHandler;
import com.smartwashr.driver.utils.Internet;
import com.smartwashr.driver.utils.Loading;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zeeshan on 5/31/17.
 */

public class ForgotPassword extends AppCompatActivity implements iResponseHandler {

    private EditText email;
    private Button submit;
    private ImageView back_btn;
    private AlertDialog alertDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.forget_pass);

        email = (EditText) findViewById(R.id.editText);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().length() > 0) {
                    if (Internet.isAvailable(ForgotPassword.this)) {
                        new RestCaller(ForgotPassword.this, SmartWashr.getRestClient().forgetPass(email.getText().toString()), 1);
                        Loading.show(ForgotPassword.this, false, "Please wait...");
                    } else {
                        Toast.makeText(ForgotPassword.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ForgotPassword.this, "Please enter you email address", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

    }

    @Override
    public void onSuccess(Call call, Response response, int reqCode) {
        Loading.cancel();
        GenericResponse success = (GenericResponse) response.body();
        String msg = "";
        String title = "";
        if (success.getError() != null) {
            msg = success.getError().toString();
            title = "Failure";
        } else {
            msg = success.getMsg().toString();
            title = "Success";
        }
        alertDialog = new AlertDialog.Builder(
                ForgotPassword.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onFailure(Call call, GenericResponse error, int reqCode) {
        Loading.cancel();
        alertDialog = new AlertDialog.Builder(
                ForgotPassword.this).create();
        alertDialog.setTitle("Failure");
        alertDialog.setMessage("Something went wrong, please try again in a while");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onApiCrash(Call call, Throwable t, int reqCode) {
        Loading.cancel();
    }
}
