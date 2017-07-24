package com.example.xuan.mvpdemo.biz.weather;

import com.example.xuan.mvpdemo.bean.City;
import com.example.xuan.mvpdemo.bean.WeatherInfo;
import com.example.xuan.mvpdemo.biz.Listener.OnRequestListener;
import com.example.xuan.mvpdemo.biz.location.LocationService;
import com.example.xuan.mvpdemo.common.AppConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.xuan.mvpdemo.common.AppConstants.BASE_WEATHER_API;

/**
 * Author : xuan.
 * Data : 2017/7/20.
 * Description :input the description of this file.
 */

public class WeatherBiz implements IWeatherBiz{
    @Override
    public String getWeatherInfo(String cityName , final OnRequestListener onRequestListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_WEATHER_API)
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherInfo> model = service.getWeatherInfo(cityName);
        model.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                if (response.body().getStatus() == AppConstants.STATUS_OK) {
                    onRequestListener.onRequestSuccess(response.body());
                }else{
                    onRequestListener.onRequestFailed();
                }
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                onRequestListener.onRequestFailed();
            }
        });
        return  null;
    }
}
