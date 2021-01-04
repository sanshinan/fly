package com.example.day02.logintoken.tokenpersent;

import android.text.TextUtils;
import android.util.Log;

import com.example.day02.logintoken.contra.TokenContract;
import com.example.day02.logintoken.tokenmodle.TokenModle;
import com.example.day02.modle.bean.Tokenbean;
import com.example.mvp.base.BasePresenter;
import com.example.mvp.net.INetCallBack;
import com.tencent.mmkv.MMKV;

import java.util.HashMap;

public class Tokenpersent extends BasePresenter<TokenContract.tokenview, TokenModle> implements TokenContract.tokenpersent {


    @Override
    public TokenModle getiModle() {
        return new TokenModle();
    }

    @Override
    public void refreshtoken() {
        MMKV mmkv = MMKV.defaultMMKV();
        if (!TextUtils.isEmpty(mmkv.decodeString("token"))){
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("X-Nideshop-Token",mmkv.decodeString("token"));
            iModle.refreshtoken("auth/refreshToken", hashMap, new INetCallBack<Tokenbean>() {

                @Override
                public void onSuccess(Tokenbean tokenbean) {
                    mmkv.encode("token",tokenbean.getData());
                }

                @Override
                public void onFail(String err) {
                    Log.i("TAG", "onFail: "+"刷新token失败");
                }
            });
        }
    }
}
