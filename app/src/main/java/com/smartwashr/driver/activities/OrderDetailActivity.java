package com.smartwashr.driver.activities;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.smartwashr.driver.DetailAdapter;
import com.smartwashr.driver.R;
import com.smartwashr.driver.SmartWashr;
import com.smartwashr.driver.models.GenericResponse;
import com.smartwashr.driver.models.detailOrder.Detail;
import com.smartwashr.driver.models.detailOrder.DetailResponse;
import com.smartwashr.driver.restiapis.RestCaller;
import com.smartwashr.driver.restiapis.iResponseHandler;
import com.smartwashr.driver.utils.Constants;
import com.smartwashr.driver.utils.Internet;
import com.smartwashr.driver.utils.Loading;
import com.smartwashr.driver.utils.SessionManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zeeshan on 6/25/17.
 */

public class OrderDetailActivity extends AppCompatActivity implements iResponseHandler {

    private ArrayList<Detail> products = new ArrayList<>();
    private DetailAdapter adapter;
    private RecyclerView recyclerView;
    private Button btn, rec;
    private TextView sub_total, total, order_num, call;
    private String phone, lat, lng = "";
    private SessionManager sessionManager;
    private String val = "3";
    private ImageButton ib_add;
    private Button btn_time, send_push, send_invoice;
    private Button showDetail;
    private LinearLayout user_detail, order_detail;
    private int value = 0;
    private ImageView close;
    private TextView name, contact, address, payment_type, user_comments, del_price, txt_time, pdtime;
    double newInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);

        sessionManager = new SessionManager(OrderDetailActivity.this);
        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        sub_total = (TextView) findViewById(R.id.total_price);
        total = (TextView) findViewById(R.id.final_total_price);
        order_num = (TextView) findViewById(R.id.order_num);
        btn = (Button) findViewById(R.id.map_btn);
        del_price = findViewById(R.id.del_price);
        rec = (Button) findViewById(R.id.received);
        ib_add = (ImageButton) findViewById(R.id.ib_add);
        call = (TextView) findViewById(R.id.phone);
        btn_time = (Button) findViewById(R.id.update_time);
        send_push = (Button) findViewById(R.id.send_push);
        send_invoice = (Button) findViewById(R.id.send_invoice);
        showDetail = (Button) findViewById(R.id.show_user);
        order_detail = (LinearLayout) findViewById(R.id.order_detail);
        user_detail = (LinearLayout) findViewById(R.id.detail);
        close = (ImageView) findViewById(R.id.close);
        pdtime = findViewById(R.id.pdtime);

        name = (TextView) findViewById(R.id.name);
        contact = (TextView) findViewById(R.id.contact);
        address = (TextView) findViewById(R.id.address);
        payment_type = findViewById(R.id.payment_type);
        user_comments = findViewById(R.id.user_comments);

        recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));

        Constants.order.setOrderstatusId(Integer.parseInt(val));

        if (Constants.order.getUser().getFullName() != null)
        {
            name.setText(Constants.order.getUser().getFullName());
        }
       if (Constants.order.getUser().getPhone()!=null)
       {
           contact.setText(Constants.order.getUser().getPhone());
       }
        if (Constants.order.getAddress() != null)
        {
            address.setText(Constants.order.getAddress());
        }
        if (Constants.order.getPaymentMethod() != null)
        {
            payment_type.setText(Constants.order.getPaymentMethod());
        }
        if (Constants.order.getUserComments() == null )
        {
            user_comments.setText("No Comments");
            Log.e("Comment", Constants.order.getUserComments()+"");
        }else
        {
            user_comments.setText(Constants.order.getUserComments()+"");
            Log.e("Comment", Constants.order.getUserComments()+"");
        }
        if (Constants.order.getId() != null) {

            order_num.setText("Order# " + Constants.order.getId());
        }
