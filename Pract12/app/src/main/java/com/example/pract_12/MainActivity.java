package com.example.pract_12;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button submit;
    TextView result;
    RadioGroup radioGroup;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.btnSubmit);
        result = findViewById(R.id.txtResult);
        radioGroup = findViewById(R.id.radioGroup);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedID = radioGroup.getCheckedRadioButtonId();

                if (selectedID == -1){
                    Toast.makeText(MainActivity.this,"Please Select a Playlist",Toast.LENGTH_LONG).show();
                }
                else{
                    RadioButton selectedRadioButton = findViewById(selectedID);
                    String selectedText = selectedRadioButton.getText().toString();
                    result.setText("Selected: "+selectedText);
                }
            }
        });

    }
}