package com.example.xuan.mvpdemo.presenter;

import com.example.xuan.mvpdemo.bean.WeatherInfo;
import com.example.xuan.mvpdemo.biz.weather.IWeatherBiz;
import com.example.xuan.mvpdemo.biz.Listener.OnRequestListener;
import com.example.xuan.mvpdemo.biz.weather.WeatherBiz;
import com.example.xuan.mvpdemo.view.IWeatherView;

/**
 * Author : xuan.
 * Data : 2017/7/20.
 * Description :the presenter between the view and model
 */

public class WeatherInfoPresenter {
    private IWeatherBiz weatherBiz;
    private IWeatherView weatherView;

    public WeatherInfoPresenter(IWeatherView weatherView) {
        this.weatherView = weatherView;
        this.weatherBiz = new WeatherBiz();
    }
    public void getWeatherInfo() {
        weatherView.showLoading();
        weatherBiz.getWeatherInfo(weatherView.getCityName(), new OnRequestListener<WeatherInfo>() {
            @Override
            public void onRequestSuccess(WeatherInfo info) {
                weatherView.showWeatherInfo(info);
            }

            @Override
            public void onRequestFailed() {

            }
        });
    }
}
