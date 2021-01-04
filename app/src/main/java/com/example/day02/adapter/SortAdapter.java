package com.example.day02.adapter;

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
import com.example.day02.modle.bean.Sortbean;

import java.util.List;

public class SortAdapter extends RecyclerView.Adapter {
    Context context;
    List<Sortbean.DataBean.CurrentCategoryBean.SubCategoryListBean> sort;

    public SortAdapter(Context context, List<Sortbean.DataBean.CurrentCategoryBean.SubCategoryListBean> sort) {
        this.context = context;
        this.sort = sort;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_sorat, parent, false);
        return new SOR(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SOR h= (SOR) holder;
        Sortbean.DataBean.CurrentCategoryBean.SubCategoryListBean categoryBean = sort.get(position);
        h.textView.setText(categoryBean.getName());
        Glide.with(context).load(categoryBean.getWap_banner_url()).into(h.img);
    }

    @Override
    public int getItemCount() {
        return sort.size();
    }
    class SOR extends RecyclerView.ViewHolder{
        ImageView img;
        TextView textView;
        public SOR(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_item_sorcat);
            textView=itemView.findViewById(R.id.text_item_sorat);
        }
    }
}
