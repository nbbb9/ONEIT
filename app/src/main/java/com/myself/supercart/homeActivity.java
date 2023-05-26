package com.myself.supercart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class homeActivity extends AppCompatActivity {
    DatabaseReference mReference;
    FirebaseDatabase mDatabase;
    ChildEventListener mChild;
    TextView hellouser;
    ImageButton heartbtn, basketbtn,mylocbtn,settingbtn, connectbtn, paybtn, userbtn;
    SearchView searchView;

    ListView things_listView;//상품 출력 리스트뷰.
    Button testbtn, testbtn2;
    public static ArrayList<Things> thingsList = new ArrayList<Things>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //객체화
        hellouser = (TextView)findViewById(R.id.hellouser);//사용자의 이름에 맞게 안녕하세요 표시.
        heartbtn = (ImageButton)findViewById(R.id.heartbtn);//찜목록 버튼.
        basketbtn = (ImageButton)findViewById(R.id.basketbtn);//장바구니 버튼.
        mylocbtn = (ImageButton)findViewById(R.id.mylocationbtn);//내 위치 새로고침 버튼.
        settingbtn = (ImageButton)findViewById(R.id.settingbtn);//어플 설정 버튼.
        connectbtn = (ImageButton)findViewById(R.id.connectbtn);//카트와 연결하기 기능 창 버튼.
        paybtn = (ImageButton)findViewById(R.id.paymentbtn);//결제 창 버튼.
        userbtn = (ImageButton)findViewById(R.id.userbtn);//사용자 벙보 창 버튼.
        searchView = (SearchView) findViewById(R.id.search_view);//검색 창.

        testbtn = (Button)findViewById(R.id.testbtn);//임시 테스트 버튼
        testbtn2 = (Button)findViewById(R.id.testbtn2);//외부 DB테스트 버튼\

        hellouser.setText("LYW님 반가워요!");

        setUpData(); //데이터 셋팅

        setUpList(); //리스트 셋팅

        setUpOnClickListener();//상세페이지 이벤트

        heartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),likelistActivity.class);
                startActivity(intent);
            }
        });

        basketbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),basketlistActivity.class);
                startActivity(intent);
            }
        });

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),settingActivity.class);
                startActivity(intent);
            }
        });

        connectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),connectActivity.class);
                startActivity(intent);
            }
        });

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),paymentActivity.class);
                startActivity(intent);
            }
        });

        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),userActivity.class);
                startActivity(intent);
            }
        });

        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),test1Activity.class);
                startActivity(intent);
            }
        });

        testbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),test2Activity.class);
                startActivity(intent);
            }
        });

    }

    //데이터 셋팅>>여기를 firebase에서 가져오도록 설정.
    private void setUpData(){

        Things apple = new Things("0", "사과", "1200", "2", R.drawable.apple);
        thingsList.add(apple);

        Things banana = new Things("1","바나나", "2340", "6", R.drawable.banana);
        thingsList.add(banana);

        Things eggplant = new Things("2","가지", "3000", "4", R.drawable.eggplant);
        thingsList.add(eggplant);

        Things snack = new Things("3", "과자(간식)", "2400", "1", R.drawable.snack);
        thingsList.add(snack);

        Things fish = new Things("4", "생선", "4500", "3", R.drawable.fish);
        thingsList.add(fish);

        Things grape = new Things("5", "포도", "3200", "2", R.drawable.grape);
        thingsList.add(grape);

        Things garlic = new Things("6", "마늘","2300", "3", R.drawable.garlic);
        thingsList.add(garlic);

        Things onion = new Things("7", "양파", "2700", "3", R.drawable.onion);
        thingsList.add(onion);

        Things peanutbutter = new Things("8", "땅콩버터", "7500", "5", R.drawable.peanut_butter);
        thingsList.add(peanutbutter);

        Things softdrink = new Things("9", "탄산음료", "2100", "2", R.drawable.softdrink);
        thingsList.add(softdrink);

    }

    private void initDatabase(){

        mChild = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        mReference.addChildEventListener(mChild);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }

    //리스트 셋팅
    private void setUpList(){

        things_listView = (ListView)findViewById(R.id.things_listview);

        ThingsAdapter adapter = new ThingsAdapter(getApplicationContext(), 0, thingsList);
        things_listView.setAdapter(adapter);

    }

    //상세페이지 이벤트
    private void setUpOnClickListener(){
        things_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Things selectThings = (Things)things_listView.getItemAtPosition(position);
                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
                showDetail.putExtra("id", selectThings.getId());
                startActivity(showDetail);

            }
        });
    }

}