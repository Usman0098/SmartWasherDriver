package com.smartwashr.driver.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.smartwashr.driver.R;
import com.smartwashr.driver.SmartWashr;
import com.smartwashr.driver.models.GenericResponse;
import com.smartwashr.driver.models.loginresponse.LoginResponse;
import com.smartwashr.driver.restiapis.RestCaller;
import com.smartwashr.driver.restiapis.iResponseHandler;
import com.smartwashr.driver.utils.Constants;
import com.smartwashr.driver.utils.FontUtils;
import com.smartwashr.driver.utils.Internet;
import com.smartwashr.driver.utils.Loading;
import com.smartwashr.driver.utils.RippleEffect;
import com.smartwashr.driver.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zeeshan on 5/31/17.
 */

public class Login extends AppCompatActivity implements iResponseHandler {

    private Button sign_in;
    private ImageView back_btn;
    private TextView forgot_password;
    private SessionManager sessionManager;
    private EditText email, password;
    private View parent;
    private FirebaseAnalytics mFirebaseAnalytics;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sign_in);

        sessionManager = new SessionManager(Login.this);
        parent = findViewById(R.id.parent);
        sign_in = (Button) findViewById(R.id.sign_in);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

//                email.setText("info@smartwashr.com");
//                password.setText("Smartwashr2018");

//        email.setText("developer@driver.com");
//        password.setText("driver123");
//        email.setText("driver@bahraintest.com");
//        password.setText("driver123");


        FontUtils.setFont(email);
        FontUtils.setFont(password);
        FontUtils.setFont(forgot_password);
        FontUtils.setFont(sign_in);


        Log.e("LAT", Constants.LAT);
        Log.e("LNG", Constants.LNG);

        //   FCM token
      final String token=  FirebaseInstanceId.getInstance().getToken();
        sessionManager.put(Constants.DEVICE_TOKEN, token);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.getAppInstanceId();
        sessionManager = new SessionManager(Login.this);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().length() != 0 && password.getText().length() != 0) {
                    if (isValidEmail(email.getText().toString())) {
                        if (Internet.isAvailable(Login.this)) {
                            if (!Constants.LAT.isEmpty() || !Constants.LNG.isEmpty()) {

//                                new RestCaller(Login.this, SmartWashr.getRestClient().login(email.getText().toString(), password.getText().toString(), Constants.LNG, Constants.LAT, sessionManager.get(Constants.DEVICE_TOKEN)), 1);
//                                new RestCaller(Login.this, SmartWashr.getRestClient().login(email.getText().toString(), password.getText().toString(),  sessionManager.get(Constants.DEVICE_TOKEN)), 1);
                                new RestCaller(Login.this, SmartWashr.getRestClient().login(email.getText().toString(), password.getText().toString(),  token), 1);
                                Log.e("Device Token", sessionManager.get(Constants.DEVICE_TOKEN));
                                Loading.show(Login.this, false, "Please wait...");

                            }

                        } else {
                            Snackbar.make(parent, "Check your Internet Connection", Snackbar.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Snackbar.make(parent, "Please fill all fields", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, ForgotPassword.class);
                startActivity(i);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        RippleEffect.applyRippleEffect(sign_in, "#bdbdbd");
        RippleEffect.applyRippleEffect(back_btn, "#bdbdbd");
        RippleEffect.applyRippleEffect(forgot_password, "#bdbdbd");
        RippleEffect.applyRippleEffect(email, "#bdbdbd");
        RippleEffect.applyRippleEffect(password, "#bdbdbd");
    }

    public boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    public void onSuccess(Call call, Response response, int reqCode) {

        try {
            LoginResponse loginResponse = (LoginResponse) response.body();
            Log.d("response", new Gson().toJson(loginResponse));
            Log.d("response user", new Gson().toJson(loginResponse.getUser()));


//            Toast.makeText(Login.this,loginResponse.getCurrency(), Toast.LENGTH_LONG).show();
            Constants.CURRENCY=loginResponse.getCurrency();

            sessionManager.putAccessToken("Bearer " + loginResponse.getToken());

//            sessionManager.putCurrency(loginResponse.getCurrency());
//                        sessionManager.putCurrency("BHD");


            Loading.cancel();
            Intent i = new Intent(Login.this, HomeActivity.class);
            startActivity(i);
            Login.this.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call call, GenericResponse error, int reqCode) {
        Log.d("Response", error.getError());
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
        builder1.setMessage("Email or Password is incorrect!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
//        Snackbar.make(parent, error.getError(), Snackbar.LENGTH_LONG).show();
        Loading.cancel();
    }

    @Override
    public void onApiCrash(Call call, Throwable t, int reqCode) {
        Loading.cancel();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
        builder1.setMessage("Email or Password is incorrect!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
        Log.e("Error on Api Crashed", t.getMessage());
    }

}
