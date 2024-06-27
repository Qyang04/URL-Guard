package com.example.virus_detection_application;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText urlInput;
    private Button checkButton;
    private TextView resultTextView;
    private RequestQueue requestQueue;
    private final String API_KEY = "4c6c6e545f2ef1bcd1cb0dac27984ee059ca55c41a9f0653c3c723b54119b2c6"; // Replace with your VirusTotal API key
    private final String BASE_URL = "https://www.virustotal.com/vtapi/v2/url/report";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlInput = findViewById(R.id.txtAmount);
        checkButton = findViewById(R.id.button);
        resultTextView = findViewById(R.id.scan_result);

        requestQueue = Volley.newRequestQueue(this);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlInput.getText().toString();
                checkURL(url);
            }
        });
    }

    private void checkURL(String url) {
        String apiUrl = BASE_URL + "?apikey=" + API_KEY + "&resource=" + url;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, apiUrl, null,
                response -> handleResponse(response),
                error -> resultTextView.setText("Error: " + error.getMessage())
        );

        requestQueue.add(jsonObjectRequest);
    }

    private void handleResponse(JSONObject response) {
        try {
            int positives = response.getInt("positives");
            int total = response.getInt("total");

            if (positives > 0) {
                resultTextView.setText("Warning: The URL is malicious!\n" + positives + " out of " + total + " security vendors flagged this URL.");
            } else {
                resultTextView.setText("The URL is clean.");
            }
        } catch (Exception e) {
            resultTextView.setText("Error parsing response.");
        }
    }
}