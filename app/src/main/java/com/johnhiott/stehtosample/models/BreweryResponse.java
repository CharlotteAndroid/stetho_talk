package com.johnhiott.stehtosample.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BreweryResponse {

    @SerializedName("message")String mMessage;
    @SerializedName("data")ArrayList<Beer> mBeers;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public ArrayList<Beer> getBeers() {
        return mBeers;
    }

}
