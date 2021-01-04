package com.example.day02.logintoken.contra;

import com.example.mvp.base.BaseModle;
import com.example.mvp.base.BaseView;
import com.example.mvp.net.INetCallBack;

import java.util.HashMap;

public class TokenContract {

    public interface tokenview extends BaseView {
        void condown();
    }
    public interface tokenmodle extends BaseModle {
      <T>  void refreshtoken(String url, HashMap<String,String> hashMap, INetCallBack<T> callBack);
    }
    public interface tokenpersent{
        void refreshtoken();
    }
}
