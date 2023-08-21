package com.myself.supercart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<CustomItem> {

    public CustomAdapter(Context context, ArrayList<CustomItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.things_item, parent, false);
        }

        CustomItem currentItem = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.things_image);
        imageView.setImageResource(currentItem.getImageResource());

        TextView name = convertView.findViewById(R.id.things_name);
        name.setText(currentItem.getText());

        TextView price = convertView.findViewById(R.id.things_price);
        price.setText(currentItem.getText2());

        return convertView;
    }
}