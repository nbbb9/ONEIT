package com.myself.supercart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class homeActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase; //데이터베이스에 접근할 수 있는 진입점 클래스.
    private DatabaseReference myRef;//데이터베이스의 주소를 저장.

    public ArrayList<String> matchingDataList = new ArrayList<>();
    public ArrayList<CustomItem> itemlist = new ArrayList<>();
    ArrayAdapter<String> adapter;

    ImageButton heartbtn, basketbtn,mylocbtn,settingbtn, connectbtn, paybtn, userbtn;
    SearchView searchView;

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

    ListView things_listView;//상품 출력 리스트뷰.
    Button testbtn, testbtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //heartbtn = (ImageButton)findViewById(R.id.heartbtn);//찜목록 버튼.
        basketbtn = (ImageButton)findViewById(R.id.basketbtn);//장바구니 버튼.
        settingbtn = (ImageButton)findViewById(R.id.settingbtn);//어플 설정 버튼.
        paybtn = (ImageButton)findViewById(R.id.paymentbtn);//결제 창 버튼.
        userbtn = (ImageButton)findViewById(R.id.userbtn);//사용자 벙보 창 버튼.
        searchView = (SearchView) findViewById(R.id.search_view);//검색 창.

        things_listView = (ListView)findViewById(R.id.things_listview);//상품 정보 리스트

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, matchingDataList);

        mDatabase = FirebaseDatabase.getInstance();

        setUpData(); //데이터 셋팅

        setUpOnClickListener();//상세페이지 이벤트

        //searchThings();//검색 기능

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

    }

    //데이터 셋팅>>현재 로컬 DB와 firebase DB에서의 데이터 동일.
    private void setUpData(){

        myRef = mDatabase.getReference("Things");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(snapshot.exists()){
                    Map<String, Object> dataMap = (Map<String, Object>)snapshot.getValue();
                    Object data = dataMap.get(targetkey);
                    Log.d("Firebase_Things", "dataname: " + data);
                    Log.d("HashMap", "HashMap: " + dataMap);
                    if(dataMap != null){
                        if(data != null && data.toString().equals(targetValue1)){
                            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), itemlist);
                            things_listView.setAdapter(adapter);
                            itemlist.add(new CustomItem(R.drawable.apple, "사과", "1200원"));
                        }
                        if(data != null && data.toString().equals(targetValue2)){
                            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), itemlist);
                            things_listView.setAdapter(adapter);
                            itemlist.add(new CustomItem(R.drawable.banana, "바나나", "2340원"));
                        }
                        if(data != null && data.toString().equals(targetValue3)){
                            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), itemlist);
                            things_listView.setAdapter(adapter);
                            itemlist.add(new CustomItem(R.drawable.eggplant, "가지", "3000원"));
                        }
                        if(data != null && data.toString().equals(targetValue4)){
                            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), itemlist);
                            things_listView.setAdapter(adapter);
                            itemlist.add(new CustomItem(R.drawable.snack, "과자", "2400원"));
                        }
                        if(data != null && data.toString().equals(targetValue5)){
                            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), itemlist);
                            things_listView.setAdapter(adapter);
                            itemlist.add(new CustomItem(R.drawable.fish, "생선", "4500원"));
                        }
                        if(data != null && data.toString().equals(targetValue6)){
                            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), itemlist);
                            things_listView.setAdapter(adapter);
                            itemlist.add(new CustomItem(R.drawable.grape, "포도", "3200원"));
                        }
                        if(data != null && data.toString().equals(targetValue7)){
                            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), itemlist);
                            things_listView.setAdapter(adapter);
                            itemlist.add(new CustomItem(R.drawable.garlic, "마늘", "2300원"));
                        }
                        if(data != null && data.toString().equals(targetValue8)){
                            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), itemlist);
                            things_listView.setAdapter(adapter);
                            itemlist.add(new CustomItem(R.drawable.onion, "양파", "2700원"));
                        }
                        if(data != null && data.toString().equals(targetValue9)){
                            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), itemlist);
                            things_listView.setAdapter(adapter);
                            itemlist.add(new CustomItem(R.drawable.peanut_butter, "땅콩버터", "7500원"));
                        }
                        if(data != null && data.toString().equals(targetValue10)){
                            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), itemlist);
                            things_listView.setAdapter(adapter);
                            itemlist.add(new CustomItem(R.drawable.softdrink, "탄산음료", "2100원"));
                        }
                    }
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}

        });

    }

    //상세페이지 이벤트
    private void setUpOnClickListener(){
        things_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                CustomItem selectedItem = itemlist.get(position);//선택한 항목의 데이터를 가져옵니다.
                //showDetail을 사용하여 DetailActivity로 데이터를 전달합니다.
                Intent showDetail = new Intent(homeActivity.this, DetailActivitytest.class);
                //putExtra의 첫값은 식별 태그, 뒤에는 다음 화면에 넘길 값.
                showDetail.putExtra("name", selectedItem.getText());
                showDetail.putExtra("image", Integer.toString(selectedItem.getImageResource()));
                showDetail.putExtra("price", selectedItem.getText2());
                startActivity(showDetail);

            }
        });
    }

    //검색기능 이벤트
    private void searchThings(){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

    }

}