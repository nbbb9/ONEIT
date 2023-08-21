package com.myself.supercart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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
import java.util.Locale;
import java.util.Map;

public class paymentActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase; //데이터베이스에 접근할 수 있는 진입점 클래스.
    private DatabaseReference myRef;//데이터베이스의 주소를 저장.(장바구니)
    private  DatabaseReference getThingsRef;//상품 정보 데이터 가져오기.
    private DatabaseReference thingsRef;//데이터베이스의 주소를 저장.(상품정보)
    private ChildEventListener mChildEventListener;

    ProductInfoAdapter productInfoAdapter;
    public ArrayList<ProductInfo> productInfoList = new ArrayList<>();
    public ArrayList<String> matchingDataList = new ArrayList<>();

    ListView productInfoView;//결제 상품 정보 리스트
    TextView sum_price;//가격 합계
    TextView sum_quantity;//수량 합계
    Button buybtn;//결제하기 버튼
    String product; //상품 이름
    Long quantityLong;//상품 수량
    int sumprice = 0;//합계 수량 변수
    int sumquantity = 0;//합계 수량 변수

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
    private String targetkey = "product";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        setUpData();//현재 로컬 DB와 firebase DB에서의 데이터 동일.

        buybtn = (Button) findViewById(R.id.buybtn);//결제하기 버튼
        sum_price = (TextView) findViewById(R.id.sum_price);//가격 합계
        sum_quantity = (TextView) findViewById(R.id.sum_quantity);//수량 합계
        productInfoView = (ListView) findViewById(R.id.my_cart_list);//결제할 상품 정보 리스트

        ProductInfoAdapter productInfoAdapter = new ProductInfoAdapter(this,productInfoList);

        productInfoView.setAdapter(productInfoAdapter);

        mDatabase = FirebaseDatabase.getInstance();//현재 데이터베이스를 접근할 수 있는 진입점 받기.

        myRef = mDatabase.getReference().child("PaymentInfo");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                ProductInfo productInfo = snapshot.getValue(ProductInfo.class);
                Log.d("PaymentInfo", "Product: " + productInfo.getProduct());
                Log.d("PaymentInfo", "Quantity: " + productInfo.getQuantity());

                Map<String, Object>dataMap = (Map<String, Object>)snapshot.getValue();
                Object data = dataMap.get(targetkey);
                Log.d("Firebase_Things", "dataname: " + data);
                Log.d("HashMap", "HashMap: " + dataMap);


                //리스트뷰에 상품 정보 및 상품 수량 표시
                if(dataMap != null){
                    if(data != null && data.toString().equals(targetValue1)){
                        ProductInfoAdapter adapter = new ProductInfoAdapter(getApplicationContext(), productInfoList);
                        productInfoView.setAdapter(adapter);
                        int price = 1200;
                        productInfoList.add(new ProductInfo(R.drawable.apple, productInfo.getProduct(), price, productInfo.getQuantity()));
                        sumprice += price * productInfo.getQuantity();
                    }
                    if(data != null && data.toString().equals(targetValue2)){
                        ProductInfoAdapter adapter = new ProductInfoAdapter(getApplicationContext(), productInfoList);
                        productInfoView.setAdapter(adapter);
                        int price = 2340;
                        productInfoList.add(new ProductInfo(R.drawable.banana, productInfo.getProduct(), productInfo.getPrice(), productInfo.getQuantity()));
                        sumprice += price * productInfo.getQuantity();
                    }
                    if(data != null && data.toString().equals(targetValue3)){
                        ProductInfoAdapter adapter = new ProductInfoAdapter(getApplicationContext(), productInfoList);
                        productInfoView.setAdapter(adapter);
                        int price = 3000;
                        productInfoList.add(new ProductInfo(R.drawable.eggplant, productInfo.getProduct(), productInfo.getPrice(), productInfo.getQuantity()));
                        sumprice += price * productInfo.getQuantity();
                    }
                    if(data != null && data.toString().equals(targetValue4)){
                        ProductInfoAdapter adapter = new ProductInfoAdapter(getApplicationContext(), productInfoList);
                        productInfoView.setAdapter(adapter);
                        int price = 2400;
                        productInfoList.add(new ProductInfo(R.drawable.snack, productInfo.getProduct(), productInfo.getPrice(), productInfo.getQuantity()));
                        sumprice += price * productInfo.getQuantity();
                    }
                    if(data != null && data.toString().equals(targetValue5)){
                        ProductInfoAdapter adapter = new ProductInfoAdapter(getApplicationContext(), productInfoList);
                        productInfoView.setAdapter(adapter);
                        int price = 4500;
                        productInfoList.add(new ProductInfo(R.drawable.fish, productInfo.getProduct(), productInfo.getPrice(), productInfo.getQuantity()));
                        sumprice += price * productInfo.getQuantity();
                    }
                    if(data != null && data.toString().equals(targetValue6)){
                        ProductInfoAdapter adapter = new ProductInfoAdapter(getApplicationContext(), productInfoList);
                        productInfoView.setAdapter(adapter);
                        int price = 3200;
                        productInfoList.add(new ProductInfo(R.drawable.grape, productInfo.getProduct(), productInfo.getPrice(), productInfo.getQuantity()));
                        sumprice += price * productInfo.getQuantity();
                    }
                    if(data != null && data.toString().equals(targetValue7)){
                        ProductInfoAdapter adapter = new ProductInfoAdapter(getApplicationContext(), productInfoList);
                        productInfoView.setAdapter(adapter);
                        int price = 2300;
                        productInfoList.add(new ProductInfo(R.drawable.garlic, productInfo.getProduct(), productInfo.getPrice(), productInfo.getQuantity()));
                        sumprice += price * productInfo.getQuantity();
                    }
                    if(data != null && data.toString().equals(targetValue8)){
                        ProductInfoAdapter adapter = new ProductInfoAdapter(getApplicationContext(), productInfoList);
                        productInfoView.setAdapter(adapter);
                        int price = 2700;
                        productInfoList.add(new ProductInfo(R.drawable.onion, productInfo.getProduct(), productInfo.getPrice(), productInfo.getQuantity()));
                        sumprice += price * productInfo.getQuantity();
                    }
                    if(data != null && data.toString().equals(targetValue9)){
                        ProductInfoAdapter adapter = new ProductInfoAdapter(getApplicationContext(), productInfoList);
                        productInfoView.setAdapter(adapter);
                        int price = 7500;
                        productInfoList.add(new ProductInfo(R.drawable.peanut_butter, productInfo.getProduct(), productInfo.getPrice(), productInfo.getQuantity()));
                        sumprice += price * productInfo.getQuantity();
                    }
                    if(data != null && data.toString().equals(targetValue10)){
                        ProductInfoAdapter adapter = new ProductInfoAdapter(getApplicationContext(), productInfoList);
                        productInfoView.setAdapter(adapter);
                        int price = 2100;
                        productInfoList.add(new ProductInfo(R.drawable.softdrink, productInfo.getProduct(), productInfo.getPrice(), productInfo.getQuantity()));
                        sumprice += price * productInfo.getQuantity();
                    }
                }

                //총합 구하기
                if(productInfo != null){
                    sumquantity += productInfo.getQuantity();
                    sum_quantity.setText(String.format(Locale.getDefault(), "%d", sumquantity));
                }

                //총액 구하기
                sum_price.setText(String.format(Locale.getDefault(),"%d", sumprice));

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ProductInfo", "Error: " + error.getCode());
            }
        });

        //결제완료 화면으로 넘어가기
        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),thankyouActivity.class);
                startActivity(intent);
            }
        });

    }

    //현재 로컬 DB와 firebase DB에서의 데이터 동일.
    private void setUpData(){

        Things apple = new Things("0", "사과", "1200원", "2kg", R.drawable.apple);

        Things banana = new Things("1","바나나", "2340원", "6kg", R.drawable.banana);

        Things eggplant = new Things("2","가지", "3000원", "4kg", R.drawable.eggplant);

        Things snack = new Things("3", "과자(간식)", "2400원", "1kg", R.drawable.snack);

        Things fish = new Things("4", "생선", "4500원", "3kg", R.drawable.fish);

        Things grape = new Things("5", "포도", "3200원", "2kg", R.drawable.grape);

        Things garlic = new Things("6", "마늘","2300원", "3kg", R.drawable.garlic);

        Things onion = new Things("7", "양파", "2700원", "3kg", R.drawable.onion);

        Things peanutbutter = new Things("8", "땅콩버터", "7500원", "5kg", R.drawable.peanut_butter);

        Things softdrink = new Things("9", "탄산음료", "2100원", "2kg", R.drawable.softdrink);

    }

}
