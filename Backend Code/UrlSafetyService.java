<<<<<<< HEAD
package com.example.virus_detection_application;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UrlSafetyService {
    @GET("url/report")
    Call<UrlScanResult> scanUrl(@Query("apikey") String apiKey, @Query("resource") String url);
=======
package com.example.virus_detection_application;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UrlSafetyService {
    @GET("url/report")
    Call<UrlScanResult> scanUrl(@Query("apikey") String apiKey, @Query("resource") String url);
>>>>>>> 3ca3b248c43f2f4a683cb075aa0866f78c5dd616
}