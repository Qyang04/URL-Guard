package com.example.virus_detection_application;

import com.google.gson.annotations.SerializedName;

public class UrlScanResult {
    @SerializedName("response_code")
    private int responseCode;

    @SerializedName("verbose_msg")
    private String verboseMsg;

    @SerializedName("positives")
    private int positives;

    @SerializedName("total")
    private int total;

    // Getters and setters for each field
    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getVerboseMsg() {
        return verboseMsg;
    }

    public void setVerboseMsg(String verboseMsg) {
        this.verboseMsg = verboseMsg;
    }

    public int getPositives() {
        return positives;
    }

    public void setPositives(int positives) {
        this.positives = positives;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
