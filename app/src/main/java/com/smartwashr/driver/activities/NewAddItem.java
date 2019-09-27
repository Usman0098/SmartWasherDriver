package com.smartwashr.driver.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smartwashr.driver.R;
import com.smartwashr.driver.SmartWashr;
import com.smartwashr.driver.models.GenericResponse;
import com.smartwashr.driver.models.categoryResponse.categoryResponse.CategoryResponse;
import com.smartwashr.driver.models.categoryResponse.categoryResponse.Datum;
import com.smartwashr.driver.models.categoryResponse.categoryResponse.Product;
import com.smartwashr.driver.models.categoryResponse.categoryResponse.Service;
import com.smartwashr.driver.models.loginresponse.LoginResponse;
import com.smartwashr.driver.restiapis.RestCaller;
import com.smartwashr.driver.restiapis.iResponseHandler;
import com.smartwashr.driver.utils.Constants;
import com.smartwashr.driver.utils.Internet;
import com.smartwashr.driver.utils.Loading;
import com.smartwashr.driver.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class NewAddItem extends AppCompatActivity implements iResponseHandler {

    private Spinner category_selector;
    private Spinner services_selector;
    private EditText et_qty;
    private TextView et_paid_price;
    private TextView et_laundry_price;
    int order_id = 0;
    private Button submit, add, minus;
    ArrayList<Product> products = new ArrayList<>();

    ArrayList<Service> services = new ArrayList<>();
    private int service_id, product_id;
    String price, laundry_price;
    int prod_position;
    double paidPrice;
    String strPaidprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        order_id = getIntent().getIntExtra("order_id", 0);
        Log.e("Order Id", order_id + "");
        et_qty = (EditText) findViewById(R.id.et_qty);
        et_paid_price = findViewById(R.id.et_paid_price);
        et_laundry_price = findViewById(R.id.et_laundry_price);
        add = findViewById(R.id.btn_plus);
        minus = findViewById(R.id.btn_minus);
        submit = (Button) findViewById(R.id.submit);
        services_selector = (Spinner) findViewById(R.id.services_selector);
        category_selector = (Spinner) findViewById(R.id.category_selector);
        fetchData();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (category_selector.getSelectedItemPosition() == 0)
                    Toast.makeText(NewAddItem.this, "Please select some product", Toast.LENGTH_SHORT).show();
                else if (services_selector.getSelectedItemPosition() == 0)
                    Toast.makeText(NewAddItem.this, "Please select some service", Toast.LENGTH_SHORT).show();
                else if (et_paid_price.getText().toString().trim().isEmpty())
                    Toast.makeText(NewAddItem.this, "Please enter paid price", Toast.LENGTH_SHORT).show();
                else if (et_laundry_price.getText().toString().trim().isEmpty())
                    Toast.makeText(NewAddItem.this, "Please enter laundry price", Toast.LENGTH_SHORT).show();
                else
//                    Log.e("Param1",product_id+"");
//                    Log.e("Params2",service_id+"");
//                    Log.e("Params3",paidPrice+"");
//                    Log.e("Params4",laundry_price+"");
//                    Log.e("Params5",price+"");
                    calculatePrice();
                    if (paidPrice+"" != null && laundry_price != null )
                    {
                        addProduct(
                                product_id,
                                service_id,
                                Integer.parseInt(et_qty.getText().toString().trim()),
//                            et_paid_price.getText().toString().trim(),
//                            et_laundry_price.getText().toString().trim());

                                paidPrice+"",
                                laundry_price);
                    }


            }
        });

        et_qty.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        // Identifier of the action. This will be either the identifier you supplied,
                        // or EditorInfo.IME_NULL if being called due to the enter key being pressed.
                        if (actionId == EditorInfo.IME_ACTION_SEARCH
                                || actionId == EditorInfo.IME_ACTION_DONE
                                || event.getAction() == KeyEvent.ACTION_DOWN
                                && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (et_qty.getText().toString().trim().length() > 0) {
                                if (et_qty.getText().toString().trim().matches("[0-9]+")) {
                                    calculatePrice();
                                }
                            } else {
                                et_qty.requestFocus();
                                et_qty.setError("Please enter valid quantity");
                            }
                            return true;
                        }
                        // Return true if you have consumed the action, else false.
                        return false;
                    }
                });

    }

    private void calculatePrice() {
        if (category_selector.getSelectedItemPosition() == 0) {
            et_qty.requestFocus();
            et_qty.setError("Please select some product");
        } else if (services_selector.getSelectedItemPosition() == 0) {
            et_qty.requestFocus();
            et_qty.setError("Please select some service");
        } else {
            double val = Double.valueOf(price) ;
           // double qty = Double.parseDouble(et_qty.getText().toString());
             int qty = Integer.parseInt(et_qty.getText().toString());

             Log.e("Quantity", qty+"");
            paidPrice = qty * val;
            strPaidprice = paidPrice+"";
            Log.e("Paid Price", paidPrice+"");

            et_paid_price.setText(Constants.CURRENCY + " " + (qty * val));

        }
    }

    private void addProduct(int productId, int serviceId, int qty, String paid_price, String laundry_price) {
        if (Internet.isAvailable(NewAddItem.this)) {
            new RestCaller(NewAddItem.this, SmartWashr.getRestClient().addItem(new SessionManager(this).get(Constants.ACCESS_TOKEN), order_id, productId, serviceId, qty, paid_price, laundry_price), 2);
            Loading.show(NewAddItem.this, false, "Please wait...");
        }
    }

    private void fetchData() {
        if (Internet.isAvailable(NewAddItem.this)) {
            Loading.show(NewAddItem.this, true, "Please wait...");
            LoginResponse loginResponse = new LoginResponse();
            if (Constants.CURRENCY != null && Constants.CURRENCY != "") {
                new RestCaller(NewAddItem.this, SmartWashr.getRestClient().category("android", Constants.CURRENCY), 1);
            }

        }
    }

    @Override
    public void onSuccess(Call call, Response response, int reqCode) {
        Loading.cancel();
        if (reqCode == 1) {
            CategoryResponse categoryResponse = (CategoryResponse) response.body();

//            for (int i = 0; i < categoryResponse.getData().size(); i++) {
//                Datum datum = categoryResponse.getData().get(i);
//                products.addAll(datum.getProducts());
//            }
            int i, j;
            for (i = 0; i < categoryResponse.getData().size(); i++) {
                for (j = 0; j < categoryResponse.getData().get(i).getProducts().size(); j++) {

                    if (categoryResponse.getData().get(i).getProducts().get(j).getServices().size() > 0) {
//                list.addAll(pricingResponse.getData().get(i).getProducts());
                        Datum datum = categoryResponse.getData().get(i);
                        products.add(datum.getProducts().get(j));
//            checkList.addAll(pricingResponse.getData().get(i).getProducts().get(i).getServices());
                        //   products.addAll(datum.getProducts());
                    }
                }
            }


            List<String> list = new ArrayList<>();
            list.add("Select Product");

            for (int k = 0; k < products.size(); k++) {
                list.add(products.get(k).getName());
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(NewAddItem.this,
                    android.R.layout.simple_spinner_item, list);


            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            category_selector.setAdapter(dataAdapter);

            category_selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    et_qty.setText("1");
                    List<String> list = new ArrayList<String>();
                    services.clear();

                    list.add("Select Service");

//                    if (i != 0) {
//                            product_id = products.get(i - 1).getId();
//                            for (int j = 0; j < products.get(i - 1).getServices().size(); j++) {
//                                Service service = products.get(i - 1).getServices().get(j);
//                                services.add(service);
//                                list.add(service.getName());
//                            }
//
////                            product_id = products.get(i).getId();
////                            for (int j = 0; j < products.get(i).getServices().size(); j++) {
////                                Service service = products.get(i).getServices().get(j);
////                                services.add(service);
////                                list.add(service.getName());
//
//
//                    }
//

                    if (position != 0) {
                        product_id = products.get(position - 1).getId();
                        prod_position = position - 1;


                        int product_size = products.get(position - 1).getServices().size();
                        Log.e("product size", product_size + "");
                        for (int j = 0; j < product_size; j++) {
                            Service service = products.get(position - 1).getServices().get(j);

                            services.add(service);
                            list.add(service.getName());

//
                        }


                    }


                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(NewAddItem.this,
                            android.R.layout.simple_spinner_item, list);


                    // Drop down layout style - list view with radio button
                    dataAdapter
                            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    services_selector.setAdapter(dataAdapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            services_selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Log.e("Position", position + "");
                    if (position != 0) {
                        Service service = new Service();

                        service_id = services.get(position - 1).getId();
                        Log.e("Service Id", service_id + "");


                    }
//                    if (position !=0) {
//                        service_id = services.get(position).getId();
//                        for (int i = 0; i < services.size(); i++) {
//                            Product product = products.get(i);
//                            if (product.getId() == product_id) {
////
//                                Log.e("Price", product.getServices().get(service_id).getPrice().toString());
////
//                                price = product.getServices().get(service_id).getPrice().toString();
//                                laundry_price = product.getServices().get(service_id).getDiscountPrice().toString();
//                                Log.d("TAG", Constants.CURRENCY + " " + price + "\n" + laundry_price);
//                                et_paid_price.setText(Constants.CURRENCY + " " + price);
//                                et_laundry_price.setText(Constants.CURRENCY + " " + laundry_price);
//                                break;
//                            }
//                        }
//
//                    }


                    if (position != 0) {

                        for (int i = 0; i < services.size(); i++) {
                            //    Product product = products.get(i);
//                            Log.e("Product Id",product.getId().toString());
                            Log.e("Product_id", product_id + "");

                            //   if (product.getId() == product_id) {


                            Log.e("Currency", Constants.CURRENCY);
                            Log.e("Product", products.get(prod_position).getName());
                            price = products.get(prod_position).getServices().get(position - 1).getPrice().toString();
                            Log.e("Price", price);

                            laundry_price = products.get(prod_position).getServices().get(position - 1).getDiscountPrice().toString();
                            Log.e("Laundry Price", laundry_price);
                            et_paid_price.setText(Constants.CURRENCY + " " + price);
                            et_laundry_price.setText(Constants.CURRENCY + " " + laundry_price);
                            break;
                            //  }
                        }


                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (category_selector.getSelectedItemPosition() == 0) {
                        Toast.makeText(NewAddItem.this, "Please select some product", Toast.LENGTH_SHORT).show();
                    } else if (services_selector.getSelectedItemPosition() == 0) {
                        Toast.makeText(NewAddItem.this, "Please select some service", Toast.LENGTH_SHORT).show();
                    } else {
                        addQty();
                    }
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (category_selector.getSelectedItemPosition() == 0) {
                        Toast.makeText(NewAddItem.this, "Please select some product", Toast.LENGTH_SHORT).show();
                    } else if (services_selector.getSelectedItemPosition() == 0) {
                        Toast.makeText(NewAddItem.this, "Please select some service", Toast.LENGTH_SHORT).show();
                    } else if (!et_qty.getText().toString().equalsIgnoreCase("1")) {
                        minusQty();
                    }
                }
            });


        } else if (reqCode == 2) {
            finish();
        }
    }

    private void minusQty() {
        Double val = Double.parseDouble(price);
        int qty = Integer.parseInt(et_qty.getText().toString()) - 1;
        et_qty.setText(qty + "");
        et_paid_price.setText(Constants.CURRENCY + " " + (qty * val));
    }

    private void addQty() {
        Double val = Double.parseDouble(price);
        int qty = Integer.parseInt(et_qty.getText().toString()) + 1;
        et_qty.setText(qty + "");
        et_paid_price.setText(Constants.CURRENCY + " " + (qty * val));

    }

    @Override
    public void onFailure(Call call, GenericResponse error, int reqCode) {
        Loading.cancel();
    }

    @Override
    public void onApiCrash(Call call, Throwable t, int reqCode) {
        Toast.makeText(NewAddItem.this, t.getMessage(), Toast.LENGTH_LONG).show();
        Loading.cancel();

    }
}
