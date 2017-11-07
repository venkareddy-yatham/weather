package com.yathams.weather.dao;

import com.yathams.weather.dao.WeatherResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by reddve5 on 11/6/17.
 */

public interface WeatherService {

    @GET("weather")
    Call<WeatherResponse> getCurrentWeather(@QueryMap Map<String, String> options);
}
