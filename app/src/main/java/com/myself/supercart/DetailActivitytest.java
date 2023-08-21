package com.myself.supercart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivitytest extends AppCompatActivity {

    private FirebaseDatabase mDatabase; //데이터베이스에 접근할 수 있는 진입점 클래스.
    private DatabaseReference myRef;//데이터베이스의 주소를 저장.

    private String targetValue1 = "사과";
    private String targetValue2 = "바나나";
    private String targetValue3 = "가지";
    private String targetValue4 = "과자";
    private String targetValue5 = "생선";
    private String targetValue6 = "포도";
    private String targetValue7 = "마늘";
    private String targetValue8 = "양파";
    private String targetValue9 = "땅콩버터";
    private String targetValue10 = "탄산음료";
    private String targetkey = "name";
    CustomItem selectedItem;
    TextView name, price;
    ImageView iv;
    ImageButton heartbtn;
    int img;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thingsdetail);

        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Things");

        name = findViewById(R.id.things_detail_name);
        iv = findViewById(R.id.things_detail_image);
        price = findViewById(R.id.things_detail_price);

        heartbtn = findViewById(R.id.heartbtn);

        heartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedItemName = name.getText().toString();

                // 선택한 항목을 리스트에 추가
                basketlistActivity.itemList.add(selectedItemName);

                // 알림 대화상자를 사용하여 사용자에게 선택한 항목이 추가되었다고 알림
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivitytest.this);
                builder.setMessage(selectedItemName + " 이(가) 장바구니에 추가되었습니다!")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();

            }
        });

        getSelectedThings();//선택한 상품정보 보여주기

    }

    private void getSelectedThings(){
        //SettingActivity에서 전달된 데이터를 가져옵니다.
        Intent intent = getIntent();

        name.setText(intent.getStringExtra("name"));
        img = Integer.parseInt(intent.getStringExtra("image"));
        iv.setImageResource(img);
        price.setText(intent.getStringExtra("price"));

    }

    private void sendtobasket(){
        //선택항목 장바구니로 전송

        String selectedItemName = name.getText().toString();
        Intent intent = new Intent(DetailActivitytest.this, basketlistActivity.class);

        // 상품 정보를 intent에 추가
        intent.putExtra("item_name", selectedItemName);

    }


}
