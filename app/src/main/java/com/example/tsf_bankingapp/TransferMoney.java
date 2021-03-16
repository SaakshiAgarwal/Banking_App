package com.example.tsf_bankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tsf_bankingapp.database.DBHelper;
import com.example.tsf_bankingapp.database.DBHelperTransaction;
import com.example.tsf_bankingapp.models.Model;

import java.util.ArrayList;
import java.util.List;

public class TransferMoney extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String from_name, from_email, from_accnumber;
    String to_name, to_email, samount="", to_accnumber;
    int to_balance, from_balance;
    TextView tfrom, tfrombalance, tfromaccnumber, ttoaccnumber;
    EditText etamount;
    Button button;
    Spinner spinner;
    List<Model> list = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    List<Integer> balanceList = new ArrayList<>();
    List<String> emailList = new ArrayList<>();
    List<String> accnumberList = new ArrayList<>();

    List<String> allnameList = new ArrayList<>();
    List<String> allaccnumberList = new ArrayList<>();
    List<Integer> allbalanceList = new ArrayList<>();
    List<String> allemailList = new ArrayList<>();

    DBHelper myDB;
    DBHelperTransaction myDBTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);

        tfrom = findViewById(R.id.from);
        tfrombalance = findViewById(R.id.from_balance);
        etamount = findViewById(R.id.edittextamount);
        button = findViewById(R.id.button);
        spinner = (Spinner) findViewById(R.id.spinner);
        tfromaccnumber = findViewById(R.id.from_accnumber);
        ttoaccnumber = findViewById(R.id.to_accnumber);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        myDB = new DBHelper(TransferMoney.this);
        myDBTransaction = new DBHelperTransaction(TransferMoney.this);

        Intent i = getIntent();
        from_name = i.getStringExtra("name_from");
        from_balance = i.getIntExtra("balance_from",100);
        from_email = i.getStringExtra("email_from");
        from_accnumber = i.getStringExtra("accnumber_from");

        viewAll();

        for(int j=0;j<allnameList.size();j++){
//            if(!list.get(j).getName().matches(from_name))
//                nameList.add(list.get(j).getName());
            if(!allnameList.get(j).matches(from_name)){
                nameList.add(allnameList.get(j));
                emailList.add(allemailList.get(j));
                balanceList.add(allbalanceList.get(j));
                accnumberList.add(allaccnumberList.get(j));
            }
        }

        tfrom.setText(from_name);
        tfrombalance.setText(from_balance+"");
        tfromaccnumber.setText(from_accnumber);
        Log.i("HiiiiiiiiiiiFROM", from_name +"   "+from_balance);



        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,nameList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                samount = etamount.getText().toString().trim();

                if(samount == null || samount.isEmpty() || Integer.parseInt(samount)>from_balance){

                    if(samount == null || samount.isEmpty())
                    Toast.makeText(getApplicationContext(),"Amount is not specified",Toast.LENGTH_LONG).show();

                    if(Integer.parseInt(samount)>from_balance)
                        Toast.makeText(getApplicationContext(),"Insufficient balance",Toast.LENGTH_LONG).show();

                }
                else{
                    Log.i("Hiiiiiii_FROM","From final "+(from_balance - Integer.parseInt(samount))+"  "+from_name);
                    Log.i("Hiiiiiii_TO","To final "+(to_balance + Integer.parseInt(samount))+to_name);

                    myDB.updateBalance(from_name,from_balance - Integer.parseInt(samount));
                    myDB.updateBalance(to_name,to_balance + Integer.parseInt(samount));
                    myDBTransaction.insertTransaction(from_name, to_name, Integer.parseInt(samount));
                    AlertDialog alertDialog = new AlertDialog.Builder(TransferMoney.this)

                            .setIcon(android.R.drawable.ic_dialog_alert)

                            .setTitle("Do you want to continue?")
                            .setMessage("Transfer\n"+"Amount: "+samount+"\n"+"From: "+from_name+"\n"+"To: "+to_name )
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            /*Toast.makeText(getApplicationContext(), "Transfered successfully \n"+"Amount: "+samount+"\n"+" From: "+from_name+" To: "+to_name,Toast.LENGTH_LONG).show();*/
                                            Toast.makeText(getApplicationContext(),"Transaction was successful!",Toast.LENGTH_LONG).show();

                                            startActivity(new Intent(TransferMoney.this, MainActivity.class));
                                            finish();
                                        }
                                    })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //set what should happen when negative button is clicked
                                  /*  Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();*/

                                }
                            })
                            .show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        to_name = nameList.get(position);
        to_email = emailList.get(position);
        to_balance = balanceList.get(position);
        to_accnumber = accnumberList.get(position);
        ttoaccnumber.setVisibility(View.VISIBLE);
        ttoaccnumber.setText(to_accnumber);
        Log.i("HiiiiiiiiiiiTO", to_name+"   "+to_balance);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void viewAll(){
        Cursor cursor = myDB.getUserData();

        if(cursor.getCount() == 0)
            return;

        while (cursor.moveToNext()) {

            String name = cursor.getString(0);
            String email = cursor.getString(1);
            int balance = cursor.getInt(2);
            String accnum = cursor.getString(3);

//            Model model = new Model(name, email, balance);
//            list.add(model);
            allnameList.add(name);
            allbalanceList.add(balance);
            allemailList.add(email);
            allaccnumberList.add(accnum);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       /* overridePendingTransition(R.anim.fixed, R.anim.normaltobottom);*/
        finish();
    }
}