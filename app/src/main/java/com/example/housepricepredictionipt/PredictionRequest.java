package com.example.housepricepredictionipt;

import com.google.gson.annotations.SerializedName;

public class PredictionRequest {

    @SerializedName("area")    // Annotates the field to match the JSON key
    private float area;

    @SerializedName("bedrooms")  // Annotates the field to match the JSON key
    private int bedrooms;

    @SerializedName("bathrooms")  // Annotates the field to match the JSON key
    private int bathrooms;

    // Constructor
    public PredictionRequest(float area, int bedrooms, int bathrooms) {
        this.area = area;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
    }

    // Getters
    public float getArea() {
        return area;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    // Optional: Setters if you need to set values later (for example, if you use Gson to deserialize)
    public void setArea(float area) {
        this.area = area;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }
}
