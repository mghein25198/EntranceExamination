package com.mghein.entranceexamination.model;

import com.google.gson.annotations.SerializedName;

public class DataResponse {
    @SerializedName("rates")
    private Rate rate;
    @SerializedName("info")
    private String  info;
    @SerializedName("description")
    private String  description;
    @SerializedName("timestamp")
    private String  timestamp;

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