//        name.setText("TestName");
//        contact.setText("73445378");
//        address.setText("Lahore,Pakistan");
//        payment_type.setText("Easy paisa");
//        user_comments.setText("Wash it carefully"+"");



        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderDetailActivity.this, TimeUpdateActivity.class);
                startActivity(i);
            }
        });

        send_invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loading.show(OrderDetailActivity.this, true, "Please wait...");
                new RestCaller(OrderDetailActivity.this, SmartWashr.getRestClient().sendInvoice(sessionManager.get(Constants.ACCESS_TOKEN),
                        Constants.order.getId() + ""), 4);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LAT_LNG", lat + " , " + lng);
                String url = "http://www.google.com/maps/search/?api=1&query=" + lat + "," + lng;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                browserIntent.setPackage("com.google.android.apps.maps");
                if (browserIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(browserIntent);
                } else {
                    Toast.makeText(OrderDetailActivity.this, "Please install google maps", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 1;
                showUser(value);

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 0;
                showUser(value);

            }
        });


        send_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Internet.isAvailable(OrderDetailActivity.this)) {
                    Loading.show(OrderDetailActivity.this, false, "Please wait...");
                    new RestCaller(OrderDetailActivity.this, SmartWashr.getRestClient().sendPush(sessionManager.get(Constants.ACCESS_TOKEN),
                            Constants.order.getId() + ""), 3);
                }
            }
        });

        ib_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailActivity.this, NewAddItem.class);
                intent.putExtra("order_id", Constants.order.getId());
                startActivity(intent);
            }
        });

        rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Internet.isAvailable(OrderDetailActivity.this)) {
                    Loading.show(OrderDetailActivity.this, false, "Please wait...");
                    new RestCaller(OrderDetailActivity.this, SmartWashr.getRestClient().updateTime(
                            sessionManager.get(Constants.ACCESS_TOKEN),
                            Constants.order.getId() + "",
                            Constants.order.getPickupTime(),
                            Constants.order.getDeliveryTime(),
                            Constants.order.getOrderstatusId() + ""), 1);
                }
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCallBtnClick();
            }
        });

        adapter = new DetailAdapter(OrderDetailActivity.this, products, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void showUser(int i) {
        if (i == 1) {
            order_detail.setVisibility(View.GONE);
            user_detail.setVisibility(View.VISIBLE);
        } else {
            order_detail.setVisibility(View.VISIBLE);
            user_detail.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(OrderDetailActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getOrderDetail(Constants.order.getId());


    }

    public void getOrderDetail(Integer orderId) {
        Loading.show(this, true, "Please wait...");
        new RestCaller(OrderDetailActivity.this, SmartWashr.getRestClient().getOrder(new SessionManager(this).get(Constants.ACCESS_TOKEN), orderId + ""), 2);
    }

    private void onCallBtnClick() {
        if (Build.VERSION.SDK_INT < 23) {
            phoneCall();
        } else {

            if (ActivityCompat.checkSelfPermission(OrderDetailActivity.this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                phoneCall();
            } else {
                final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                //Asking request Permissions
                ActivityCompat.requestPermissions(OrderDetailActivity.this, PERMISSIONS_STORAGE, 9);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch (requestCode) {
            case 9:
                permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (permissionGranted) {
            phoneCall();
        } else {
            Toast.makeText(OrderDetailActivity.this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    private void phoneCall() {
        if (ActivityCompat.checkSelfPermission(OrderDetailActivity.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" +
                    phone));
            startActivity(callIntent);
        } else {
            Toast.makeText(OrderDetailActivity.this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSuccess(Call call, Response response, int reqCode) {
        Loading.cancel();
        if (reqCode == 1) {
            rec.setClickable(false);
            Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show();

        } else if (reqCode == 2) {
            products.clear();
            DetailResponse detailResponse = (DetailResponse) response.body();

            for (int i = 0; i < detailResponse.getDetail().size(); i++) {
//                Detail product = Constants.order.getDetail().get(i);
                if (detailResponse.getDetail().get(i).getProduct() != null) {
                    Detail product = detailResponse.getDetail().get(i);
                    products.add(product);
                }
            }

            String tot = detailResponse.getTotal() + ""/*getIntent().getStringExtra("total")*/;
            String status = detailResponse.getStatus().getId() + ""/*getIntent().getStringExtra("status")*/;
            final String order_id = detailResponse.getId() + "" /*getIntent().getStringExtra("order_id")*/;
            lat = detailResponse.getLatLng().getCoordinates().get(1) + "";/*getIntent().getStringExtra("lat");*/
            lng = detailResponse.getLatLng().getCoordinates().get(0) + "";/*getIntent().getStringExtra("lng");*/
            phone = Constants.order.getUser().getPhone()/*getIntent().getStringExtra("phone")*/;

//            phone = "743857349875348"/*getIntent().getStringExtra("phone")*/;

            double total_sub= Double.parseDouble(detailResponse.getSubtotal().toString());
            reduceDouble(total_sub);
            Log.e("Sub_total",detailResponse.getSubtotal().toString());
            Log.e("Sub_total2", newInput+"");




            sub_total.setText(Constants.CURRENCY+" " + newInput+"");

            Log.e("Delivery Charges",detailResponse.getDeliveryCharges().toString());
            del_price.setText(Constants.CURRENCY+ " " + detailResponse.getDeliveryCharges());

            Log.e("Total",detailResponse.getTotal().toString());
            total.setText(Constants.CURRENCY+ " " + tot);

            Constants.order.setPickupTime(detailResponse.getPickupTime());
            Constants.order.setDeliveryTime(detailResponse.getDeliveryTime());

            pdtime.setText(detailResponse.getPickupTime());

            if (!status.equalsIgnoreCase("2")) {
                rec.setText("Delivered");
                val = "6";
                Constants.order.setOrderstatusId(Integer.parseInt(val));
            } else if (detailResponse.getOrderstatusId() == 3) {
                rec.setText("Getting Washed");
                val = "4";

                Constants.order.setOrderstatusId(Integer.parseInt(val));
            }
            adapter.notifyDataSetChanged();

        }
        if (reqCode == 3) {
            Toast.makeText(this, "Notified", Toast.LENGTH_SHORT).show();
        } else if (reqCode == 4) {
            Toast.makeText(this, "Email Sent", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(Call call, GenericResponse error, int reqCode) {
        Loading.cancel();
        Toast.makeText(this, error.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onApiCrash(Call call, Throwable t, int reqCode) {
        Loading.cancel();
        t.printStackTrace();
    }
    private Double reduceDouble(double value) {
        double val = 0.0;
        BigDecimal bd = new BigDecimal(value).setScale(4, RoundingMode.HALF_UP);
         newInput = bd.doubleValue();

        return newInput;
    }


}
