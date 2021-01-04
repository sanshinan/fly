package com.example.day02.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.example.day02.R;
import com.example.day02.modle.bean.Homebean;

import java.util.List;

public class MainEdtextAdapter extends DelegateAdapter.Adapter {
    Context context;
    LayoutHelper layoutHelper;
    private List<Homebean.DataBean> beans;

    public MainEdtextAdapter(Context context, LayoutHelper layoutHelper, List<Homebean.DataBean> beans) {
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_edtext, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder holder1= (ViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        EditText editText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            editText=itemView.findViewById(R.id.edit_query);
        }
    }
}
