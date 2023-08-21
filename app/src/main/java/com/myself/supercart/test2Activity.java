package com.myself.supercart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class test2Activity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    EditText edit_name, edit_price, edit_weight;
    Button insert_btn, update_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);

        //firebase 실시간 DB 관리 객체 얻어오기
        firebaseDatabase= FirebaseDatabase.getInstance();

        //저장시킬 노드 참조객체 가져오기 //getReference()안에 아무것도 안쓰면 최상위 노드
        mDatabase = FirebaseDatabase.getInstance("https://oneit-c6974-default-rtdb.firebaseio.com/").getReference();

        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_price = (EditText)findViewById(R.id.edit_price);
        edit_weight = (EditText)findViewById(R.id.edit_weight);

        insert_btn = (Button)findViewById(R.id.btn_insert);
        update_btn = (Button)findViewById(R.id.btn_update);

        insert_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String text_name = edit_name.getText().toString();
                String text_price = edit_price.getText().toString();
                String text_weight = edit_weight.getText().toString();

                HashMap result = new HashMap<>();
                result.put("name", text_name);
                result.put("price", text_price);
                result.put("weight", text_weight);

                writeThings(text_name, text_price, text_weight);
            }
        });

    }

    private void writeThings(String name, String price, String weight){
        ThingsInfoModel things = new ThingsInfoModel(name, price, weight);
        //데이터 저장
        mDatabase.child("Things").child(name).setValue(things).addOnSuccessListener(new OnSuccessListener<Void>() { //데이터베이스에 넘어간 이후 처리
            @Override
            public void onSuccess(Void aVoid){
                Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e){
                Toast.makeText(getApplicationContext(), "저장 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

}