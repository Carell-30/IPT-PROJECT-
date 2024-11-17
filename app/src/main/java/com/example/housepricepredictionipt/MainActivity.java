package com.example.housepricepredictionipt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        EditText areaEditText = findViewById(R.id.areaEditText);
        EditText bedroomsEditText = findViewById(R.id.bedroomsEditText);
        EditText bathroomsEditText = findViewById(R.id.bathroomsEditText);
        resultTextView = findViewById(R.id.resultTextView);
        Button predictButton = findViewById(R.id.predictButton);

        // Set click listener for the predict button
        predictButton.setOnClickListener(v -> {
            // Get user input from EditTexts
            String areaText = areaEditText.getText().toString().trim();
            String bedroomsText = bedroomsEditText.getText().toString().trim();
            String bathroomsText = bathroomsEditText.getText().toString().trim();

            // Validate input
            if (areaText.isEmpty() || bedroomsText.isEmpty() || bathroomsText.isEmpty()) {
                Toast.makeText(MainActivity.this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
                return;
            }

            // Parse inputs to appropriate data types with error handling
            float area = 0;
            int bedrooms = 0, bathrooms = 0;
            try {
                area = Float.parseFloat(areaText);
                bedrooms = Integer.parseInt(bedroomsText);
                bathrooms = Integer.parseInt(bathroomsText);
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                return;
            }

            // Create PredictionRequest object with input values
            PredictionRequest request = new PredictionRequest(area, bedrooms, bathrooms);

            // Make the API call
            RetrofitClient.getClient().create(ApiService.class)
                    .getPrediction(request)
                    .enqueue(new Callback<PredictionResponse>() {
                        @Override
                        public void onResponse(Call<PredictionResponse> call, Response<PredictionResponse> response) {
                            if (response.isSuccessful()) {
                                PredictionResponse predictionResponse = response.body();
                                if (predictionResponse != null) {
                                    // Display the predicted price using the string resource
                                    double predictedPrice = predictionResponse.getPredictedPrice();
                                    resultTextView.setText(getString(R.string.predicted_price, predictedPrice));
                                } else {
                                    resultTextView.setText(R.string.error_no_response);
                                }
                            } else {
                                // Handle unsuccessful response
                                resultTextView.setText(getString(R.string.error_unable_to_get_prediction, response.code()));
                            }
                        }

                        @Override
                        public void onFailure(Call<PredictionResponse> call, Throwable t) {
                            // Handle failure
                            resultTextView.setText(getString(R.string.error_message, t.getMessage()));
                        }
                    });
        });
    }
}
