package com.example.pract_19_contentproviderapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;
import java.util.Locale;

public class MyContentProvider extends ContentProvider {
    private static final String TAG = "MyContentProvider";

    static final String PROVIDE_NAME = "com.example.pract_19_contentproviderapp.provider";
    static final String URL = "content://" + PROVIDE_NAME + "/students";
    static final Uri CONTENT_URI = Uri.parse(URL);

    private static HashMap<String,String> STUDENTS_PROJECTION_MAP = new HashMap<>();
    static final int STUDENTS = 1;
    static final UriMatcher uriMatcher;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDE_NAME,"students",STUDENTS);
        
        // Initialize projection map
        STUDENTS_PROJECTION_MAP.put(DatabaseHelper.COL_ID, DatabaseHelper.COL_ID);
        STUDENTS_PROJECTION_MAP.put(DatabaseHelper.COL_NAME, DatabaseHelper.COL_NAME);
    }

    SQLiteDatabase db;

    public boolean onCreate(){
        Log.d(TAG, "ContentProvider onCreate called");
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        db = dbHelper.getWritableDatabase();
        boolean result = (db != null);
        Log.d(TAG, "Database initialization " + (result ? "successful" : "failed"));
        return result;
    }

    public Cursor query(Uri uri, String []projection, String selection, String[] selectionArgs, String sortOrder){
        Log.d(TAG, "Query called with URI: " + uri);
        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                Log.d(TAG, "Querying students table");
                Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                Log.d(TAG, "Query returned " + cursor.getCount() + " rows");
                return cursor;
            default:
                Log.e(TAG, "Unknown URI: " + uri);
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    public String getType(Uri uri) {
        Log.d(TAG, "getType called for URI: " + uri);
        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd." + PROVIDE_NAME + ".students";
            default:
                Log.e(TAG, "Unknown URI in getType: " + uri);
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    public Uri insert(Uri uri, ContentValues values){
        Log.d(TAG, "Insert called with URI: " + uri);
        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                Log.d(TAG, "Inserting into students table");
                long rowID = db.insert(DatabaseHelper.TABLE_NAME, "", values);
                if (rowID > 0) {
                    Uri newUri = ContentUris.withAppendedId(CONTENT_URI, rowID);
                    getContext().getContentResolver().notifyChange(newUri, null);
                    Log.d(TAG, "Insert successful, new URI: " + newUri);
                    return newUri;
                }
                Log.e(TAG, "Failed to insert record");
                throw new SQLException("Failed to add a record into "+ uri);
            default:
                Log.e(TAG, "Unknown URI in insert: " + uri);
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    public int delete(Uri uri, String selection, String[] selectionArgs){
        Log.d(TAG, "Delete called with URI: " + uri);
        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                Log.d(TAG, "Deleting from students table");
                int count = db.delete(DatabaseHelper.TABLE_NAME, selection, selectionArgs);
                Log.d(TAG, "Deleted " + count + " rows");
                return count;
            default:
                Log.e(TAG, "Unknown URI in delete: " + uri);
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        Log.d(TAG, "Update called with URI: " + uri);
        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                Log.d(TAG, "Updating students table");
                int count = db.update(DatabaseHelper.TABLE_NAME, values, selection, selectionArgs);
                Log.d(TAG, "Updated " + count + " rows");
                return count;
            default:
                Log.e(TAG, "Unknown URI in update: " + uri);
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
}
