package com.smartwashr.driver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smartwashr.driver.activities.OrderDetailActivity;
import com.smartwashr.driver.models.GenericResponse;
import com.smartwashr.driver.models.detailOrder.Detail;
import com.smartwashr.driver.models.orderResponse.OrderResponse;
import com.smartwashr.driver.restiapis.RestCaller;
import com.smartwashr.driver.restiapis.iResponseHandler;
import com.smartwashr.driver.utils.Constants;
import com.smartwashr.driver.utils.Loading;
import com.smartwashr.driver.utils.SessionManager;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zeeshan on 6/25/17.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailView> implements iResponseHandler {

    private ArrayList<Detail> lst;
    private Activity activity;
    private int pos;
    private Context context;
    private double newInput;

    public DetailAdapter(Activity activity, ArrayList<Detail> list, Context context) {
        lst = list;
        this.activity = activity;
        this.context=context;

    }

    private Detail getObject(int i) {
        return (lst.get(i));
    }

    @Override
    public DetailView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_view, parent, false);
        return new DetailView(itemView);
    }

    @Override
    public void onBindViewHolder(DetailView holder, final int position) {
        holder.getTxt_title().setText(getObject(position).getProduct().getName());
        holder.getTotal_num().setText("Quantity: " + getObject(position).getQty());
        Log.e("Value in Object",getObject(position).getTotalPaidPrice().toString());
        reduceDouble(asDouble(getObject(position).getTotalPaidPrice()));
        Log.e("Reduced Double",reduceDouble(asDouble(getObject(position).getTotalPaidPrice())).toString());

        holder.getTotal().setText("Total: "+Constants.CURRENCY+" " + reduceDouble(asDouble(getObject(position).getTotalPaidPrice())).toString());
        holder.getService().setText("Service: " + getObject(position).getService().getName());
        Picasso.with(activity).load(getObject(position).getCreatedAt()).placeholder(R.drawable.ic_pending).fit().into(holder.getImg());

        holder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = position;
                Loading.show(activity, false, "Please wait...");
                new RestCaller(DetailAdapter.this, SmartWashr.getRestClient().deleteOrder(
                        new SessionManager(activity).get(Constants.ACCESS_TOKEN),
                        getObject(position).getId() + ""), 1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return lst.size();
    }

    @Override
    public void onSuccess(Call call, Response response, int reqCode) {
        Loading.cancel();
        OrderResponse orderResponse = (OrderResponse)response.body();
        Log.e("Item Del Response",orderResponse.getSuccess().toString());
        if(orderResponse.getSuccess()){
            lst.remove(getObject(pos));
            notifyDataSetChanged();
            Intent intent = new Intent(context, OrderDetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else {
            Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call call, GenericResponse error, int reqCode) {
        Log.e("OnFailure", error.getMsg());
        Loading.cancel();

    }

    @Override
    public void onApiCrash(Call call, Throwable t, int reqCode) {
        Log.e("Api crashed", t.getMessage());
        Loading.cancel();

    }
    private Double reduceDouble(double value) {
        double val = 0.0;
        BigDecimal bd = new BigDecimal(value).setScale(4, RoundingMode.HALF_UP);
        newInput = bd.doubleValue();

        return newInput;
    }
    Double asDouble(Object o) {
        Double val = null;
        if (o instanceof Number) {
            val = ((Number) o).doubleValue();
        }
        return val;
    }

}
