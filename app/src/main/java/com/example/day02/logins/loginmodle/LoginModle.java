package com.example.day02.logins.loginmodle;

import com.example.day02.logins.contract.Logincontract;
import com.example.mvp.net.INetCallBack;
import com.example.mvp.net.RetrofitUtils;

import java.util.HashMap;

public class LoginModle implements Logincontract.LogMode {
    @Override
    public <T> void getlogin(String url, HashMap<String, String> hashMap, INetCallBack<T> callBack) {
        RetrofitUtils.getInstance().post(url,hashMap,callBack);
    }
}
