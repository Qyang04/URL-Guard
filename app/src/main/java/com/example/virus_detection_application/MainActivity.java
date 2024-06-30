package com.example.virus_detection_application;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private EditText urlInput;
    private Button checkButton;
    private RequestQueue requestQueue;
    private final String API_KEY = "4c6c6e545f2ef1bcd1cb0dac27984ee059ca55c41a9f0653c3c723b54119b2c6"; // Replace with your VirusTotal API key
    private final String BASE_URL = "https://www.virustotal.com/vtapi/v2/url/report";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlInput = findViewById(R.id.urlInput);
        checkButton = findViewById(R.id.checkButton);
        ImageButton homeButton = findViewById(R.id.imageButton);

        requestQueue = Volley.newRequestQueue(this);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlInput.getText().toString();
                checkURL(url);
            }
        });

        //function of home button
        homeButton.setOnClickListener(view -> {
            // Create AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure you want to exit the application?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Exit the application
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Dismiss the dialog
                            dialog.dismiss();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });



    }

    private void checkURL(String url) {
        String apiUrl = BASE_URL + "?apikey=" + API_KEY + "&resource=" + url;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, apiUrl, null,
                response -> handleResponse(response),
                error -> {
                    Toast.makeText(MainActivity.this, "Error: Cannot retrieve null value" , Toast.LENGTH_SHORT).show();
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    private void handleResponse(JSONObject response) {
        try {
            int positives = response.getInt("positives");
            int total = response.getInt("total");

            StringBuilder result = new StringBuilder();

            if(positives > 0){
                result.append("This URL is detected as malicious by ")
                        .append(positives).append(" out of ").append(total).append(" security vendors flagged this URL.\n\n")
                        .append("Details:\n");
            } else {
                result.append("This URL is detected as secure by ")
                        .append(positives).append(" out of ").append(total).append(" security vendors flagged this URL.\n\n")
                        .append("Details:\n");
            }


            JSONObject scans = response.getJSONObject("scans");
            Iterator<String> keys = scans.keys();

            while (keys.hasNext()) {
                String vendor = keys.next();
                JSONObject vendorResult = scans.getJSONObject(vendor);
                boolean detected = vendorResult.getBoolean("detected");
                String detectionResult = vendorResult.has("result") ? vendorResult.getString("result") : "No result";

                result.append("Vendor: ").append(vendor).append("\n")
                        .append("Detected: ").append(detected).append("\n")
                        .append("Result: ").append(detectionResult).append("\n\n");
            }

            // Start ResultActivity and pass the result data
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("result", result.toString());
            startActivity(intent);

        } catch (JSONException e) {
            Toast.makeText(MainActivity.this, "Error parsing response.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        // Handle back button press
        // Show AlertDialog similar to the home button press
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure you want to exit the Tips Calculator application?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Exit the application
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Call super method to preserve default behavior
        super.onBackPressed();
    }
}
