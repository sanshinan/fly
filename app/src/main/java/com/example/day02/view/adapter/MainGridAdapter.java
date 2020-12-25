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


public class MainGridAdapter extends DelegateAdapter.Adapter {



    private GridLayoutHelper gridLayoutHelper;

    private Context context;

    public MainGridAdapter(GridLayoutHelper gridLayoutHelper, Context context) {
        this.gridLayoutHelper = gridLayoutHelper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_itm_layout2, parent, false);
        return new GridViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        GridViewHolder viewHolder = ( GridViewHolder) holder;

        if(position==0){
            viewHolder.textView.setImageResource(R.drawable.a);
            viewHolder.text.setText("居家");
        }else if(position==1){
            viewHolder.textView.setImageResource(R.drawable.ab);
            viewHolder.text.setText("餐厅");
        }else if(position==2){
            viewHolder.textView.setImageResource(R.drawable.ad);
            viewHolder.text.setText("配件");
        }else if(position==3){
            viewHolder.textView.setImageResource(R.drawable.ac);
            viewHolder.text.setText("服装");
        }else if(position==4) {
            viewHolder.textView.setImageResource(R.drawable.ae);
            viewHolder.text.setText("志趣");
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class GridViewHolder extends RecyclerView.ViewHolder{
        private TextView text;
        private ImageView textView;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.grid_my_image);
            text=itemView.findViewById(R.id.titlea);
        }
    }

}
