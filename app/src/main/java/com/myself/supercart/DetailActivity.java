package com.myself.supercart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    Things selectedThings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSelectedThings(); //선택한 동물 정보 가져오기

        setValues(); //가져온 정보 화면에 보여주기

    }

    private void setValues(){
        TextView tv = findViewById(R.id.things_detail_name);
        ImageView iv = findViewById(R.id.things_detail_image);

        tv.setText(selectedThings.getName());
        iv.setImageResource((selectedThings.getImage()));

    }

    private void getSelectedThings() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        selectedThings = homeActivity.thingsList.get(Integer.valueOf(id));
    }

}