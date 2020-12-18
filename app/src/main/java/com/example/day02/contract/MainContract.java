package com.example.day02.contract;

import com.example.day02.base.BaseModle;
import com.example.day02.base.BaseView;
import com.example.day02.modle.bean.Banbean;
import com.example.day02.modle.bean.Chbean;
import com.example.day02.modle.bean.UserBean;
import com.example.day02.util.net.INetCallBack;

public class MainContract {
    public interface IMainModel extends BaseModle {
        //Bnanner 业务
        <T> void getLoginData(String url, INetCallBack<T> callBack);
        //新闻业务
        <T> void getLoginBan(String url, INetCallBack<T> callBack);
        //成绩业务
        <T> void getLoginCh(String url, INetCallBack<T> callBack);
    }
    public  interface IMainPresenter {
        //Bnanner 业务
        void loginban(String url);
        //新闻业务
        void login(String url);
        //成绩业务
        void loginCh(String url);
        void loginResult(String result);
    }
    public interface IMainView extends BaseView {
        String getUserName();
        String getPassword();
        //Bnanner 业务
        void getBan(Banbean banbean);
        //新闻业务
        void getData(UserBean string);
        //成绩业务
        void getCh(Chbean chbean);
    }
}
