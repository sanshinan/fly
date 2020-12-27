package com.example.day02.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.day02.R;
import com.example.day02.modle.bean.Homebean;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainLinearAdapter extends DelegateAdapter.Adapter {
    ArrayList<Homebean.DataBean> beans;
    LayoutHelper linearLayoutHelper;
    Context context;

    public MainLinearAdapter(ArrayList<Homebean.DataBean> beans, LayoutHelper linearLayoutHelper, Context context) {
        this.beans = beans;
        this.linearLayoutHelper = linearLayoutHelper;
        this.context = context;

    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return linearLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_ban, parent, false);
        return new Vh(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Vh vh= (Vh) holder;
        //只有一个bean类
        List<Homebean.DataBean.BannerBean> banner = beans.get(0).getBanner();
        vh.bann.setImages(banner)
                .setBannerAnimation(Transformer.ForegroundToBackground)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Homebean.DataBean.BannerBean oo= (Homebean.DataBean.BannerBean) path;
                        Glide.with(context).load(oo.getImage_url()).into(imageView);
                    }
                }).start();
    }

    @Override
    public int getItemCount() {
        if (beans.size()>0){
            return 1;
        }else {
            return 0;
        }
    }
    class Vh extends RecyclerView.ViewHolder {
        Banner bann;
        public Vh(@NonNull View itemView) {
            super(itemView);
            bann=itemView.findViewById(R.id.banb);
        }
    }
}
