package com.example.pract_8;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    String[] countries = {"India", "Indonesia", "Malesia", "Italy","Iceland", "Iran", "iraq", "Israel","Japan","jordan"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

//        Array Adapter for AutoCompleteTextView
        ArrayAdapter <String> adapter =new ArrayAdapter<>(
                this,android.R.layout.simple_dropdown_item_1line,countries
        );

        autoCompleteTextView.setAdapter(adapter);

//      for suggest text after single character type
autoCompleteTextView.setThreshold(1);


    }
}