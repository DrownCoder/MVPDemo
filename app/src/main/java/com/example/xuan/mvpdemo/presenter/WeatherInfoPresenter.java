package com.example.xuan.mvpdemo.presenter;

import android.content.Context;

import com.example.xuan.mvpdemo.bean.CityWeather;
import com.example.xuan.mvpdemo.bean.WeatherInfo;
import com.example.xuan.mvpdemo.biz.Listener.OnRequestListener;
import com.example.xuan.mvpdemo.biz.backup.BackupBiz;
import com.example.xuan.mvpdemo.biz.weather.IWeatherBiz;
import com.example.xuan.mvpdemo.biz.weather.WeatherBiz;
import com.example.xuan.mvpdemo.view.IWeatherView;

/**
 * Author : xuan.
 * Data : 2017/7/20.
 * Description :the presenter between the view and model
 */

public class WeatherInfoPresenter {
    private Context mContext;
    private WeatherInfo mWeatherInfo;
    private IWeatherBiz weatherBiz;
    private IWeatherView weatherView;

    private BackupBiz mDao;

    public WeatherInfoPresenter(Context context, IWeatherView weatherView) {
        this.mContext = context;
        this.weatherView = weatherView;
        this.weatherBiz = new WeatherBiz();
        this.mDao = new BackupBiz(mContext);
    }

    public WeatherInfo getWeatherInfo() {
        weatherView.showLoading();
        weatherBiz.getWeatherInfo(weatherView.getCityName(), new OnRequestListener<WeatherInfo>() {
            @Override
            public void onRequestSuccess(WeatherInfo info) {
                mWeatherInfo = info;
                mDao.insertOrUpdateCity(new CityWeather(info.getData().getCity()
                        , info.getData().getWendu(), info.getData().getForecast().get(0).getType()), "1");
                showDataWeather(0);
            }

            @Override
            public void onRequestFailed() {
            }
        });
        return mWeatherInfo;
    }

    public void showWeatherInfo(int i) {
        if (mWeatherInfo != null) {
            showDataWeather(i);
        }
    }

    private void showDataWeather(int i) {
        switch (i) {
            case -1: {//昨天
                weatherView.showWeatherInfo(
                        mWeatherInfo.getData().getYesterday().getDate(),
                        mWeatherInfo.getData().getYesterday().getHigh().substring(3),
                        mWeatherInfo.getData().getYesterday().getType());
                break;
            }
            case 0: {
                weatherView.showWeatherInfo(
                        mWeatherInfo.getData().getForecast().get(0).getDate(),
                        mWeatherInfo.getData().getWendu() + "℃",
                        mWeatherInfo.getData().getForecast().get(0).getType());
                break;
            }
            default: {
                weatherView.showWeatherInfo(
                        mWeatherInfo.getData().getForecast().get(i).getDate(),
                        mWeatherInfo.getData().getForecast().get(i).getHigh().substring(3),
                        mWeatherInfo.getData().getForecast().get(i).getType());
                break;
            }
        }
    }
}
