package com.example.xuan.mvpdemo.weather.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xuan.mvpdemo.R;
import com.example.xuan.mvpdemo.bean.City;
import com.example.xuan.mvpdemo.bean.WeatherInfo;
import com.example.xuan.mvpdemo.common.BaseFragment;
import com.example.xuan.mvpdemo.presenter.LocationPresenter;
import com.example.xuan.mvpdemo.presenter.WeatherInfoPresenter;
import com.example.xuan.mvpdemo.view.IWeatherView;
import com.example.xuan.mvpdemo.widget.CircleButton;


public class WeatherFragment extends BaseFragment implements IWeatherView{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        initViews(view);
        initLocation();
        initEvents();
        return view;
    }

    private TextView mTvCityName;
    private TextView mTvTemperature;
    private TextView mTvWeather;
    private TextView mTvDate;

    private CircleButton mIndex;
    private int index = 0;

    private WeatherInfoPresenter mPresenter = new WeatherInfoPresenter(this);
    private LocationPresenter mLocationPresenter = new LocationPresenter(this);

    private void initLocation() {
        mLocationPresenter.getLocationInfo();
    }

    @Override
    public String getCityName() {
        return mTvCityName.getText().toString();
    }

    @Override
    public void showWeatherInfo(String date, String temp, String type) {
        mTvDate.setText(date);
        mTvTemperature.setText(temp);
        mTvWeather.setText(type);
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
    public void showWeather() {
        mPresenter.getWeatherInfo();
    }

    @Override
    public void initViews(View root) {
        mTvDate = root.findViewById(R.id.tv_date);
        mTvCityName = root.findViewById(R.id.tv_city_name);
        mTvTemperature = root.findViewById(R.id.tv_temperature);
        mTvWeather = root.findViewById(R.id.tv_city_weather);
        mIndex = root.findViewById(R.id.cb_index);
    }

    @Override
    public void initEvents() {
        mIndex.setOnRotatingListener(new CircleButton.onRotatingListener() {
            @Override
            public void onRotaing(int direction) {
                index = index + direction;
                if (index > 4) {
                    index = 4;
                } else if (index < -1) {
                    index = -1;
                }
                mPresenter.showWeatherInfo(index);
            }
        });
    }

    public WeatherInfo getCurrentCity() {
        return mPresenter.getWeatherInfo();
    }
}
