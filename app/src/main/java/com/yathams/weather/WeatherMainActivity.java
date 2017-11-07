package com.yathams.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.squareup.picasso.Picasso;
import com.yathams.weather.dao.WeatherResponse;
import com.yathams.weather.dao.WeatherServiceManager;
import com.yathams.weather.databinding.ActivityWeatherMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherMainActivity extends AppCompatActivity {

    private ActivityWeatherMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_main);
        WeatherServiceManager weatherServiceManager = WeatherServiceManager.getInstance();

        binding.progressContainer.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        SharedPreferences sharedPreferences = getSharedPreferences("com.yathams.weather", 0);


        binding.buttonSearch.setOnClickListener(view -> { //This is Lambda expression Java 8
            //Handle the error message views
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.progressContainer.setVisibility(View.VISIBLE);
            binding.textViewErrorMessage.setVisibility(View.GONE);
            String keyWord = binding.editTextSearch.getText().toString().trim();

            if (!keyWord.isEmpty()) {
                //Hide the soft keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                weatherServiceManager.getCurrentWeather(keyWord, new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                        if (response.code() == 200) { //SUCCESS
                            WeatherResponse weatherResponse = response.body();
                            binding.setWeather(weatherResponse);
                            String uri = "http://openweathermap.org/img/w/" + weatherResponse.weather.get(0).icon + ".png";
                            Picasso.with(WeatherMainActivity.this).load(Uri.parse(uri)).into(binding.imageViewIcon);
                            binding.progressContainer.setVisibility(View.GONE);
                            sharedPreferences.edit().putString("lastSearchedCity", keyWord).apply();
                        } else { //SOMETHING WENT WRONG
                            binding.progressBar.setVisibility(View.GONE);
                            binding.textViewErrorMessage.setVisibility(View.VISIBLE);
                            binding.textViewErrorMessage.setText(response.message());
                        }

                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        binding.progressBar.setVisibility(View.GONE);
                        binding.textViewErrorMessage.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        //Getting last searched keyword
        String keyWord = sharedPreferences.getString("lastSearchedCity", "");
        if (!keyWord.isEmpty()) {
            binding.editTextSearch.setText(keyWord);
            binding.buttonSearch.performClick();
        }

    }
}
