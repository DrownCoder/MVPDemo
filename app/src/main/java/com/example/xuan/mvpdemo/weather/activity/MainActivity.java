package com.example.xuan.mvpdemo.weather.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.xuan.mvpdemo.R;
import com.example.xuan.mvpdemo.biz.backup.DatabaseHelper;
import com.example.xuan.mvpdemo.common.BaseActivity;
import com.example.xuan.mvpdemo.weather.fragment.QueryFragment;
import com.example.xuan.mvpdemo.weather.fragment.WeatherFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{
    private ViewPager mViewPager;
    private Fragment mWeatherFragment;
    private Fragment mQueryFragment;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x111) {
                String cityname = msg.obj.toString();
                mViewPager.setCurrentItem(0);
                ((WeatherFragment) mWeatherFragment).showCityWeather(cityname);
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    public void initViews() {
        mViewPager = findViewById(R.id.viewpager);
        List<Fragment> fragments = new ArrayList<>();
        mWeatherFragment = new WeatherFragment();
        mQueryFragment = new QueryFragment();
        fragments.add(mWeatherFragment);
        fragments.add(mQueryFragment);
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void initEvents() {

    }

    public Handler getHandler() {
        return mHandler;
    }

    public class FragAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public FragAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
            super(fragmentManager);
            mFragments=fragments;
        }

        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            return mFragments.get(arg0);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mFragments.size();
        }

    }
}
