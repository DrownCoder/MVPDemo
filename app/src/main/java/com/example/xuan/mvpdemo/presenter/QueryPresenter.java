package com.example.xuan.mvpdemo.presenter;

import android.content.Context;

import com.example.xuan.mvpdemo.bean.CityWeather;
import com.example.xuan.mvpdemo.bean.WeatherInfo;
import com.example.xuan.mvpdemo.biz.Listener.OnRequestListener;
import com.example.xuan.mvpdemo.biz.backup.BackupBiz;
import com.example.xuan.mvpdemo.biz.weather.IWeatherBiz;
import com.example.xuan.mvpdemo.biz.weather.WeatherBiz;
import com.example.xuan.mvpdemo.view.IQueryView;

import java.util.List;

/**
 * Author : xuan.
 * Data : 2017/7/24.
 * Description :input the description of this file.
 */

public class QueryPresenter {
    private Context mContext;
    private IWeatherBiz mWeatherBiz;
    private IQueryView mQueryView;

    private BackupBiz mDao;

    public QueryPresenter(Context context, IQueryView queryView) {
        this.mWeatherBiz = new WeatherBiz();
        this.mQueryView = queryView;
        this.mContext = context;
        mDao = new BackupBiz(context);
    }

    public void queryCity(String cityName) {
        mWeatherBiz.getWeatherInfo(cityName, new OnRequestListener<WeatherInfo>() {
            @Override
            public void onRequestSuccess(WeatherInfo info) {
                boolean isUpdate = mDao.insertOrUpdateCity(new CityWeather(info.getCity(),info.getData().getWendu(),
                        info.getData().getForecast().get(0).getType()));

                CityWeather city = new CityWeather();
                city.setName(info.getCity());
                city.setTemperature(info.getData().getWendu());
                city.setType(info.getData().getForecast().get(0).getType());

                if (!isUpdate) {
                    mQueryView.addCity(city);
                }

            }

            @Override
            public void onRequestFailed() {
                mQueryView.showError();
            }
        });
    }

    public List<CityWeather> getBackup(String key) {
        return mDao.getAllCityWeather();
    }

    public void deleteCity(String city) {
        mDao.deleteCity(city);
    }
}
