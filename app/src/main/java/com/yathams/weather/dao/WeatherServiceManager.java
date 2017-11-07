package com.yathams.weather.dao;

import android.util.Log;

import com.yathams.weather.WeatherService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by reddve5 on 11/6/17.
 */

public class WeatherServiceManager {
    private static final String TAG = "WeatherServiceManager";
    private static WeatherServiceManager serviceManager;
    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private final String APP_ID = "c5847d937c149b421a19fb22856edd61";
    private final String KEY_APP_ID = "appid";
    private final String KEY_Q = "q";
    private final String KEY_UNITS = "units";
    private final String UNIT_IMPERIAL = "imperial";
    private final String UNIT_METRIC = "metric";

    private WeatherService weatherService;

    private WeatherServiceManager() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherService = retrofit.create(WeatherService.class);

    }

    public static WeatherServiceManager getInstance() {
        if (serviceManager == null) {
            serviceManager = new WeatherServiceManager();
        }
        return serviceManager;
    }

    public void getCurranetWeather(String cityName, Callback<WeatherResponse> callback) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(KEY_APP_ID, APP_ID);
        queryMap.put(KEY_UNITS, UNIT_IMPERIAL);
        queryMap.put(KEY_Q, cityName);
        Call<WeatherResponse> weatherResponseCall = weatherService.getCurrentWeather(queryMap);
        weatherResponseCall.enqueue(callback);
    }
}
