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
import com.bumptech.glide.Glide;
import com.example.day02.R;
import com.example.day02.modle.bean.Homebean;

import java.util.ArrayList;

public class MainGrmoneAdapter extends DelegateAdapter.Adapter {
    Context context;
    private ArrayList<Homebean.DataBean.NewGoodsListBean> newGoodsListBeans;
    LayoutHelper layoutHelper;

    public MainGrmoneAdapter(Context context, ArrayList<Homebean.DataBean.NewGoodsListBean> newGoodsListBeans, LayoutHelper layoutHelper) {
        this.context = context;
        this.newGoodsListBeans = newGoodsListBeans;
        this.layoutHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_five, parent, false);
        return new VHY(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VHY vhy= (VHY) holder;
        vhy.texta.setText(newGoodsListBeans.get(position).getName());
        vhy.textb.setText("￥ "+newGoodsListBeans.get(position).getRetail_price());
        Glide.with(context).load(newGoodsListBeans.get(position).getList_pic_url()).into(vhy.img);
    }

    @Override
    public int getItemCount() {
        if (newGoodsListBeans.size()>0){
            return newGoodsListBeans.size();
        }else {
            return 0;
        }
    }
    class VHY extends RecyclerView.ViewHolder{
        ImageView img;
        TextView texta;
        TextView textb;
        public VHY(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.products_img);
            texta=itemView.findViewById(R.id.products_name);
            textb=itemView.findViewById(R.id.products_price);
        }
    }
}
