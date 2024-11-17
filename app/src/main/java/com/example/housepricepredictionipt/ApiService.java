package com.example.housepricepredictionipt;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/predict")
    Call<PredictionResponse> getPrediction(@Body PredictionRequest request);
}