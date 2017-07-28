package com.example.xuan.mvpdemo.common;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Author : xuan.
 * Data : 2017/7/24.
 * Description :input the description of this file.
 */

public abstract class BaseFragment extends Fragment{
    public abstract void initViews(View root);
    public abstract void initEvents();
    public abstract void initDatas();
}
