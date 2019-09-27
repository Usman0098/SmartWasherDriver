package com.smartwashr.driver;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zeeshan on 6/6/17.
 */

class CategoryView extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView title_num, status, price;
    private LinearLayout parent;

    public CategoryView(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.order_status_img);
        title_num = (TextView) itemView.findViewById(R.id.title_num);
        status = (TextView) itemView.findViewById(R.id.status_progress);
        price = (TextView) itemView.findViewById(R.id.price);
        parent = (LinearLayout) itemView.findViewById(R.id.parent);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTitle_num() {
        return title_num;
    }

    public void setTitle_num(TextView title_num) {
        this.title_num = title_num;
    }

    public TextView getStatus() {
        return status;
    }

    public void setStatus(TextView status) {
        this.status = status;
    }

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    public LinearLayout getParent() {
        return parent;
    }

    public void setParent(LinearLayout parent) {
        this.parent = parent;
    }
}
