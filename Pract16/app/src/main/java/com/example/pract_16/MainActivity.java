package com.example.pract_16;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView dateText,timeText;
    Button dateButton,timeButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);

        dateButton = findViewById(R.id.dateButton);
        timeButton = findViewById(R.id.timeButton);

        dateButton.setOnClickListener(v->{
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,(view,year1,month1,dayOfMonth)->
                    dateText.setText(dayOfMonth+"/"+(month1+1)+"/"+year1),year,month,day
                    );
            datePickerDialog.show();
        });

        timeButton.setOnClickListener(v->{
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,(view,hourOfDay,minute1)->
                    timeText.setText(hourOfDay+":"+minute1),hour,minute,true);
            timePickerDialog.show();
        });

    }
}