package com.example.xuan.mvpdemo.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xuan.mvpdemo.R;
import com.example.xuan.mvpdemo.bean.CityWeather;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private Animation mAnim;

    public interface onItemClickListener {
        void onItemClick(String name);

        void onItemDelete(String name);
    }

    private onItemClickListener mOnItemClickListener;

    public onItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setmOnItemClickListener(onItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private List<CityWeather> mDatas;
    private Context mContext;

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rl_layout:
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view.getTag().toString());
                    }
                    break;
                case R.id.iv_index:
                    int pos = (int) view.getTag();
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemDelete(mDatas.get(pos).getName());
                    }
                    mDatas.remove(pos);
                    notifyItemRemoved(pos);
                    break;
            }
        }
    };


    public CityAdapter(Context context, List<CityWeather> data) {
        this.mContext = context;
        this.mDatas = data;
        mAnim = AnimationUtils.loadAnimation(mContext,R.anim.icon_show);

        if (data == null) {
            this.mDatas = new ArrayList<>();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_city, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CityWeather item = mDatas.get(position);
        holder.tvCityName.setText(item.getName());
        holder.rl.setTag(item.getName());
        holder.ivIndex.setTag(position);
        holder.rl.setOnClickListener(onClickListener);
        holder.rl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.ivIndex.setVisibility(View.VISIBLE);
                holder.ivIndex.startAnimation(mAnim);
                return true;
            }
        });
        holder.ivIndex.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCityName;
        ImageView ivIndex;
        RelativeLayout rl;

        public ViewHolder(View view) {
            super(view);
            tvCityName = view.findViewById(R.id.tv_city_name);
            ivIndex = view.findViewById(R.id.iv_index);
            rl = view.findViewById(R.id.rl_layout);
        }
    }
}