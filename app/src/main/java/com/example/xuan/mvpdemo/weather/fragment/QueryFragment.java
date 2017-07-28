package com.example.xuan.mvpdemo.weather.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.xuan.mvpdemo.R;
import com.example.xuan.mvpdemo.bean.CityWeather;
import com.example.xuan.mvpdemo.common.AppConstants;
import com.example.xuan.mvpdemo.common.BaseFragment;
import com.example.xuan.mvpdemo.presenter.QueryPresenter;
import com.example.xuan.mvpdemo.util.KeyBoardUtils;
import com.example.xuan.mvpdemo.view.IQueryView;
import com.example.xuan.mvpdemo.weather.activity.MainActivity;
import com.example.xuan.mvpdemo.weather.adapter.CityAdapter;

import java.util.List;

public class QueryFragment extends BaseFragment implements IQueryView{
    private ImageView mIvDelete;
    private ImageView mIvQuery;
    private EditText mEtQuery;
    private RecyclerView mCityList;
    private List<CityWeather> mDatas;
    private CityAdapter mAdapter;

    private QueryPresenter mPresenter;

    private MainActivity mActivity;
    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        initViews(view);
        initDatas();
        initEvents();
        return view;
    }

    @Override
    public void initViews(View root) {
        mPresenter = new QueryPresenter(getContext(), this);

        mIvDelete = root.findViewById(R.id.iv_delete);
        mIvQuery = root.findViewById(R.id.iv_query);
        mEtQuery = root.findViewById(R.id.et_query);
        mCityList = root.findViewById(R.id.city_list);

        mCityList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void initEvents() {
        mAdapter.setmOnItemClickListener(new CityAdapter.onItemClickListener() {
            @Override
            public void onItemClick(String name) {
                Message msg = new Message();
                msg.what = 0x111;
                msg.obj = name;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onItemDelete(String name) {
                mPresenter.deleteCity(name);
            }
        });
        mEtQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = charSequence.length();
                if (length == 0) {
                    mIvQuery.setVisibility(View.GONE);
                }else{
                    mIvQuery.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mIvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.queryCity(mEtQuery.getText().toString());
                KeyBoardUtils.closeKeybord(mEtQuery, getContext());
            }
        });
        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtQuery.setText("");
                KeyBoardUtils.closeKeybord(mEtQuery, getContext());
            }
        });
    }

    @Override
    public void initDatas() {
        mDatas = mPresenter.getBackup(AppConstants.LOCAL_CITY);
        mAdapter = new CityAdapter(getContext(), mDatas);
        mCityList.setLayoutManager(new LinearLayoutManager(getContext()));
        mCityList.setAdapter(mAdapter);
    }

    @Override
    public void addCity(CityWeather info) {
        mDatas.add(info);
        mAdapter.notifyItemInserted(mDatas.size()-1);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "暂无该城市天气信息╮(╯▽╰)╭",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
        mHandler = mActivity.getHandler();
    }
}
