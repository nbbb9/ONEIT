package com.myself.supercart;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class basketlistActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference mRef;

    Button gotocart;
    ListView basketlist;
    ArrayAdapter<String> arrayAdapter;
    public static ArrayList<String> itemList = new ArrayList<>();// 선택한 항목을 저장하는 static list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basketlist);

        basketlist = findViewById(R.id.basketlist);
        gotocart = findViewById(R.id.tocartbtn);

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.basketlisttext, itemList);
        basketlist.setAdapter(arrayAdapter);

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("BasketInfo");

        gotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendItemsToDatabase();
            }
        });

        //항목을 길게 누르면 삭제하는 기능.
        basketlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // AlertDialog로 사용자에게 항목 삭제를 확인
                AlertDialog.Builder builder = new AlertDialog.Builder(basketlistActivity.this);
                builder.setMessage("해당 상품을 삭제하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // 목록에서 해당 항목을 삭제하고 arrayAdapter에 알림
                                itemList.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                                Toast.makeText(basketlistActivity.this, "상품이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();

                return false;
            }
        });
    }

    private void sendItemsToDatabase() {// 데이터를 Realtime Database에 저장

        String key = mRef.push().getKey(); // 고유한 키 생성

        mRef.setValue(itemList)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(basketlistActivity.this, "카트로 전송되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(basketlistActivity.this, "전송에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}