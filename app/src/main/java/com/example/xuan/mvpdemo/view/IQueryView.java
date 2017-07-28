package com.example.xuan.mvpdemo.view;

import com.example.xuan.mvpdemo.bean.CityWeather;

/**
 * Author : xuan.
 * Data : 2017/7/24.
 * Description :input the description of this file.
 */

public interface IQueryView {
    void addCity(CityWeather city);

    void showError();

}
