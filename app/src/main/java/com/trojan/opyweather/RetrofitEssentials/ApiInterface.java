package com.trojan.opyweather.RetrofitEssentials;

import com.trojan.opyweather.Model.WeatherDetails;
import com.trojan.opyweather.Model.WeatherForcastDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather")
    Call<WeatherDetails> getWeather(
            @Query("q") String q,
            @Query("appid") String appid,
            @Query("units") String units
    );

    @GET("weather")
    Call<WeatherDetails> getWeatherWithLocation(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("appid") String appid,
            @Query("units") String units
    );

    @GET("forecast")
    Call<WeatherForcastDetails> getForcast(
            @Query("q") String q,
            @Query("appid") String appid,
            @Query("units") String units
    );

    @GET("forecast")
    Call<WeatherForcastDetails> getForcastWithLocation(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("appid") String appid,
            @Query("units") String units
    );

}
