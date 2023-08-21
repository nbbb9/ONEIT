package com.myself.supercart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FireStoretestActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText find;
    TextView data;
    Button findbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firestoretest);

        find = (EditText)findViewById(R.id.find2);
        data = (TextView)findViewById(R.id.testdata);
        findbtn = (Button) findViewById(R.id.findbtn2);

        findbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myreaddata();

            }
        });

    }

    private void myreaddata(){
        //CollectionReference는 Firestore의 컬랙션을 참조하는 객체.
        CollectionReference Ref = db.collection("UserInformation");
        //get을 통해서 해당 컬랙션의 정보를 가져온다.
        Ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@androidx.annotation.NonNull Task<QuerySnapshot> task) {
                //작업이 성공적으로 마쳤을 때
                if(task.isSuccessful()){
                    //컬랙션 아래에 있는 모든 정보를 가져온다.
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        //여러 방법으로 데이터를 가져올 수 있다.
                        UserInfoModel userinfo = document.toObject(UserInfoModel.class);
                        data.setText("이름: " + userinfo.username);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "데이터를 가져오는데 실패했습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
