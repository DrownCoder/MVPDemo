package com.example.xuan.mvpdemo.biz.location;

import com.example.xuan.mvpdemo.biz.Listener.OnRequestListener;

/**
 * Author : xuan.
 * Data : 2017/7/21.
 * Description :input the description of this file.
 */

public interface ILocationBiz {
    public void getLocationByIp(OnRequestListener onRequestListener);
}
