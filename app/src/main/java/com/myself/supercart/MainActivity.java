package com.myself.supercart;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button Loginbtn, Signupbtn;
    EditText UserID, UserPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
//                Intent intent;
//                if(myID.equals("12")){
//                    intent = new Intent(getApplicationContext(),homeActivity.class);
//                    startActivity(intent);
//                }else {
//                    intent = new Intent(getApplicationContext(),homeActivity.class);
//                    startActivity(intent);
//                    //임시로 넣어둠. 원래는 >> showAlertDialog();
//                }
                Intent intent = new Intent(getApplicationContext(),homeActivity.class);
                startActivity(intent);
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

}