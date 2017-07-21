package com.example.xuan.mvpdemo.common;

import android.app.Activity;

/**
 * Author : xuan.
 * Data : 2017/7/20.
 * Description :the BaseActivity of all the Activity
 */

public abstract class BaseActivity extends Activity{
    public abstract void initViews();
    public abstract void initEvents();
}
