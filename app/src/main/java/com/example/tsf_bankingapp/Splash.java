package com.example.tsf_bankingapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    private static long Splash_time_out = 2200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferenceManager sharedPref = new SharedPreferenceManager(Splash.this);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent homeIntent = new Intent(Splash.this, MainActivity.class);
                    startActivity(homeIntent);
                    overridePendingTransition(R.anim.fadeout,R.anim.fadein);
                    finish();
                }
            },Splash_time_out);


    }


}