package com.example.jegge.test2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Jegge on 2016-11-28.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Database.db";
    protected static final String FIRST_TABLE_NAME = "golfhit_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "CLUB";
    public static final String COL_3 = "DISTANCE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + FIRST_TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, CLUB STRING, DISTANCE STRING);");
        //db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //THIS WILL BE EXECUTED WHEN YOU UPDATED VERSION OF DATABASE_VERSION
        //YOUR DROP AND CREATE QUERIES
        db.execSQL("DROP TABLE IF EXISTS " +FIRST_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String club, String distance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,club);
        contentValues.put(COL_3,distance);

        long result = db.insert(FIRST_TABLE_NAME,null,contentValues);
        if (result == -1)
        {
            return false;
        }
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + FIRST_TABLE_NAME,null);
        return res;
    }

    public ArrayList<String> queryXData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> xNewData = new ArrayList<String>();
        String query = "SELECT " + COL_2 + " FROM " + FIRST_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            xNewData.add(cursor.getString(cursor.getColumnIndex(COL_2)));
        }
        cursor.close();
        return xNewData;
    }

    public ArrayList<Float> queryYData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Float> yNewData = new ArrayList<Float>();
        String query = "SELECT " + COL_3 + " FROM " + FIRST_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            yNewData.add(cursor.getFloat(cursor.getColumnIndex(COL_3)));
        }
        cursor.close();
        return yNewData;
    }
}