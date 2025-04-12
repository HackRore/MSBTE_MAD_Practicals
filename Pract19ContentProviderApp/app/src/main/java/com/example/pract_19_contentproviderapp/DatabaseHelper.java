package com.example.pract_19_contentproviderapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    public static final String DATABASE_NAME = "StudentDB";
    public static final String TABLE_NAME = "students";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        Log.d(TAG, "DatabaseHelper constructor called");
    }

    public void onCreate(SQLiteDatabase db){
        Log.d(TAG, "Creating database table: " + TABLE_NAME);
        try {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NAME + " TEXT);");
            Log.d(TAG, "Table created successfully");
            
            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_NAME + ") VALUES ('RAVINDRA'),('SHRADHHA'),('KISHOR');");
            Log.d(TAG, "Initial data inserted successfully");
        } catch (Exception e) {
            Log.e(TAG, "Error creating table or inserting data: " + e.getMessage());
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            Log.d(TAG, "Old table dropped successfully");
            onCreate(db);
        } catch (Exception e) {
            Log.e(TAG, "Error upgrading database: " + e.getMessage());
        }
    }
}
