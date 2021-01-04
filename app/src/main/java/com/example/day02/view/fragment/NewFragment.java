package com.example.day02.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day02.R;
import com.example.day02.adapter.SortAdapter;
import com.example.day02.modle.bean.Sortbean;
import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewFragment extends Fragment {


    String bannerurl="https://cdwan.cn/api/catalog/current?id=";
    List<Sortbean.DataBean>  sort;
    List<Sortbean.DataBean.CurrentCategoryBean.SubCategoryListBean> catas;
    private int a;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
        }
    };
    private ImageView img;
    private TextView text1;
    private RecyclerView recy;
    private SortAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        a=getArguments().getInt("id");
        View inflate = inflater.inflate(R.layout.fragment_new, null);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(bannerurl+a+"");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    if (urlConnection.getResponseCode()==200){
                        InputStream in = urlConnection.getInputStream();
                        int len=0;
                        byte[] bytes=new byte[1024];
                        StringBuffer sb = new StringBuffer();
                        while ((len=in.read(bytes))!=-1){
                            sb.append(new String(bytes,0,len));
                        }
                        String json = sb.toString();
                        Sortbean sortbean = new Gson().fromJson(json, Sortbean.class);
                        sort.add(sortbean.getData());
                        catas.addAll(sortbean.getData().getCurrentCategory().getSubCategoryList());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(getContext()).load(sort.get(0).getCurrentCategory().getBanner_url()).into(img);
                                text1.setText("----"+sort.get(0).getCurrentCategory().getName()+"----");
                            }
                        });

                        handler.sendEmptyMessage(1);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void initView(View inflate) {
        catas=new ArrayList<>();
        sort=new ArrayList<>();

        img = inflate.findViewById(R.id.img_sorat);
        text1 = inflate.findViewById(R.id.text_sat);
        recy = inflate.findViewById(R.id.recy_sorat);

        recy.setLayoutManager(new GridLayoutManager(getContext(),3));
        adapter = new SortAdapter(getContext(), catas);
        recy.setAdapter(adapter);

    }
}
