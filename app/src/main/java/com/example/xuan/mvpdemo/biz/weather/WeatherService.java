package com.example.xuan.mvpdemo.biz.weather;

import com.example.xuan.mvpdemo.bean.City;
import com.example.xuan.mvpdemo.bean.WeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author : xuan.
 * Data : 2017/7/21.
 * Description :input the description of this file.
 */

public interface WeatherService {
    @GET("/open/api/weather/json.shtml?")
    Call<WeatherInfo> getWeatherInfo(@Query("city") String city);
}
