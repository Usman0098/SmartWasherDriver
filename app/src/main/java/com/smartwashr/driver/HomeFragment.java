package com.smartwashr.driver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smartwashr.driver.models.GenericResponse;
import com.smartwashr.driver.models.GetOrders.Order;
import com.smartwashr.driver.models.GetOrders.OrdersResults;
import com.smartwashr.driver.restiapis.RestCaller;
import com.smartwashr.driver.restiapis.iResponseHandler;
import com.smartwashr.driver.utils.Constants;
import com.smartwashr.driver.utils.FontUtils;
import com.smartwashr.driver.utils.Internet;
import com.smartwashr.driver.utils.Loading;
import com.smartwashr.driver.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zeeshan on 5/31/17.
 */

public class HomeFragment extends Fragment implements iResponseHandler {

    private RecyclerView recyclerView;
    private ProductsAdapter adapter;
    private ArrayList<Order> driverOrders;
    public static String cat_name = "";
    private Spinner mySpinner;
    private TextView no_data;
    private SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View set = inflater.inflate(R.layout.home_orders, container, false);
        recyclerView = (RecyclerView) set.findViewById(R.id.recylerView);
        no_data = (TextView) set.findViewById(R.id.no_data);
        mySpinner = (Spinner) set.findViewById(R.id.category_selector);


        FontUtils.setFont(mySpinner);
        sessionManager = new SessionManager(getActivity());
        driverOrders = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ProductsAdapter(getActivity(), driverOrders, cat_name);
        if (driverOrders.size() == 0) {
            loadSpinnerData();
        }
        recyclerView.setAdapter(adapter);

        return set;
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.e("Currency",Constants.CURRENCY);
    }

    private void loadSpinnerData() {
        mySpinner.setVisibility(View.VISIBLE);
        // Creating adapter for spinner
        List<String> list = new ArrayList<String>();

        list.add("Select Product");
        list.add("Order awaiting pickup from client");
        list.add("Order received from clients");
        list.add("Order awaiting delivery to client");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);


        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mySpinner.setAdapter(dataAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                driverOrders.clear();
                adapter.notifyDataSetChanged();
                if (pos == 1) {
                    makeCall(1);
                } else if (pos == 2) {
                    makeCall(3);
                } else if( pos == 3) {
                    makeCall(2);

                }else
                    {
                        makeCall(1);
                    }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void makeCall(int i) {
        if (Internet.isAvailable(getActivity())) {
            /*if (i == 1) {
                new RestCaller(HomeFragment.this, SmartWashr.getRestClient().getOrders(sessionManager.get(Constants.ACCESS_TOKEN), "2"), 1);
                Loading.show(getActivity(), false, "Please wait");
            } else if (i == 2) {
                new RestCaller(HomeFragment.this, SmartWashr.getRestClient().getOrders(sessionManager.get(Constants.ACCESS_TOKEN), "8"), 1);
                Loading.show(getActivity(), false, "Please wait");
            } else if (i == 3) {
                new RestCaller(HomeFragment.this, SmartWashr.getRestClient().getOrders(sessionManager.get(Constants.ACCESS_TOKEN), "3"), 1);
                Loading.show(getActivity(), false, "Please wait");
            }*/

            if (i == 1) {
                new RestCaller(HomeFragment.this, SmartWashr.getRestClient().getOrders(sessionManager.get(Constants.ACCESS_TOKEN), "2"), 1);
                Loading.show(getActivity(), false, "Please wait");
            } else if (i == 2) {
                new RestCaller(HomeFragment.this, SmartWashr.getRestClient().getOrders(sessionManager.get(Constants.ACCESS_TOKEN), "8"), 1);
                Loading.show(getActivity(), false, "Please wait");
            } else if (i == 3) {
                new RestCaller(HomeFragment.this, SmartWashr.getRestClient().getOrders(sessionManager.get(Constants.ACCESS_TOKEN), "3"), 1);
                Loading.show(getActivity(), false, "Please wait");
            }
        }
    }

    @Override
    public void onSuccess(Call call, Response response, int reqCode) {
        Loading.cancel();

        OrdersResults results = (OrdersResults) response.body();
        Log.e("response", new Gson().toJson(response.body()));

        //    getting currency code here
        if(!results.getOrders().isEmpty())
        {
            if (!results.getOrders().get(0).getCurrency_code().isEmpty()){
            Constants.CURRENCY = results.getOrders().get(0).getCurrency_code();
            }

//            if (!results.getOrders().get(results.getOrders().size()-1).getDetail().isEmpty()) {
//                if (!results.getOrders().get(results.getOrders().size()-1).getDetail().get(0).getCurrency_code().isEmpty()) {
//                    Constants.CURRENCY = results.getOrders().get(results.getOrders().size()-1).getDetail().get(0).getCurrency_code();
////                    Toast.makeText(getContext(), Constants.CURRENCY, Toast.LENGTH_LONG).show();
//                }
//            }

        }

        //

        if (results.getOrders() != null) {
            if (results.getOrders().size() > 0) {
                no_data.setVisibility(View.GONE);
                for (int i = 0; i < results.getOrders().size(); i++) {

                    Order driverOrder = (Order) results.getOrders().get(i);
                    if (driverOrder !=  null)
                    {
                        driverOrders.add(driverOrder);
                    }


                }
            } else {
                no_data.setVisibility(View.VISIBLE);
            }
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onFailure(Call call, GenericResponse error, int reqCode) {
        Loading.cancel();
        Toast.makeText(getActivity(), "Something went wrong, Try again in a while.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onApiCrash(Call call, Throwable t, int reqCode) {
        Loading.cancel();
        t.printStackTrace();
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }


}


