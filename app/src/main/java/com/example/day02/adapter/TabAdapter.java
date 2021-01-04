package com.example.day02.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.day02.modle.bean.Tabbean;
import com.example.day02.view.fragment.NewFragment;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentStatePagerAdapter {
    private List<Tabbean.DataBean.CategoryListBean> categoryBeans;
     private ArrayList<NewFragment> newFragments;

    public TabAdapter(@NonNull FragmentManager fm, List<Tabbean.DataBean.CategoryListBean> categoryBeans, ArrayList<NewFragment> newFragments) {
        super(fm);
        this.categoryBeans = categoryBeans;
        this.newFragments = newFragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return newFragments.get(position);
    }

    @Override
    public int getCount() {
        return newFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categoryBeans.get(position).getName();
    }
}
