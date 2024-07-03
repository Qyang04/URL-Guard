package com.example.virus_detection_application;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UrlSafetyService {
    @GET("url/report")
    Call<UrlScanResult> scanUrl(@Query("apikey") String apiKey, @Query("resource") String url);
}