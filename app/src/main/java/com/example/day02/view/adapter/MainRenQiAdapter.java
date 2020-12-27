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

public class MainRenQiAdapter extends DelegateAdapter.Adapter {
    Context context;
    LayoutHelper layoutHelper;
    private ArrayList<Homebean.DataBean.HotGoodsListBean> hotGoodsListBeans;

    public MainRenQiAdapter(Context context, LayoutHelper layoutHelper, ArrayList<Homebean.DataBean.HotGoodsListBean> hotGoodsListBeans) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.hotGoodsListBeans = hotGoodsListBeans;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_six, parent, false);
        return new VHI(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VHI vhi= (VHI) holder;
        vhi.ta.setText(hotGoodsListBeans.get(position).getName());
        vhi.tb.setText(hotGoodsListBeans.get(position).getGoods_brief());
        vhi.tc.setText("钱"+hotGoodsListBeans.get(position).getRetail_price());
        Glide.with(context).load(hotGoodsListBeans.get(position).getList_pic_url()).into(vhi.img);
    }

    @Override
    public int getItemCount() {
       if (hotGoodsListBeans.size()>0){
           return hotGoodsListBeans.size();
       }else {
           return 0;
       }
    }
    class VHI extends RecyclerView.ViewHolder{
        ImageView img;
        TextView ta;
        TextView tb;
        TextView tc;
        public VHI(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.products_img);
            ta=itemView.findViewById(R.id.popularity_name);
            tb=itemView.findViewById(R.id.popularity_jies);
            tc=itemView.findViewById(R.id.products_price);

        }
    }
}
