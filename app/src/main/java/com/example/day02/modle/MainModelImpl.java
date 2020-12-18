package com.example.day02.modle;


import com.example.day02.contract.MainContract;
import com.example.day02.util.net.INetCallBack;
import com.example.day02.util.net.RetrofitUtils;

public class MainModelImpl implements MainContract.IMainModel {

    private MainContract.IMainPresenter presenter;
    public MainModelImpl(MainContract.IMainPresenter presenter){
        this.presenter=presenter;
    }

    @Override
    public <T> void getLoginData(String url, INetCallBack<T> callBack) {
        RetrofitUtils.getInstance().get(url,callBack);
    }

    @Override
    public <T> void getLoginBan(String url, INetCallBack<T> callBack) {
        RetrofitUtils.getInstance().get(url,callBack);
    }

    @Override
    public <T> void getLoginCh(String url, INetCallBack<T> callBack) {
        RetrofitUtils.getInstance().get(url,callBack);
    }
}
