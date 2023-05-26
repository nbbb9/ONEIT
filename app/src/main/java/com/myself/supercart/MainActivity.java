package com.myself.supercart;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    // Firebase 실시간 데이터베이스 인스턴스를 가져옵니다.
    private DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;

    Button Loginbtn, Signupbtn;
    EditText UserID;
    EditText UserPW;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //firebase 실시간 DB관리 객체 얻어오기
        firebaseDatabase = FirebaseDatabase.getInstance();
        //저장시킬 노드 참조객체 가져오기 //getreference()안에 아무것도 안쓰면 최상위 노드
        mDatabase = FirebaseDatabase.getInstance("https://oneit-c6974-default-rtdb.firebaseio.com/").getReference("UserInformation");

        //객체화
        Loginbtn = (Button)findViewById(R.id.loginbtn);
        Signupbtn = (Button)findViewById(R.id.signupbtn);

        UserID = (EditText) findViewById(R.id.userid);
        UserPW = (EditText) findViewById(R.id.userpassword);

        String myID = UserID.getText().toString();
        String myPW = UserPW.getText().toString();



        //로그인 버튼을 눌렀을 때 발생하는 메소드.
        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(myID.equals("12")){
                    intent = new Intent(getApplicationContext(),homeActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getApplicationContext(),homeActivity.class);
                    startActivity(intent);
                    //임시로 넣어둠. 원래는 >> showAlertDialog();
                }
            }
        });

        Signupbtn.setOnClickListener(new View.OnClickListener() {//회원가입 화면으로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    void showAlertDialog(){//경고 메세지 출력 함수
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("경고");
        builder.setMessage("아이디 또는 비밀번호를 다시 입력하세요.");
        builder.show();
    }

    void readUser(String username){
        //데이터 읽기
        mDatabase.child("UserInformation").child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
    }

}