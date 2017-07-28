package com.example.xuan.mvpdemo.bean;

/**
 * Author : xuan.
 * Data : 2017/7/27.
 * Description :input the description of this file.
 */

public class CityWeather {
    private String name;
    private String temperature;
    private String type;

    public CityWeather(String name, String temperature, String type) {
        this.name = name;
        this.temperature = temperature;
        this.type = type;
    }

    public CityWeather() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
