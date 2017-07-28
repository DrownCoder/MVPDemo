package com.example.xuan.mvpdemo.biz.backup;

import com.example.xuan.mvpdemo.bean.CityWeather;

import java.util.List;

/**
 * Author : xuan.
 * Data : 2017/7/27.
 * Description :input the description of this file.
 */

public interface IBackupBiz {
    public void addCityWeather(CityWeather item);

    public void addCityWeather(CityWeather item, String islocal);

    public List<CityWeather> getAllCityWeather();

    public boolean containCity(String name);

    public void updateCity(CityWeather cityWeather);

    public boolean insertOrUpdateCity(CityWeather cityWeather);

    public boolean insertOrUpdateCity(CityWeather cityWeather, String islocal);

    public void deleteCity(String city);
}
