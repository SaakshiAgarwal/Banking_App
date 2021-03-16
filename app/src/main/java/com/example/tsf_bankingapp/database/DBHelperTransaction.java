package com.example.tsf_bankingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperTransaction extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="TransactionDetails.db";
    public static final String TABLE_NAME ="TransactionDetails";
    public static final String COL1 ="FROMUSER";
    public static final String COL2 ="TOUSER";
    public static final String COL3 ="AMOUNT";


    public DBHelperTransaction(Context context){
        super(context,DATABASE_NAME, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE "+TABLE_NAME+"( FROMUSER TEXT, TOUSER TEXT,AMOUNT INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertTransaction(String from, String to, int amount){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1,from);
        contentValues.put(COL2,to);
        contentValues.put(COL3,amount);
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
//        contentValues.put(COL3, to_balance);

        db.update(TABLE_NAME,contentValues,"NAME =?",new String[] { name });

        return true;
    }

}
