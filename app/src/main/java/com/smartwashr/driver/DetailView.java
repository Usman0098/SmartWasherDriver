package com.smartwashr.driver;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zeeshan on 6/25/17.
 */

class DetailView extends RecyclerView.ViewHolder {

    private TextView total_num, txt_title, total, service;
    ImageView img, iv_del;
    private String img_url;
    private LinearLayout product;

    public DetailView(View itemView) {
        super(itemView);

        txt_title = (TextView) itemView.findViewById(R.id.product_name);
        total_num = (TextView) itemView.findViewById(R.id.product_quantity);
        service = (TextView) itemView.findViewById(R.id.product_service);
        total = (TextView) itemView.findViewById(R.id.product_total);
        img = (ImageView) itemView.findViewById(R.id.img);
        iv_del = (ImageView) itemView.findViewById(R.id.iv_del);
        product = (LinearLayout) itemView.findViewById(R.id.parent);
        img_url = "";
    }

    public TextView getTotal_num() {
        return total_num;
    }

    public void setTotal_num(TextView total_num) {
        this.total_num = total_num;
    }

    public TextView getTxt_title() {
        return txt_title;
    }

    public void setTxt_title(TextView txt_title) {
        this.txt_title = txt_title;
    }

    public TextView getTotal() {
        return total;
    }

    public void setTotal(TextView total) {
        this.total = total;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public LinearLayout getProduct() {
        return product;
    }

    public void setProduct(LinearLayout product) {
        this.product = product;
    }

    public TextView getService() {
        return service;
    }

    public void setService(TextView service) {
        this.service = service;
    }
}