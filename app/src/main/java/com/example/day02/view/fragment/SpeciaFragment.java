package com.example.day02.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.day02.R;
import com.example.day02.modle.bean.Specialbean;
import com.example.day02.view.speciaadapter.Ztadapter;

import java.util.ArrayList;
import java.util.List;

public class SpeciaFragment extends Fragment {

    private RecyclerView recyspecia;
     List<Specialbean.DataBeanX.DataBean> dataBeans;
    private Ztadapter ztadapter;

    public SpeciaFragment() {
        dataBeans=new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_b, null);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        recyspecia = inflate.findViewById(R.id.recy_specia);
        //初始化布局管理器
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getActivity());
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        ztadapter = new Ztadapter(dataBeans, getContext(), linearLayoutHelper);

        DelegateAdapter adapter = new DelegateAdapter(virtualLayoutManager, false);
        //专题
        adapter.addAdapter(ztadapter);
        recyspecia.setLayoutManager(virtualLayoutManager);
        recyspecia.setAdapter(adapter);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            if (dataBeans.size()>0){
                ztadapter.notifyDataSetChanged();
            }
        }
    }

    public void getSpecia(List<Specialbean.DataBeanX.DataBean>  beans){
        if (beans!=null &&beans.size()>0){
            dataBeans.addAll(beans);

        }
    }
}
