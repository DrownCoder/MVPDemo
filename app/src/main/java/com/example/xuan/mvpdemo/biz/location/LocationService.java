package com.example.xuan.mvpdemo.biz.location;

import com.example.xuan.mvpdemo.bean.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author : xuan.
 * Data : 2017/7/21.
 * Description :input the description of this file.
 */

public interface LocationService {
    @GET("/location/ip")
    Call<City> getLocationByIp(@Query("ak") String ak,
                               @Query("mcode") String mcode);
}
