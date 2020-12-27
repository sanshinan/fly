package com.example.day02.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.example.day02.R;
import com.example.day02.modle.bean.Homebean;

import java.util.ArrayList;

public class MainSingAdapter extends DelegateAdapter.Adapter {
    Context context;
    LayoutHelper layoutHelper;
    private ArrayList<Homebean.DataBean> beans;

    public MainSingAdapter(Context context, LayoutHelper layoutHelper, ArrayList<Homebean.DataBean> beans) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.beans = beans;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_sj, parent, false);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH) holder;
        vh.text.setText("品牌制造商提供");
    }

    @Override
    public int getItemCount() {
        if (beans.size()>0){
            return beans.size();
        }else {
            return 0;
        }
    }

    class VH extends RecyclerView.ViewHolder{
        TextView text;
        public VH(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.titleb);
        }
    }
}
