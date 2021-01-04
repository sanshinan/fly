package com.example.day02.logintoken.tokenmodle;

import com.example.day02.logintoken.contra.TokenContract;
import com.example.mvp.net.INetCallBack;
import com.example.mvp.net.RetrofitUtils;

import java.util.HashMap;

public class TokenModle implements TokenContract.tokenmodle {
    @Override
    public <T> void refreshtoken(String url, HashMap<String, String> hashMap, INetCallBack<T> callBack) {
        RetrofitUtils.getInstance().post(url,hashMap,callBack);
    }
}
