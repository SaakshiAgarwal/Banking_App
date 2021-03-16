package com.example.tsf_bankingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="UserDetails.db";
    public static final String TABLE_NAME ="UserDetails";
    public static final String COL1 ="Name";
    public static final String COL2 ="Email";
    public static final String COL3 ="Balance";
    public static final String COL4 = "AccountNumber";


    public DBHelper(Context context){
        super(context,DATABASE_NAME, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE "+TABLE_NAME+"(NAME TEXT primary key, EMAIL TEXT,BALANCE INTEGER,ACCOUNTNUMBER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUserData(String name, String email, int balance, String accnumber){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1,name);
        contentValues.put(COL2,email);
        contentValues.put(COL3,balance);
        contentValues.put(COL4, accnumber);
        long res = db.insert(TABLE_NAME, null, contentValues);

        return res != -1;
    }

    public Cursor getUserData(){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);

    }

    public boolean updateBalance(String name,int balance){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3, balance);

        db.update(TABLE_NAME,contentValues,"NAME =?",new String[] { name });

        return true;
    }

}
