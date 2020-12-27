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
import com.example.day02.modle.bean.Homebean;

import java.util.ArrayList;
import java.util.List;


public class MainGridAdapter extends DelegateAdapter.Adapter {


    private ArrayList<Homebean.DataBean.ChannelBean> channelBeans;
    private LayoutHelper layoutHelper;

    private Context context;

    public MainGridAdapter(ArrayList<Homebean.DataBean.ChannelBean> channelBeans, LayoutHelper gridLayoutHelper, Context context) {
        this.channelBeans = channelBeans;
        this.layoutHelper = gridLayoutHelper;
        this.context = context;

    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
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
        viewHolder.text.setText(channelBeans.get(position).getName());
        Glide.with(context).load(channelBeans.get(position).getIcon_url()).into(viewHolder.textView);

    }

    @Override
    public int getItemCount() {
        if (channelBeans.size()>0){
            return channelBeans.size();
        }else {
            return 0;
        }
    }


    class GridViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        ImageView textView;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.grid_my_image);
            text=itemView.findViewById(R.id.titlea);
        }
    }

}
