package com.example.day02.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.example.day02.R;
import com.example.day02.modle.bean.Homebean;

import java.util.ArrayList;

public class MainTopAdapter extends DelegateAdapter.Adapter {
    Context context;
    LayoutHelper layoutHelper;
    private ArrayList<Homebean.DataBean.TopicListBean> topicListBeans;

    public MainTopAdapter(Context context, LayoutHelper layoutHelper, ArrayList<Homebean.DataBean.TopicListBean> topicListBeans) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.topicListBeans = topicListBeans;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_top, parent, false);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH) holder;
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        vh.recy.setLayoutManager(layoutManager);
        TopAdapter topAdapter = new TopAdapter(context, topicListBeans);
        vh.recy.setAdapter(topAdapter);
    }

    @Override
    public int getItemCount() {
        if (topicListBeans.size()>0){
            return 1;
        }else {
            return 0;
        }
    }
    class VH extends RecyclerView.ViewHolder{
        RecyclerView recy;
        public VH(@NonNull View itemView) {
            super(itemView);
            recy=itemView.findViewById(R.id.special_rec);
        }
    }
}
