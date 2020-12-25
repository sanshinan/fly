package com.example.day02.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.day02.R;
import com.example.day02.contract.MainContract;
import com.example.day02.modle.bean.Banbean;
import com.example.day02.modle.bean.Chbean;
import com.example.day02.modle.bean.Homebean;
import com.example.day02.modle.bean.UserBean;
import com.example.day02.persent.MainPresenterImpl;
import com.example.day02.view.fragment.OneFragment;
import com.example.mvp.base.BaseActivity;
import com.example.mvp.base.BasePresenter;
import com.example.mvp.net.URLConstant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class PushActivity extends BaseActivity<MainPresenterImpl> implements MainContract.IMainView {

    private List<Homebean.DataBean> beans;
    private com.google.android.material.bottomnavigation.BottomNavigationView bvBottomNavigation;
    private ArrayList<Fragment> fragments;
    private OneFragment oneFragment;
    private int lastIndex;
    @Override
    protected void initData() {
//        presenter.homepage(URLConstant.JIAJU_Z);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        beans=new ArrayList<>();
        fragments = new ArrayList<>();
        oneFragment = new OneFragment();
        fragments.add(oneFragment);
        for (int i = 0; i <4 ; i++) {
            fragments.add(new Fragment());
        }
        setFragmentPosition(0);
        bvBottomNavigation = (BottomNavigationView) findViewById(R.id.bv_bottomNavigation);
        //监听
        bvBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_message:
                        setFragmentPosition(0);
                        break;
                    case R.id.menu_contacts:
                        setFragmentPosition(1);
                        break;
                    case R.id.menu_discover:
                        setFragmentPosition(2);
                        break;
                    case R.id.menu_me:
                        setFragmentPosition(3);
                        break;
                    case R.id.menu_me_two:
                        setFragmentPosition(4);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_push;
    }

    @Override
    public MainPresenterImpl getPresenter() {
        return new MainPresenterImpl();
    }


    @Override
    public void getBan(Banbean banbean) {

    }

    @Override
    public void getData(UserBean string) {

    }

    @Override
    public void getCh(Chbean chbean) {

    }

    @Override
    public void gethome(Homebean homebean) {
        beans.notify();
        beans.add(homebean.getData());
        Log.i("TAG", "gethome: "+beans.toString());
    }
    private void setFragmentPosition(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = fragments.get(position);
        Fragment lastFragment = fragments.get(lastIndex);
        lastIndex = position;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.f_layout, currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }
}