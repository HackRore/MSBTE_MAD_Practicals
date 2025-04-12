package com.example.pract_19_contentconsumerapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        String URL = "content://com.example.pract_19_contentproviderapp.provider/students";
        Uri students = Uri.parse(URL);
        
        Cursor c = getContentResolver().query(students, null, null, null, null);
        StringBuilder result = new StringBuilder("Content Provider Data: \n\n");
        
        if (c != null && c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex("name"));
                result.append(name).append("\n");
            } while (c.moveToNext());
            c.close();
        } else {
            result.append("No data available");
        }
        
        textView.setText(result.toString());
    }
}