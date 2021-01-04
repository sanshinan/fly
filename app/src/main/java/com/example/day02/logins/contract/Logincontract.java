package com.example.day02.logins.contract;

import com.example.mvp.base.BaseModle;
import com.example.mvp.base.BaseView;
import com.example.mvp.net.INetCallBack;

import java.util.HashMap;

public class Logincontract {

    public interface LogMode extends BaseModle {
    <T> void getlogin(String url, HashMap<String,String> hashMap, INetCallBack<T> callBack);
    }

    public interface LogPersent{
        void login(String name,String password);
    }

    public interface Logview extends BaseView {
        String getusername();
        String getpassword();

        void loginsucces();
        void loginFail();
    }
}
