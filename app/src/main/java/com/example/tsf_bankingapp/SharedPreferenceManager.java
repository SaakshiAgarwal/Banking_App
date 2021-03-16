package com.example.tsf_bankingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static final String launch_first ="TRUE";


    @SuppressLint("CommitPrefEdits")
    public SharedPreferenceManager(Context context){
        this.context = context;
        if(context != null) {
            preferences = context.getSharedPreferences("this", Context.MODE_PRIVATE);
        }
        editor = preferences.edit();
    }

    public boolean isFirstTimeLaunch(){
        return preferences.getBoolean(launch_first,true);
    }

    public void setIsFirstTimeLaunch(boolean firstTimeLaunch) {
        editor.putBoolean(launch_first,firstTimeLaunch);
        editor.commit();
    }



}
