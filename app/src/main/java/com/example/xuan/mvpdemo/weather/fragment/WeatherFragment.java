package com.example.xuan.mvpdemo.weather.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xuan.mvpdemo.R;
import com.example.xuan.mvpdemo.bean.City;
import com.example.xuan.mvpdemo.common.BaseFragment;
import com.example.xuan.mvpdemo.presenter.LocationPresenter;
import com.example.xuan.mvpdemo.presenter.WeatherInfoPresenter;
import com.example.xuan.mvpdemo.view.IWeatherView;
import com.example.xuan.mvpdemo.widget.CircleButton;
import com.github.lzyzsd.randomcolor.RandomColor;


public class WeatherFragment extends BaseFragment implements IWeatherView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        initViews(view);
        initDatas();
        initEvents();
        return view;
    }

    private RelativeLayout mContent;
    private TextView mTvCityName;
    private TextView mTvTemperature;
    private TextView mTvWeather;
    private TextView mTvDate;
    private ImageView mIvLocation;
    private ImageView mIvSwitch;

    private CircleButton mIndex;
    private int index = 0;

    private WeatherInfoPresenter mPresenter;
    private LocationPresenter mLocationPresenter = new LocationPresenter(this);

    //随机颜色
    private boolean openColor;
    private RandomColor mBacColor;

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
        showCityName(city.getContent().getAddress());
    }

    @Override
    public void showCityName(String name) {
        mTvCityName.setText(name);
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
        mContent = root.findViewById(R.id.content);
        mTvDate = root.findViewById(R.id.tv_date);
        mTvCityName = root.findViewById(R.id.tv_city_name);
        mTvTemperature = root.findViewById(R.id.tv_temperature);
        mTvWeather = root.findViewById(R.id.tv_city_weather);
        mIndex = root.findViewById(R.id.cb_index);
        mIvLocation = root.findViewById(R.id.iv_location);
        mIvSwitch = root.findViewById(R.id.iv_switch);

        mPresenter = new WeatherInfoPresenter(getContext(), this);
    }

    @Override
    public void initEvents() {
        mIndex.setOnRotatingListener(new CircleButton.onRotatingListener() {
            @Override
            public void onRotaing(int direction) {
                if (openColor) {
                    mContent.setBackgroundColor(mBacColor.randomColor());
                }
                index = index + direction;
                if (index > 4) {
                    index = 4;
                } else if (index < -1) {
                    index = -1;
                }
                mPresenter.showWeatherInfo(index);
            }
        });
        mIvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContent.setBackgroundColor(Color.parseColor("#2B2B2B"));
                mIvSwitch.setImageResource(R.mipmap.close);
                openColor = false;
                mLocationPresenter.getLocationInfo();
            }
        });
        mIvSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColor = !openColor;
                mIvSwitch.setImageResource(openColor?R.mipmap.open:R.mipmap.close);
            }
        });
    }

    @Override
    public void initDatas() {
        mLocationPresenter.getLocationInfo();
        mBacColor = new RandomColor();
    }

    public void showCityWeather(String name) {
        showCityName(name);
        mPresenter.getWeatherInfo();
    }
}
