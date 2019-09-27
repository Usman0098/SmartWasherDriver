package com.smartwashr.driver;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.smartwashr.driver.activities.OrderDetailActivity;
import com.smartwashr.driver.models.DetailResponse;
import com.smartwashr.driver.models.GenericResponse;
import com.smartwashr.driver.models.GetOrders.Order;
import com.smartwashr.driver.models.OrderDetail;
import com.smartwashr.driver.models.Orders;
import com.smartwashr.driver.restiapis.iResponseHandler;
import com.smartwashr.driver.utils.Constants;
import com.smartwashr.driver.utils.Loading;
import com.smartwashr.driver.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by zeeshan on 6/6/17.
 */

public class ProductsAdapter extends RecyclerView.Adapter<CategoryView> implements iResponseHandler {

    private ArrayList<Order> lst;
    private Activity activity;
    private ArrayList<OrderDetail> list_product;
    private SessionManager sessionManager;

    private String price_total, order_id, status, phone;
    private String lat, lng;

    private String value;

    public ProductsAdapter(Activity activity, ArrayList<Order> list, String value) {
        lst = list;
        this.activity = activity;
        this.value = value;
    }

    @Override
    public CategoryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_view, parent, false);
        list_product = new ArrayList<>();

        return new CategoryView(itemView);
    }

    private Order getObject(int i) {
        return (lst.get(i));
    }

    @Override
    public void onBindViewHolder(CategoryView holder, final int position) {
        sessionManager = new SessionManager(activity);
        if (getObject(position).getStatus().getStatus().equalsIgnoreCase("0")) {
            holder.getStatus().setText("Pending");
            holder.getImageView().setImageBitmap(getBitmap(activity, R.drawable.ic_pending));
            status = "pending";
        } else if (getObject(position).getStatus().getStatus().equalsIgnoreCase("3")) {
            holder.getStatus().setText("Completed");
            holder.getImageView().setImageBitmap(getBitmap(activity, R.drawable.ic_completed));
            status = "completed";
        }
        holder.getParent().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (Internet.isAvailable(activity)) {
//                    new RestCaller(ProductsAdapter.this, SmartWashr.getRestClient().orderDetail(sessionManager.get(Constants.ACCESS_TOKEN), getObject(position).getId()+""), 1);
//                    Loading.show(activity, false, "Please wait...");
//                    price_total = getObject(position).getTotal()+"";
//                    lat = getObject(position).getLatLng().getCoordinates().get(0)+"";
//                    lng = getObject(position).getLatLng().getCoordinates().get(1)+"";
//                    phone = getObject(position).getUser().getPhone().toString();
//                } else {
//                    Toast.makeText(activity, "No internet available", Toast.LENGTH_SHORT).show();
//                }

                Intent i = new Intent(activity, OrderDetailActivity.class);
                Constants.order = lst.get(position);
                activity.startActivity(i);


            }
        });
        holder.getTitle_num().setText("000" + getObject(position).getId() + "");
        holder.getPrice().setText(Constants.CURRENCY+" " + getObject(position).getTotal());
    }

    private static Bitmap getBitmap(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawable) {
            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    @Override
    public void onSuccess(Call call, Response response, int reqCode) {
        Loading.cancel();
        DetailResponse detailModel = (DetailResponse) response.body();
        Orders orders = (Orders) detailModel.getOrders();

        for (int i = 0; i < orders.getOrderDetail().size(); i++) {
            OrderDetail orderDetail = (OrderDetail) orders.getOrderDetail().get(i);
            list_product.add(orderDetail);
        }
        order_id = orders.getOrderId();

        Gson gson = new Gson();
        String product_list = gson.toJson(list_product);
        Intent i = new Intent(activity, OrderDetailActivity.class);
        i.putExtra("list", product_list);
        i.putExtra("total", price_total);
        i.putExtra("order_id", order_id);
        i.putExtra("lat", lat);
        i.putExtra("lng", lng);
        i.putExtra("status", status);
        i.putExtra("phone", phone);
        list_product.clear();
        activity.startActivity(i);
        activity.finish();
    }

    @Override
    public void onFailure(Call call, GenericResponse error, int reqCode) {
        Loading.cancel();
    }

    @Override
    public void onApiCrash(Call call, Throwable t, int reqCode) {
        Loading.cancel();
    }

}
