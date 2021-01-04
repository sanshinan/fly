package com.example.day02.logins.loginpersent;

import com.example.day02.logins.contract.Logincontract;
import com.example.day02.logins.loginmodle.LoginModle;
import com.example.day02.modle.bean.Loginbean;
import com.example.mvp.base.BasePresenter;
import com.example.mvp.net.INetCallBack;
import com.tencent.mmkv.MMKV;

import java.util.HashMap;

public class Loginpersent extends BasePresenter<Logincontract.Logview, LoginModle> implements Logincontract.LogPersent {
    @Override
    public void login(String name, String password) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username",name);
        hashMap.put("password",password);
        iModle.getlogin("auth/login?", hashMap, new INetCallBack<Loginbean>() {

            @Override
            public void onSuccess(Loginbean loginbean) {
                    if (loginbean.getData().getCode()==200){
                        MMKV mmkv = MMKV.defaultMMKV();
                        mmkv.encode("token",loginbean.getData().getToken());
                        mmkv.encode("uid",loginbean.getData().getUserInfo().getUid());
                        mmkv.encode("username",loginbean.getData().getUserInfo().getUsername());
                        mmkv.encode("nickname",loginbean.getData().getUserInfo().getNickname());
                        mmkv.encode("gender",loginbean.getData().getUserInfo().getGender());
                        mmkv.encode("avatar",loginbean.getData().getUserInfo().getAvatar());
                        mmkv.encode("birthday",loginbean.getData().getUserInfo().getBirthday());
                        iView.loginsucces();
                    }else {
                        iView.loginFail();
                    }
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    @Override
    public LoginModle getiModle() {
        return new LoginModle();
    }
}
