package com.myself.supercart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    EditText username, userpw, userage, userheight, userweight, usernumber, find;
    Button insertbtn, donebtn, findbtn;

    TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        //firebase 실시간 DB 관리 객체 얻어오기
        firebaseDatabase= FirebaseDatabase.getInstance();

        //저장시킬 노드 참조객체 가져오기 //getreference()안에 아무것도 안쓰면 최상위 노드
        mDatabase = FirebaseDatabase.getInstance("https://oneit-c6974-default-rtdb.firebaseio.com/").getReference();

        username = (EditText) findViewById(R.id.username);
        userpw = (EditText)findViewById(R.id.userpw);
        userage = (EditText) findViewById(R.id.userage);
        userheight = (EditText) findViewById(R.id.userheight);
        userweight = (EditText) findViewById(R.id.userweight);
        usernumber = (EditText) findViewById(R.id.usernumer);
        insertbtn = (Button) findViewById(R.id.insert);
        donebtn = (Button) findViewById(R.id.next);

        find = (EditText)findViewById(R.id.find);
        data = (TextView)findViewById(R.id.data);
        findbtn = (Button) findViewById(R.id.findbtn);

        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = username.getText().toString();
                String user_pw = userpw.getText().toString();
                String user_age = userage.getText().toString();
                String user_height = userheight.getText().toString();
                String user_weight = userweight.getText().toString();
                String user_number = usernumber.getText().toString();

                HashMap result = new HashMap<>();
                result.put("username", user_name);
                result.put("userpw", user_pw);
                result.put("userage", user_age);
                result.put("userheight", user_height);
                result.put("userweight", user_weight);
                result.put("usernumber", user_number);

                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                builder.setMessage("회원가입을 진행하시겠습니까?");

                // Positive 버튼 생성 및 클릭 이벤트 설정
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        writeUserInfo(user_name, user_pw, user_age, user_height, user_weight, user_number);
                        Toast.makeText(getApplicationContext(), "회원가입 되었습니다!", Toast.LENGTH_SHORT).show();
                        writeUserInfo(user_name, user_pw, user_age, user_height, user_weight, user_number);
                    }
                });

                // Negative 버튼 생성 및 클릭 이벤트 설정
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "회원가입이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                // AlertDialog 생성 및 표시
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        findbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readUserInfo(find.getText().toString());
            }
        });

    }

    private void writeUserInfo(String username ,String userpw, String userage, String userheight, String userweight, String usernumber){

        UserInfoModel user = new UserInfoModel(username,userpw,userage, userheight, userweight, usernumber);
        //데이터 저장
        mDatabase.child("UserInformation").child(username).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void avoid) {
                Toast.makeText(getApplicationContext(), "저장완료", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "저장실패", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void readUserInfo(String username){
        //데이터 읽기
        mDatabase.child("UserInformation").child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserInfoModel userinfo = snapshot.getValue(UserInfoModel.class);
                data.setText("이름: " + userinfo.username
                        + " PW: " + userinfo.userpw
                        + " 나이: " + userinfo.userage
                        + " 키: " + userinfo.userheight
                        + " 무게: " + userinfo.userweight
                        + " 번호: " + userinfo.usernumber);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {//참조에 엑세스 할 수 없을 때
                Toast.makeText(getApplicationContext(), "데이터를 가져오는데 실패했습니다.", Toast.LENGTH_LONG).show();
            }
        });

    }

}