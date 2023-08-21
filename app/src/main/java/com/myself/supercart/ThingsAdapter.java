package com.myself.supercart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ThingsAdapter extends ArrayAdapter<Things> {

    public ThingsAdapter(Context context, int resource, List<Things>thingsList){
        super (context, resource, thingsList);//생성자
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        Things things = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.things_item, parent, false);
        }

        TextView tv = convertView.findViewById(R.id.things_name); //things_item.xml에 존재함.
        ImageView iv = convertView.findViewById(R.id.things_image); //things_item.xml에 존재함.

        tv.setText(things.getName());
        iv.setImageResource(things.getImage());

        return convertView;
    }

}
