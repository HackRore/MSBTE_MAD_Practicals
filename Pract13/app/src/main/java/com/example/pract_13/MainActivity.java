package com.example.pract_13;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button startButton;
    private TextView progressText;
    private int progressStatus = 0;
    private Handler handler =new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        progressBar =findViewById(R.id.progressBar);
        startButton = findViewById(R.id.startButton);
        progressText = findViewById(R.id.progressText);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressStatus = 0;
                progressBar.setProgress(progressStatus);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progressStatus < 100){
                            progressStatus += 1;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progressStatus);
                                    progressText.setText(progressStatus + " % ");
                                }
                            });

                            try {
                                Thread.sleep(100);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

    }
}