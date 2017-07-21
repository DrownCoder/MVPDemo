package com.example.xuan.mvpdemo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xuan.mvpdemo.bean.WeatherInfo;
import com.example.xuan.mvpdemo.common.BaseActivity;
import com.example.xuan.mvpdemo.bean.City;
import com.example.xuan.mvpdemo.presenter.LocationPresenter;
import com.example.xuan.mvpdemo.presenter.WeatherInfoPresenter;
import com.example.xuan.mvpdemo.view.IWeatherView;

public class MainActivity extends BaseActivity implements IWeatherView{
    private TextView mTvCityName;
    private TextView mTvTemperature;
    private TextView mTvWeather;
    private ImageView mIvBac;

    private WeatherInfoPresenter mPresenter = new WeatherInfoPresenter(this);
    private LocationPresenter mLocationPresenter = new LocationPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initLocation();
        initEvents();
    }

    private void initLocation() {
        mLocationPresenter.getLocationInfo();
    }

    @Override
    public String getCityName() {
        return mTvCityName.getText().toString();
    }

    @Override
    public void showWeatherInfo(WeatherInfo info) {
        mTvTemperature.setText(info.getData().getWendu());
        mTvWeather.setText(info.getData().getForecast().get(0).getType());
    }

    @Override
    public void showCityName(City city) {
        mTvCityName.setText(city.getContent().getAddress());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void initViews() {
        mTvCityName = findViewById(R.id.tv_city_name);
        mTvTemperature = findViewById(R.id.tv_temperature);
        mTvWeather = findViewById(R.id.tv_city_weather);
        mIvBac = findViewById(R.id.iv_bac);
    }

    @Override
    public void initEvents() {

    }
}
