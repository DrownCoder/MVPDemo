package com.example.xuan.mvpdemo.presenter;

import com.example.xuan.mvpdemo.bean.City;
import com.example.xuan.mvpdemo.bean.WeatherInfo;
import com.example.xuan.mvpdemo.biz.Listener.OnRequestListener;
import com.example.xuan.mvpdemo.biz.weather.IWeatherBiz;
import com.example.xuan.mvpdemo.biz.weather.WeatherBiz;
import com.example.xuan.mvpdemo.view.IQueryView;

/**
 * Author : xuan.
 * Data : 2017/7/24.
 * Description :input the description of this file.
 */

public class QueryPresenter {
    private IWeatherBiz mWeatherBiz;
    private IQueryView mQueryView;

    public QueryPresenter(IQueryView queryView) {
        this.mWeatherBiz = new WeatherBiz();
        this.mQueryView = queryView;
    }

    public void queryCity(String cityName) {
        mWeatherBiz.getWeatherInfo(cityName, new OnRequestListener<WeatherInfo>() {
            @Override
            public void onRequestSuccess(WeatherInfo c) {
                mQueryView.addCity(c);
            }

            @Override
            public void onRequestFailed() {
                mQueryView.showError();
            }
        });
    }
}
