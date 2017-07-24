package com.example.xuan.mvpdemo.view;

import com.example.xuan.mvpdemo.bean.WeatherInfo;

/**
 * Author : xuan.
 * Data : 2017/7/24.
 * Description :input the description of this file.
 */

public interface IQueryView {
    void addCity(WeatherInfo city);

    void showError();
}
