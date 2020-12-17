package com.example.day02.contract;

import com.example.day02.modle.UserBean;
import com.example.day02.util.net.INetCallBack;

public class MainContract {
    public interface IMainModel {
        <T> void getLoginData(String url, INetCallBack<T> callBack);
    }
    public  interface IMainPresenter {
        void login(String url);
        void loginResult(String result);
    }
    public interface IMainView {
        String getUserName();
        String getPassword();
        void getData(UserBean string);
    }
}
