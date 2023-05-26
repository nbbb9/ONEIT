package com.myself.supercart;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class test1Activity extends AppCompatActivity implements View.OnClickListener{

    Button btn_Update, btn_Insert, btn_Select;

    EditText edit_NAME, edit_PRICE, edit_WEIGHT;

    TextView text_NAME;
    TextView text_PRICE;
    TextView text_WEIGHT;

    CheckBox check_NAME;
    CheckBox check_PRICE;
    CheckBox check_WEIGHT;

    long nowIndex;

    ListView listView;

    String Name;
    long Price;
    long Weight;

    String sort = "name";

    ArrayAdapter<String> arrayAdapter;

    static ArrayList<String> arrayIndex =  new ArrayList<String>();
    static ArrayList<String> arrayData = new ArrayList<String>();
    private DBOpenHelper mDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        btn_Insert = findViewById(R.id.btn_insert);
        btn_Insert.setOnClickListener(this);

        btn_Update = findViewById(R.id.btn_update);
        btn_Update.setOnClickListener(this);

        btn_Select = findViewById(R.id.btn_select);
        btn_Select.setOnClickListener(this);

        edit_NAME = findViewById(R.id.edit_name);
        edit_PRICE = findViewById(R.id.edit_price);
        edit_WEIGHT = findViewById(R.id.edit_weight);

        text_NAME = findViewById(R.id.text_name);
        text_PRICE = findViewById(R.id.text_price);
        text_WEIGHT = findViewById(R.id.text_weight);

        check_NAME = findViewById(R.id.check_name);
        check_NAME.setOnClickListener(this);
        check_PRICE = findViewById(R.id.check_price);
        check_PRICE.setOnClickListener(this);
        check_WEIGHT = findViewById(R.id.check_weight);
        check_WEIGHT.setOnClickListener(this);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView = findViewById(R.id.things_listView);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(onClickListener);
        listView.setOnItemLongClickListener(longClickListener);

        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();

        check_NAME.setChecked(true);
        showDatabase(sort);

        btn_Insert.setEnabled(true);
        btn_Update.setEnabled(false);

    }

    public void setInsertMode(){
        edit_NAME.setText("");
        edit_PRICE.setText("");
        edit_WEIGHT.setText("");
        btn_Insert.setEnabled(true);
        btn_Update.setEnabled(false);
    }

    private final AdapterView.OnItemClickListener onClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e("On Click", "position = " + position);
            nowIndex = Long.parseLong(arrayIndex.get(position));
            Log.e("On Click", "nowIndex = " + nowIndex);
            Log.e("On Click", "Data: " + arrayData.get(position));
            String[] tempData = arrayData.get(position).split("\\s+");
            Log.e("On Click", "Split Result = " + Arrays.toString(tempData));

            edit_NAME.setText(tempData[0].trim());
            edit_PRICE.setText(tempData[1].trim());
            edit_WEIGHT.setText(tempData[2].trim());

            btn_Insert.setEnabled(false);
            btn_Update.setEnabled(true);
        }
    };

    private final AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("Long Click", "position = " + position);

            nowIndex = Long.parseLong(arrayIndex.get(position));

            String[] nowData = arrayData.get(position).split("\\s+");

            String viewData = nowData[0] + ", " + nowData[1] + ", " + nowData[2];

            AlertDialog.Builder dialog = new AlertDialog.Builder(test1Activity.this);

            dialog.setTitle("데이터 삭제")
                    .setMessage("해당 데이터를 삭제 하시겠습니까?" + "\n" + viewData)
                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(test1Activity.this, "데이터를 삭제했습니다.", Toast.LENGTH_SHORT).show();
                            mDbOpenHelper.deleteColumn(nowIndex);
                            showDatabase(sort);
                            setInsertMode();
                        }
                    })
                    .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(test1Activity.this, "삭제를 취소했습니다.", Toast.LENGTH_SHORT).show();
                            setInsertMode();
                        }
                    })
                    .create()
                    .show();
            return false;
        }
    };

    public void showDatabase(String sort){
        Cursor iCursor = mDbOpenHelper.sortColumn(sort);
        Log.d("showDatabase", "DB Size: " + iCursor.getCount());
        arrayData.clear();
        arrayIndex.clear();
        while(iCursor.moveToNext()){
            @SuppressLint("Range") String tempIndex = iCursor.getString(iCursor.getColumnIndex("_id"));

            @SuppressLint("Range") String tempName = iCursor.getString(iCursor.getColumnIndex("name"));
            tempName = setTextLength(tempName,10);

            @SuppressLint("Range") String tempPrice = iCursor.getString(iCursor.getColumnIndex("price"));
            tempPrice = setTextLength(tempPrice,10);

            @SuppressLint("Range") String tempWeight = iCursor.getString(iCursor.getColumnIndex("weight"));
            tempWeight = setTextLength(tempWeight,10);

            String Result = tempName + tempPrice + tempWeight;
            arrayData.add(Result);
            arrayIndex.add(tempIndex);
        }
        arrayAdapter.clear();
        arrayAdapter.addAll(arrayData);
        arrayAdapter.notifyDataSetChanged();
    }

    public String setTextLength(String text, int length){
        if(text.length()<length){
            int gap = length - text.length();
            for (int i=0; i<gap; i++){
                text = text + " ";
            }
        }
        return text;
    }

    @Override
    public void onClick(View view){
        switch(view.getId()) {
            case R.id.btn_insert:
                Name = edit_NAME.getText().toString();
                Price = Long.parseLong(edit_PRICE.getText().toString());
                Weight = Long.parseLong(edit_WEIGHT.getText().toString());

                mDbOpenHelper.open();
                mDbOpenHelper.insertColumn(Name, Price, Weight);

                showDatabase(sort);
                setInsertMode();
                edit_NAME.requestFocus();
                edit_NAME.setCursorVisible(true);
                break;

            case R.id.btn_update:
                Name = edit_NAME.getText().toString();
                Price = Long.parseLong(edit_PRICE.getText().toString());
                Weight = Long.parseLong(edit_WEIGHT.getText().toString());
                mDbOpenHelper.updateColumn(nowIndex,Name, Price, Weight);
                showDatabase(sort);
                setInsertMode();
                edit_NAME.requestFocus();
                edit_NAME.setCursorVisible(true);
                break;

            case R.id.btn_select:
                showDatabase(sort);
                break;

            case R.id.check_name:
                check_PRICE.setChecked(false);
                check_WEIGHT.setChecked(false);
                sort = "name";
                break;

            case R.id.check_price:
                check_NAME.setChecked(false);
                check_WEIGHT.setChecked(false);
                sort = "price";
                break;

            case R.id.check_weight:
                check_NAME.setChecked(false);
                check_PRICE.setChecked(false);
                sort = "weight";
                break;
        }
    }

}