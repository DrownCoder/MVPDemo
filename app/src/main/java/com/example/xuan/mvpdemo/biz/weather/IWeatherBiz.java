package com.example.xuan.mvpdemo.biz.weather;

import com.example.xuan.mvpdemo.biz.Listener.OnRequestListener;

/**
 * Author : xuan.
 * Data : 2017/7/20.
 * Description : the operation of weather
 */

public interface IWeatherBiz {
    public String getWeatherInfo(String cityName ,OnRequestListener onRequestListener);
}
