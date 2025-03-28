package com.example.pract_11;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    CheckBox cbReading,cbTravelling,cbGaming,cbBlogging,cbTracking;
    Button submit;
    TextView result;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        cbReading = findViewById(R.id.checkboxReading);
        cbTravelling = findViewById(R.id.checkboxTravelling);
        cbBlogging = findViewById(R.id.checkboxBlogging);
        cbGaming = findViewById(R.id.checkboxGaming);
        cbTracking = findViewById(R.id.checkboxTracker);

        submit = findViewById(R.id.btnSubmit);
        result = findViewById(R.id.textViewResult);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder res = new StringBuilder("Selected Hobbies:");
                if (cbReading.isChecked()){
                    res.append("\nReading");
                }
                if (cbBlogging.isChecked()){
                    res.append("\nBlogging");
                }
                if (cbGaming.isChecked()){
                    res.append("\nGaming");
                }
                if (cbTracking.isChecked()){
                    res.append("\nTracking");
                }
                if (cbTravelling.isChecked()){
                    res.append("\nTravelling");
                }

                if(!cbTravelling.isChecked() && !cbGaming.isChecked() && ! cbBlogging.isChecked() &&
                ! cbTravelling.isChecked() && !cbReading.isChecked()){
                    res.append("No Hobbies Selected....");
                }

                result.setText(res.toString());
            }
        });

    }



}