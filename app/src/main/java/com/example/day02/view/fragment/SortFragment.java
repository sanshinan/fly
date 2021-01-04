package com.example.day02.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day02.R;
import com.example.day02.adapter.TabAdapter;
import com.example.day02.modle.bean.Tabbean;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;

public class SortFragment extends Fragment {

    private ViewPager vp;
    private VerticalTabLayout tab;
    private List<Tabbean.DataBean.CategoryListBean> categoryBeans;
    private ArrayList<NewFragment> newFragments;

    public SortFragment() {
        categoryBeans=new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_c, null);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        tab = inflate.findViewById(R.id.tab);
        vp = inflate.findViewById(R.id.viewpage);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            TabAdapter tabAdapter = new TabAdapter(getChildFragmentManager(), categoryBeans, newFragments);
            vp.setAdapter(tabAdapter);
            tab.setupWithViewPager(vp);
            tabAdapter.notifyDataSetChanged();
        }
    }

    public void getsort(List<Tabbean.DataBean> tabeab){
        if (tabeab!=null){
            categoryBeans.addAll(tabeab.get(0).getCategoryList());
            newFragments = new ArrayList<>();
            for (int i = 0; i <categoryBeans.size() ; i++) {
                NewFragment fragment = new NewFragment();
                int id = categoryBeans.get(i).getId();
                Bundle bundle = new Bundle();
                bundle.putInt("id",id);
                fragment.setArguments(bundle);
                newFragments.add(fragment);

            }


        }
    }
}
