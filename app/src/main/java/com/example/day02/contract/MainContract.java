package com.example.day02.contract;



import com.example.day02.modle.bean.Banbean;
import com.example.day02.modle.bean.Chbean;
import com.example.day02.modle.bean.Homebean;
import com.example.day02.modle.bean.Specialbean;
import com.example.day02.modle.bean.Tabbean;
import com.example.day02.modle.bean.UserBean;

import com.example.mvp.base.BaseModle;
import com.example.mvp.base.BaseView;
import com.example.mvp.net.INetCallBack;

public class MainContract {
    public interface IMainModel extends BaseModle {
        //Bnanner 业务
        <T> void getLoginData(String url, INetCallBack<T> callBack);
        //新闻业务
        <T> void getLoginBan(String url, INetCallBack<T> callBack);
        //成绩业务
        <T> void getLoginCh(String url, INetCallBack<T> callBack);
        //购物首页业务
        <T> void getHome(String url, INetCallBack<T> callBack);
        //购物专题
        //购物首页业务
        <T> void getSpecia(String url, INetCallBack<T> callBack);
        //竖着tab
        <T> void getablayout(String url,INetCallBack<T> callBack);

    }
    public  interface IMainPresenter {
        //Bnanner 业务
        void loginban(String url);
        //新闻业务
        void login(String url);
        //成绩业务
        void loginCh(String url);
        //购物首页业务
        void homepage(String url);
        //购物专题
        void Spercia(String url);
        //购物竖tablayout
        void Tab(String url);
    }
    public interface IMainView extends BaseView {

        void getBan(Banbean banbean);
        //新闻业务
        void getData(UserBean string);
        //成绩业务
        void getCh(Chbean chbean);
        //购物首页业务
        void gethome(Homebean homebean);
        //购物专题
        void getSpercia(Specialbean specialbean);
        //购物竖着tab
        void gettab(Tabbean tabbean);
    }
}
