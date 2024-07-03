package com.example.virus_detection_application;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTextView = findViewById(R.id.resultTextView);
        ImageButton backButton = findViewById(R.id.imageButton1);

        String result = getIntent().getStringExtra("result");

        resultTextView.setText(result);

        backButton.setOnClickListener(view -> {

            finish();

        });
    }
}