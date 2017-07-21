package com.example.xuan.mvpdemo.biz.location;

import com.example.xuan.mvpdemo.bean.City;
import com.example.xuan.mvpdemo.biz.Listener.OnRequestListener;
import com.example.xuan.mvpdemo.common.AppConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.xuan.mvpdemo.common.AppConstants.BASE_LOCATION_API;

/**
 * Author : xuan.
 * Data : 2017/7/21.
 * Description :input the description of this file.
 */

public class LocationBiz implements ILocationBiz {
    @Override
    public void getLocationByIp(final OnRequestListener onRequestListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_LOCATION_API)
                .build();
        LocationService service = retrofit.create(LocationService.class);
        Call<City> model = service.getLocationByIp(AppConstants.AK, AppConstants.MCODE);
        model.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                onRequestListener.onRequestSuccess(response.body());
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                onRequestListener.onRequestFailed();
            }
        });
    }
}
