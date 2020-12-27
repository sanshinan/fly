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
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.day02.R;
import com.example.day02.modle.bean.Homebean;
import com.example.day02.view.adapter.MainGrAdapter;
import com.example.day02.view.adapter.MainGridAdapter;
import com.example.day02.view.adapter.MainGrmoneAdapter;
import com.example.day02.view.adapter.MainJuAdapter;
import com.example.day02.view.adapter.MainLinearAdapter;
import com.example.day02.view.adapter.MainRenQiAdapter;
import com.example.day02.view.adapter.MainSingAdapter;
import com.example.day02.view.adapter.MainTopAdapter;
import com.example.mvp.net.INetCallBack;
import com.example.mvp.net.RetrofitUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment {


    private ArrayList<Homebean.DataBean> beans;
    private ArrayList<Homebean.DataBean.ChannelBean> channelBeans;
    private ArrayList<Homebean.DataBean.BrandListBean> brandListBeans;
    private ArrayList<Homebean.DataBean.NewGoodsListBean> newGoodsListBeans;
    private ArrayList<Homebean.DataBean.HotGoodsListBean> hotGoodsListBeans;
    private ArrayList<Homebean.DataBean.TopicListBean> topicListBeans;
    private ArrayList<Homebean.DataBean.CategoryListBean> categoryListBeans;
    private RecyclerView recydh;
    private Homebean homebeans;
    private MainLinearAdapter mainLinearAdapter;
    private MainGridAdapter mainGridAdapter;
    private MainSingAdapter mainSingAdapter;
    private MainGrAdapter mainGrAdapter;
    private MainSingAdapter singAdapter;
    private MainGrmoneAdapter mainGrmoneAdapter;
    private MainSingAdapter singaAdapter;
    private MainRenQiAdapter mainRenQiAdapter;
    private MainSingAdapter singabAdapter;
    private MainTopAdapter mainTopAdapter;
    private MainJuAdapter mainJuAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        homebeans = (Homebean) getArguments().getSerializable("key");

        View inflate = inflater.inflate(R.layout.fragment_a, null);
        initView(inflate);
        initData();
        return inflate;

    }

    private void initData() {

    }

    private void initView(View inflate) {

        beans=new ArrayList<>();
        channelBeans=new ArrayList<>();
        brandListBeans=new ArrayList<>();
        newGoodsListBeans=new ArrayList<>();
        hotGoodsListBeans=new ArrayList<>();
        topicListBeans=new ArrayList<>();
        categoryListBeans=new ArrayList<>();
        //vlayout
        recydh=inflate.findViewById(R.id.recy_dh);
        //初始化布局管理器
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getActivity());

        //线性布局使用 Banner
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();

        mainLinearAdapter = new MainLinearAdapter(beans,linearLayoutHelper, getContext());

        /**
         设置Grid布局 一横多个
         */

        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();

        columnLayoutHelper.setMargin(10,10,10,10);
        mainGridAdapter = new MainGridAdapter(channelBeans,columnLayoutHelper,getContext());

        //品牌制造商
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setItemCount(1);
        singleLayoutHelper.setAspectRatio(6);
        singleLayoutHelper.setMarginTop(6);
        String name="品牌制造商提供";
        mainSingAdapter = new MainSingAdapter(getContext(), singleLayoutHelper, beans,name);

        //四张图片grid
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setSpanCount(2);
        gridLayoutHelper.setAutoExpand(true);
       mainGrAdapter = new MainGrAdapter(getContext(), gridLayoutHelper,brandListBeans);
        //周一周四
        SingleLayoutHelper sing = new SingleLayoutHelper();
        singleLayoutHelper.setItemCount(1);
        singleLayoutHelper.setAspectRatio(6);
        singleLayoutHelper.setMarginTop(6);
        String namea="周一周四：新品首发";
        singAdapter = new MainSingAdapter(getContext(), singleLayoutHelper, beans,namea);

        //周一周四新品首发图片
        GridLayoutHelper gridLayoutHelper1 = new GridLayoutHelper(2);
        gridLayoutHelper1.setAutoExpand(true);
        gridLayoutHelper1.setVGap(10);
        gridLayoutHelper1.setHGap(10);
        gridLayoutHelper1.setAspectRatio(2);
        mainGrmoneAdapter = new MainGrmoneAdapter(getContext(), newGoodsListBeans, gridLayoutHelper1);
        //人气推荐
        SingleLayoutHelper singa = new SingleLayoutHelper();
        singleLayoutHelper.setItemCount(1);
        singleLayoutHelper.setMarginTop(6);
        String nameaa="人气推荐";
        singaAdapter = new MainSingAdapter(getContext(), singleLayoutHelper, beans,nameaa);
        //人气推荐图片
        LinearLayoutHelper linear = new LinearLayoutHelper();
        linear.setItemCount(3);
        mainRenQiAdapter = new MainRenQiAdapter(getContext(), linear, hotGoodsListBeans);
        //专题精选
        SingleLayoutHelper singab = new SingleLayoutHelper();
        singleLayoutHelper.setItemCount(1);
        singleLayoutHelper.setAspectRatio(6);
        singleLayoutHelper.setMarginTop(6);
        String nameaab="专题精选";
        singabAdapter = new MainSingAdapter(getContext(), singleLayoutHelper, beans,nameaab);
        //专题精选图片
        LinearLayoutHelper helper = new LinearLayoutHelper();
        helper.setItemCount(1);
        mainTopAdapter = new MainTopAdapter(getContext(), helper, topicListBeans);
        //复用
        LinearLayoutHelper layoutHelper = new LinearLayoutHelper();

        mainJuAdapter = new MainJuAdapter(getContext(), categoryListBeans, layoutHelper);


        DelegateAdapter adapter = new DelegateAdapter(virtualLayoutManager,true);
        adapter.addAdapter(mainLinearAdapter);//banner
        adapter.addAdapter(mainGridAdapter);//网格
        adapter.addAdapter(mainSingAdapter);//第三行
        adapter.addAdapter(mainGrAdapter);//第四行
        adapter.addAdapter(singAdapter);//第五行
        adapter.addAdapter(mainGrmoneAdapter);//第6行
        adapter.addAdapter(singaAdapter);//第7行

        adapter.addAdapter(mainRenQiAdapter);//第8行 //没显示出来
        adapter.addAdapter(singabAdapter);//第9行
        adapter.addAdapter(mainTopAdapter);
        adapter.addAdapter(mainJuAdapter);
        recydh.setLayoutManager(virtualLayoutManager);
        recydh.setAdapter(adapter);

    }
    public void getBean(List<Homebean.DataBean> bean){
        if (bean.size()>0){
            beans.addAll(bean);
            mainLinearAdapter.notifyDataSetChanged();

            channelBeans.addAll(bean.get(0).getChannel());
            mainGridAdapter.notifyDataSetChanged();
            mainSingAdapter.notifyDataSetChanged();

            brandListBeans.addAll(bean.get(0).getBrandList());
            mainGrAdapter.notifyDataSetChanged();
            singAdapter.notifyDataSetChanged();

            newGoodsListBeans.addAll(bean.get(0).getNewGoodsList());
            mainGrmoneAdapter.notifyDataSetChanged();
            singaAdapter.notifyDataSetChanged();

            hotGoodsListBeans.addAll(bean.get(0).getHotGoodsList());
            mainRenQiAdapter.notifyDataSetChanged();
            singabAdapter.notifyDataSetChanged();

            topicListBeans.addAll(bean.get(0).getTopicList());
            mainTopAdapter.notifyDataSetChanged();


            categoryListBeans.addAll(bean.get(0).getCategoryList());
            mainJuAdapter.notifyDataSetChanged();
        }
    }
}
