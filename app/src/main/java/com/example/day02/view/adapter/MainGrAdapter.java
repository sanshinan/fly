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

public class MainGrAdapter extends DelegateAdapter.Adapter {

    Context context;
    GridLayoutHelper layoutHelper;
    private ArrayList<Homebean.DataBean.BrandListBean> brandListBeans;

    public MainGrAdapter(Context context, GridLayoutHelper layoutHelper, ArrayList<Homebean.DataBean.BrandListBean> brandListBeans) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.brandListBeans = brandListBeans;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_four, parent, false);
        return new VHe(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            VHe h= (VHe) holder;

        h.txa.setText(brandListBeans.get(position).getName());
        h.txb.setText(brandListBeans.get(position).getFloor_price()+"元起");
        Glide.with(context).load(brandListBeans.get(position).getNew_pic_url()).into(h.img);
    }

    @Override
    public int getItemCount() {
        if (brandListBeans.size()>0){
            return brandListBeans.size();
        }else {
            return 0;
        }
    }

    class VHe extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txa;
        TextView txb;
        public VHe(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.grild_img_back);
            txa=itemView.findViewById(R.id.grild_one_name);
            txb=itemView.findViewById(R.id.grild_one_price);
        }
    }
}
