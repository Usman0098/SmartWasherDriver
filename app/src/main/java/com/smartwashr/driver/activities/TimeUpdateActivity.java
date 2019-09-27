package com.smartwashr.driver.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartwashr.driver.R;
import com.smartwashr.driver.SmartWashr;
import com.smartwashr.driver.models.GenericResponse;
import com.smartwashr.driver.restiapis.RestCaller;
import com.smartwashr.driver.restiapis.iResponseHandler;
import com.smartwashr.driver.utils.Constants;
import com.smartwashr.driver.utils.Loading;
import com.smartwashr.driver.utils.SessionManager;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Response;

public class TimeUpdateActivity extends AppCompatActivity implements iResponseHandler,
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {

    ImageView iv_back;
    TextView tv_p_time, tv_d_time;
    Button btn_update;
    String p_time, d_time;
    private int t;
    private String pick_date, del_date;
    private int selected_date;
    private String pick_time;
    private int selected_time;
    private boolean isPicked;
    private String del_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_update);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_p_time = (TextView) findViewById(R.id.tv_p_time);
        tv_d_time = (TextView) findViewById(R.id.tv_d_time);
        btn_update = (Button) findViewById(R.id.btn_update);

        p_time = Constants.order.getPickupTime();
        d_time = Constants.order.getDeliveryTime();

        tv_p_time.setText(getFormattedTime(p_time));
        tv_d_time.setText(getFormattedTime(d_time));

        tv_d_time.setEnabled(false);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.order.setPickupTime(p_time);
                Constants.order.setDeliveryTime(d_time);
                Loading.show(TimeUpdateActivity.this, false, "Please wait...");
                new RestCaller(TimeUpdateActivity.this, SmartWashr.getRestClient().updateTime(new SessionManager(TimeUpdateActivity.this).get(Constants.ACCESS_TOKEN),
                        Constants.order.getId() + "",
                        p_time,
                        d_time,
                        Constants.order.getOrderstatusId() + ""), 1);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_p_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t = 1;
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        TimeUpdateActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DATE)
                );
                dpd.setMinDate(new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE)));
                dpd.setTitle(getResources().getString(R.string.time_date_msg));
                dpd.show(getFragmentManager(), "Datepickerdialog");

            }
        });

        tv_d_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t = 2;
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        TimeUpdateActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        selected_date
                );
                dpd.setMinDate(new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), selected_date + 1));
                dpd.setTitle(getResources().getString(R.string.time_date_msg));
                dpd.show(getFragmentManager(), "Datepickerdialog");

            }
        });

    }

    public static String getFormattedTime(String time) {
        String displayValue = time;
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = dateFormatter.parse(time);
// Get time from date
            SimpleDateFormat timeFormatter = new SimpleDateFormat("dd-MM-yyy, hh:mm a");
            displayValue = timeFormatter.format(date);
            return displayValue;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return displayValue;
    }

    @Override
    public void onSuccess(Call call, Response response, int reqCode) {
        Loading.cancel();
        Toast.makeText(this, "Order updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailure(Call call, GenericResponse error, int reqCode) {
        Loading.cancel();
    }

    @Override
    public void onApiCrash(Call call, Throwable t, int reqCode) {
        Loading.cancel();
    }


    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String hour_, minute_, sec_;
        if (t == 1) {
            hour_ = hourOfDay + "";
            minute_ = minute + "";
            sec_ = second + "";
            if (hour_.length() == 1) {
                hour_ = "0" + hourOfDay;
            } else {
                hour_ = hourOfDay + "";
            }
            if (minute_.length() == 1) {
                minute_ = "0" + minute;
            } else {
                minute_ = minute + "";
            }
            if (sec_.length() == 1) {
                sec_ = "0" + second;
            } else {
                sec_ = second + "";
            }

            pick_time = hour_ + ":" + minute_ + ":" + sec_;

            tv_p_time.setText(getFormattedTime(pick_date + " " + pick_time));
            p_time = pick_date + " " + pick_time;
            tv_d_time.setEnabled(true);


        } else {
            hour_ = hourOfDay + "";
            minute_ = minute + "";
            sec_ = second + "";

            if (hour_.length() == 1) {
                hour_ = "0" + hourOfDay;
            } else {
                hour_ = hourOfDay + "";
            }
            if (minute_.length() == 1) {
                minute_ = "0" + minute;
            } else {
                minute_ = minute + "";
            }
            if (sec_.length() == 1) {
                sec_ = "0" + second;
            } else {
                sec_ = second + "";
            }


            del_time = hour_ + ":" + minute_ + ":" + sec_;

            tv_d_time.setText(getFormattedTime(del_date + " " + del_time));
            d_time = del_date+" "+del_time;
        }

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String day, month;

        if (t == 1) {
            day = dayOfMonth + "";
            month = (monthOfYear + 1) + "";

            if (day.length() == 1) {
                day = "0" + day;
            } else {
                day = dayOfMonth + "";
            }
            if (month.length() == 1) {
                month = "0" + (monthOfYear + 1);
            } else {
                month = monthOfYear + "";
            }

            pick_date = year + "-" + month + "-" + day;
            selected_date = dayOfMonth;

            Calendar now = Calendar.getInstance();
            now.add(Calendar.MINUTE, 60);
            TimePickerDialog dpd = TimePickerDialog.newInstance(
                    TimeUpdateActivity.this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true
            );

//        dpd.setMinTime(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), 0);
            dpd.setMinTime(
                    13, 0, 0);
            dpd.setMaxTime(22, 0, 0);
            dpd.setTitle(getResources().getString(R.string.time_date_msg));
            dpd.show(getFragmentManager(), "Datepickerdialog");

        } else {
            day = dayOfMonth + "";
            month = (monthOfYear + 1) + "";

            if (day.length() == 1) {
                day = "0" + day;
            } else {
                day = dayOfMonth + "";
            }
            if (month.length() == 1) {
                month = "0" + (monthOfYear + 1);
            } else {
                month = monthOfYear + "";
            }

            del_date = year + "-" + month + "-" + day;

            Calendar now = Calendar.getInstance();
            now.add(Calendar.MINUTE, 60);
            TimePickerDialog dpd = TimePickerDialog.newInstance(
                    TimeUpdateActivity.this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true
            );

//        dpd.setMinTime(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), 0);
            dpd.setMinTime(
                    13, 0, 0);
            dpd.setMaxTime(22, 0, 0);
            dpd.setTitle(getResources().getString(R.string.time_date_msg));
            dpd.show(getFragmentManager(), "Datepickerdialog");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        TimePickerDialog tpd = (TimePickerDialog) getFragmentManager().findFragmentByTag("TimepickerDialog");
        if (tpd != null) tpd.setOnTimeSetListener(this);
        if (dpd != null) dpd.setOnDateSetListener(this);
    }

}
