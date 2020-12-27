package com.example.day02.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.example.day02.R;
import com.example.day02.modle.bean.Homebean;

import java.util.ArrayList;
import java.util.List;

public class MainJuAdapter extends DelegateAdapter.Adapter {
    Context context;
    private ArrayList<Homebean.DataBean.CategoryListBean> categoryListBeans;
    LayoutHelper layoutHelper;

    public MainJuAdapter(Context context, ArrayList<Homebean.DataBean.CategoryListBean> categoryListBeans, LayoutHelper layoutHelper) {
        this.context = context;
        this.categoryListBeans = categoryListBeans;
        this.layoutHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_jujia, parent, false);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH) holder;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        vh.re.setLayoutManager(gridLayoutManager);
        Homebean.DataBean.CategoryListBean categoryListBean = categoryListBeans.get(position);
        List<Homebean.DataBean.CategoryListBean.GoodsListBean> goodsList = categoryListBean.getGoodsList();
        vh.name.setText(categoryListBean.getName());
        ArrayList<Homebean.DataBean.CategoryListBean.GoodsListBean> listBeans = new ArrayList<>();
        if (listBeans.size()>0){
            listBeans.addAll(goodsList);
            EvenAdapter evenAdapter = new EvenAdapter(listBeans, context);
            vh.re.setAdapter(evenAdapter);
        }
    }

    @Override
    public int getItemCount() {
        if (categoryListBeans.size()>0){
            return categoryListBeans.size();
        }else {
            return 0;
        }
    }
    class VH extends RecyclerView.ViewHolder{
        RecyclerView re;
        TextView name;
        public VH(@NonNull View itemView) {
            super(itemView);
            re=itemView.findViewById(R.id.envent_rec);
            name=itemView.findViewById(R.id.ev_item_name);
        }
    }
}
