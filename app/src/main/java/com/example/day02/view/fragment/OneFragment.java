package com.example.day02.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.day02.R;
import com.example.day02.modle.bean.Homebean;
import com.example.day02.view.adapter.MainGridAdapter;
import com.example.mvp.net.INetCallBack;
import com.example.mvp.net.RetrofitUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class OneFragment extends Fragment {

    private Banner ban;
    private ArrayList<Homebean.DataBean> beans;
    private RecyclerView recydh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_a, null);
        initView(inflate);
        initData();
        return inflate;

    }

    private void initData() {

    }

    private void initView(View inflate) {

        ban = inflate.findViewById(R.id.ban);
        beans=new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.drawable.aa);
        integers.add(R.drawable.bb);
        integers.add(R.drawable.cc);
        integers.add(R.drawable.dd);
        integers.add(R.drawable.ee);
        ban.setImages(integers)
                .setBannerAnimation(Transformer.ForegroundToBackground)
                .setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getContext()).load(path).into(imageView);
            }
        }).start();
        //vlayout
        recydh=inflate.findViewById(R.id.recy_dh);
        //初始化布局管理器
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();

        recydh.setRecycledViewPool(pool);
        pool.setMaxRecycledViews(0,10);


        /**
         设置Grid布局
         */
        recydh = inflate.findViewById(R.id.recy_dh);
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
      /*  // 在构造函数设置每行的网格个数
        // 公共属性
        gridLayoutHelper.setItemCount(5);// 设置布局里Item个数
        gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        gridLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
        gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
// gridLayoutHelper特有属性（下面会详细说明）
        gridLayoutHelper.setWeights(new float[]{20, 20, 20, 20, 20});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(5);// 设置每行多少个网格*/
        MainGridAdapter mainGridAdapter = new MainGridAdapter(gridLayoutHelper,getContext());
        DelegateAdapter adapter = new DelegateAdapter(virtualLayoutManager,true);

        adapter.addAdapter(mainGridAdapter);//第一行



        recydh.setLayoutManager(virtualLayoutManager);

        recydh.setAdapter(adapter);

    }
}
