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

import java.util.ArrayList;

public class TopAdapter extends RecyclerView.Adapter {
    Context context;
    private ArrayList<Homebean.DataBean.TopicListBean> topicListBeans;

    public TopAdapter(Context context, ArrayList<Homebean.DataBean.TopicListBean> topicListBeans) {
        this.context = context;
        this.topicListBeans = topicListBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_top_even, parent, false);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        Homebean.DataBean.TopicListBean topicListBean = topicListBeans.get(position);
        vh.name.setText(topicListBean.getTitle());
        vh.price.setText("￥" + topicListBean.getPrice_info() + "起");
        vh.jie.setText(topicListBean.getSubtitle());
        Glide.with(context).load(topicListBean.getItem_pic_url()).into(vh.img);
    }
    @Override
    public int getItemCount() {
        if (topicListBeans.size()>0){
            return topicListBeans.size();
        }else {
            return 0;

        }
    }
    class VH extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;
        TextView price;
        TextView jie;
        public VH(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.special_item_img);
            name=itemView.findViewById(R.id.special_item_name);
            price=itemView.findViewById(R.id.special_item_price);
            jie=itemView.findViewById(R.id.special_item_jiex);
        }
    }
}
