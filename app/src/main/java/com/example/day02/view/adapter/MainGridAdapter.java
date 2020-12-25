package com.example.day02.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.day02.R;


public class MainGridAdapter extends DelegateAdapter.Adapter {



    private GridLayoutHelper gridLayoutHelper;

    private Context context;

    public MainGridAdapter(GridLayoutHelper gridLayoutHelper, Context context) {
        this.gridLayoutHelper = gridLayoutHelper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_itm_layout2, parent, false);
        return new GridViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        GridViewHolder viewHolder = ( GridViewHolder) holder;
        Glide.with(context).load("http://cdwan.cn/www/static/channel/ic_canchu.png").into(((GridViewHolder) holder).textView);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class GridViewHolder extends RecyclerView.ViewHolder{

        private ImageView textView;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.grid_my_image);
        }
    }

}
