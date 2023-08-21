package com.myself.supercart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class userActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        Button localDBtest = (Button)findViewById(R.id.localDBtestbtn);//로컬 DB 테스트 버튼
        Button firebaseDBtest = (Button)findViewById(R.id.firebaseDBtestbtn);//Firebase 실시간 DB 테스트 버튼
        Button firestoreDBtest = (Button)findViewById(R.id.fireStoretestbtn);//FireStore DB 테스트 버튼

        localDBtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),test1Activity.class);
                startActivity(intent);
            }
        });

        firebaseDBtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),test2Activity.class);
                startActivity(intent);
            }
        });

        firestoreDBtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),FireStoretestActivity.class);
                startActivity(intent);
            }
        });

    }
}
