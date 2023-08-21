package com.myself.supercart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ProductInfoAdapter extends ArrayAdapter<ProductInfo> {

    public ProductInfoAdapter(Context context, ArrayList<ProductInfo> productQuantityList) {
        super(context, 0, productQuantityList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cartthings_item, parent, false);
        }

        ProductInfo productInfo = getItem(position);

        if (productInfo != null) {

            ImageView imageView = convertView.findViewById(R.id.things_image);
            TextView productTextView = convertView.findViewById(R.id.things_name);
            TextView quantityTextView = convertView.findViewById(R.id.things_quantity);

            imageView.setImageResource(productInfo.getImageResource());
            productTextView.setText(productInfo.getProduct());
            quantityTextView.setText(String.valueOf(productInfo.getQuantity()));
        }

        return convertView;
    }
}
