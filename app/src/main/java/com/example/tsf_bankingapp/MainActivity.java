package com.example.tsf_bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tsf_bankingapp.adapters.MainAdapter;
import com.example.tsf_bankingapp.database.DBHelper;
import com.example.tsf_bankingapp.models.Model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper myDB;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    MainAdapter adapter;
    Button viewTransaction;
    List<Model> dataList = new ArrayList<>();
    SharedPreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        viewTransaction = findViewById(R.id.buttonview);

        myDB = new DBHelper(MainActivity.this);
        preferenceManager = new SharedPreferenceManager(MainActivity.this);

        if(preferenceManager.isFirstTimeLaunch()){
            addUserData();
            viewAll();
        }

        viewTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewTransaction.class));
                /*overridePendingTransition(R.anim.toptonormal, R.anim.fixed);*/
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MainAdapter(this,dataList);
        recyclerView.setAdapter(adapter);

    }

    public void addUserData(){

        myDB.insertUserData("Saakshi Agarwal","saakshiagarwal06@gmail.com", 10000,"XXXX XXXX XXXX 6061");
        myDB.insertUserData("Muskan Goel","muskangoel087@gmail.com", 10000,"XXXX XXXX XXXX 6756");
        myDB.insertUserData("Neha Jain","nehajain095@gmail.com", 10000,"XXXX XXXX XXXX 8779" );
        myDB.insertUserData("Priya Verma","priyaverma099@gmail.com", 10000 ,"XXXX XXXX XXXX 5876");
        myDB.insertUserData("Ishita Beriwal","ishitaberiwal077@gmail.com", 10000,"XXXX XXXX XXXX 2336");
        myDB.insertUserData("Reyansh Agarwal","reyanshagarwal436@gmail.com", 10000,"XXXX XXXX XXXX 2657");
        myDB.insertUserData("Khushi Agarwal","khushiagarwal27@gmail.com", 10000,"XXXX XXXX XXXX 9876" );
        myDB.insertUserData("Pooja Goel","poojagoel123@gmail.com", 10000,"XXXX XXXX XXXX 6364");
        myDB.insertUserData("Sonia Gupta","soniagupta56@yahoo.com", 10000,"XXXX XXXX XXXX 8688");
        myDB.insertUserData("Ankit Agarwal","ankitagarwal56@gmail.com", 10000, "XXXX XXXX XXXX 1256");

    }

    public void viewAll(){

        Cursor cursor = myDB.getUserData();

        if(cursor.getCount() == 0)
            return;

        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String email = cursor.getString(1);
            int balance = cursor.getInt(2);
            String accnumber = cursor.getString(3);

            Model model = new Model(name, email, balance, accnumber);
            dataList.add(model);
        }
    }

}