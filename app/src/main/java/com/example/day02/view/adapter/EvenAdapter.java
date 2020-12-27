package com.example.day02.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day02.R;
import com.example.day02.modle.bean.Homebean;

import java.util.List;

public class EvenAdapter extends RecyclerView.Adapter {
    List<Homebean.DataBean.CategoryListBean.GoodsListBean> goodsList;
    Context context;

    public EvenAdapter(List<Homebean.DataBean.CategoryListBean.GoodsListBean> goodsList, Context context) {
        this.goodsList = goodsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.even, parent, false);
        return new VB(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VB vhReEv= (VB) holder;
        Homebean.DataBean.CategoryListBean.GoodsListBean goodsListBean = goodsList.get(position);
        vhReEv.tta.setText(goodsListBean.getName());
        vhReEv.ttb.setText("￥"+goodsListBean.getRetail_price());
        Glide.with(context).load(goodsListBean.getList_pic_url()).into(vhReEv.img);
    }

    @Override
    public int getItemCount() {
        if (goodsList.size()>0){
            return goodsList.size();
        }else {
            return 0;
        }
    }
    class VB extends RecyclerView.ViewHolder{
        ImageView img;
        TextView  tta;
        TextView ttb;
        public VB(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.event_item_img);
            tta=itemView.findViewById(R.id.event_item_name);
            ttb=itemView.findViewById(R.id.event_item_price);
        }
    }
}
