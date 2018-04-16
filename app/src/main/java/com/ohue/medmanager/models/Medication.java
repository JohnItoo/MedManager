package com.ohue.medmanager.models;

import android.text.TextUtils;

public class Medication {

    private String name;

    private String timeToTake;

    private String uniqueKey;

    private String details;

    private String endDate;

    private String interval;

    public Medication (String name , String timeToTake , String details){
        this.name = name;
        this.timeToTake = timeToTake;
        if(TextUtils.isEmpty(details)){
            this.details = "No Additional Details";
        }
        else{
            this.details = details;
        }

    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeToTake() {
        return timeToTake;
    }

    public void setTimeToTake(String timeToTake) {
        this.timeToTake = timeToTake;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
}
