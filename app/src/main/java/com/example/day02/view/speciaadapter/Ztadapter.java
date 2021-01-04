package com.example.day02.view.speciaadapter;

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
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bumptech.glide.Glide;
import com.example.day02.R;
import com.example.day02.modle.bean.Specialbean;

import java.util.ArrayList;
import java.util.List;

public class Ztadapter extends DelegateAdapter.Adapter {
    private List<Specialbean.DataBeanX.DataBean> dataBeans;
    Context context;
    LayoutHelper layoutHelper;

    public Ztadapter(List<Specialbean.DataBeanX.DataBean> dataBeans, Context context, LayoutHelper layoutHelper) {
        this.dataBeans = dataBeans;
        this.context = context;
        this.layoutHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_specia, parent, false);
        return new SPVH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SPVH spvh= (SPVH) holder;
        Specialbean.DataBeanX.DataBean dataBean = dataBeans.get(position);
        spvh.texta.setText(dataBean.getTitle()+"");
        spvh.textb.setText(dataBean.getSubtitle());
        spvh.textc.setText(dataBean.getPrice_info()+"元起");
        Glide.with(context).load(dataBean.getScene_pic_url()).into(spvh.img);
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }
    class SPVH extends RecyclerView.ViewHolder{
        ImageView img;
        TextView texta;
        TextView textb;
        TextView textc;
        public SPVH(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_specia);
            texta=itemView.findViewById(R.id.title_a);
            textb=itemView.findViewById(R.id.title_b);
            textc=itemView.findViewById(R.id.title_c);
        }
    }
}
