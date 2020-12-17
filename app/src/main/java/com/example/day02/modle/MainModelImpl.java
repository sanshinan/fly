package com.example.day02.modle;


import com.example.day02.contract.MainContract;
import com.example.day02.util.net.INetCallBack;
import com.example.day02.util.net.RetrofitUtils;

public class MainModelImpl implements MainContract.IMainModel {



    @Override
    public <T> void getLoginData(String url, INetCallBack<T> callBack) {
        RetrofitUtils.getInstance().get(url,callBack);
    }
}
