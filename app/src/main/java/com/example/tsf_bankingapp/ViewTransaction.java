package com.example.tsf_bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tsf_bankingapp.adapters.MainAdapterTransaction;
import com.example.tsf_bankingapp.database.DBHelperTransaction;
import com.example.tsf_bankingapp.models.ModelTransaction;

import java.util.ArrayList;
import java.util.List;

public class ViewTransaction extends AppCompatActivity {

    DBHelperTransaction myDB;
    List<ModelTransaction> dataList = new ArrayList<>();

    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    MainAdapterTransaction adapter;
    LinearLayout linearLayout;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction);

        recyclerView = findViewById(R.id.recyclerview);
        linearLayout = findViewById(R.id.layout_heading);
        textView = findViewById(R.id.notransaction);
        myDB = new DBHelperTransaction(ViewTransaction.this);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        viewAll();

        if(dataList.size() == 0){
            linearLayout.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
        else {
            linearLayout.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            linearLayoutManager = new LinearLayoutManager(this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewTransaction.this);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new MainAdapterTransaction(this, dataList);
            recyclerView.setAdapter(adapter);
        }

    }

    public void viewAll(){

        Cursor cursor = myDB.getUserData();

        if(cursor.getCount() == 0)
            return;

        while (cursor.moveToNext()) {

            String from = cursor.getString(0);
            String to = cursor.getString(1);
            int balance = cursor.getInt(2);

            ModelTransaction model = new ModelTransaction(from, to, balance);
            dataList.add(model);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*overridePendingTransition(R.anim.fixed, R.anim.normaltobottom);*/
        finish();
    }
}