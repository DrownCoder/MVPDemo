package com.example.xuan.mvpdemo.weather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuan.mvpdemo.R;
import com.example.xuan.mvpdemo.bean.WeatherInfo;
import com.example.xuan.mvpdemo.common.BaseFragment;
import com.example.xuan.mvpdemo.presenter.QueryPresenter;
import com.example.xuan.mvpdemo.view.IQueryView;

import java.util.ArrayList;
import java.util.List;

public class QueryFragment extends BaseFragment implements IQueryView{
    private ImageView mIvDelete;
    private ImageView mIvQuery;
    private EditText mEtQuery;
    private RecyclerView mCityList;
    private List<WeatherInfo> mDatas;
    private CityAdapter mAdapter;

    private QueryPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        initViews(view);
        initEvents();
        return view;
    }

    @Override
    public void initViews(View root) {
        mPresenter = new QueryPresenter(this);

        mIvDelete = root.findViewById(R.id.iv_delete);
        mIvQuery = root.findViewById(R.id.iv_query);
        mEtQuery = root.findViewById(R.id.et_query);
        mCityList = root.findViewById(R.id.city_list);
        mDatas = new ArrayList<>();
        mAdapter = new CityAdapter(mDatas);
        mCityList.setLayoutManager(new LinearLayoutManager(getContext()));
        mCityList.setAdapter(mAdapter);
    }

    @Override
    public void initEvents() {
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
            }
        });
        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtQuery.setText("");
            }
        });
    }

    @Override
    public void addCity(WeatherInfo info) {
        mDatas.add(info);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "暂无该城市天气信息╮(╯▽╰)╭",Toast.LENGTH_SHORT).show();
    }

    class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>
    {
        private List<WeatherInfo> mDatas;
        public CityAdapter(List<WeatherInfo> data) {
            this.mDatas = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.item_city, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {
            holder.tvCityName.setText(mDatas.get(position).getData().getCity());
            holder.tvAdvice.setText(mDatas.get(position).getData().getGanmao());
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView tvCityName;
            TextView tvAdvice;
            ImageView ivIndex;
            public ViewHolder(View view)
            {
                super(view);
                tvCityName = view.findViewById(R.id.tv_city_name);
                tvAdvice = view.findViewById(R.id.tv_advice);
                ivIndex = view.findViewById(R.id.iv_index);
            }
        }
    }
}
