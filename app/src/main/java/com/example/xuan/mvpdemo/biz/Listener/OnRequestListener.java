package com.example.xuan.mvpdemo.biz.Listener;


/**
 * Author : xuan.
 * Data : 2017/7/20.
 * Description :input the description of this file.
 */

public interface OnRequestListener<T> {
    void onRequestSuccess(T t);
    void onRequestFailed();
}
