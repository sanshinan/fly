package com.example.day02.persent;


import com.example.day02.base.BasePresenter;
import com.example.day02.contract.MainContract;
import com.example.day02.modle.bean.Banbean;
import com.example.day02.modle.bean.Chbean;
import com.example.day02.modle.MainModelImpl;
import com.example.day02.modle.bean.UserBean;
import com.example.day02.util.net.INetCallBack;

public class MainPresenterImpl extends BasePresenter<MainContract.IMainView> implements MainContract.IMainPresenter{

    private MainContract.IMainModel mainModel;


    public MainPresenterImpl(MainContract.IMainView mainView) {
        this.mainModel = new MainModelImpl();

    }
    //Banner业务
    @Override
    public void loginban(String url) {
        mainModel.getLoginBan(url, new INetCallBack<Banbean>() {

            @Override
            public void onSuccess(Banbean banbean) {
                iView.getBan(banbean);
            }

            @Override
            public void onFail(String err) {

            }
        });
    }
    //新闻业务
    @Override
    public void login(String url) {
        mainModel.getLoginData(url, new INetCallBack<UserBean>() {
            @Override
            public void onSuccess(UserBean userBean) {
                iView.getData(userBean);
            }

            @Override
            public void onFail(String err) {

            }
        });
    }
    //成绩业务
    @Override
    public void loginCh(String url) {
        mainModel.getLoginCh(url, new INetCallBack<Chbean>() {

            @Override
            public void onSuccess(Chbean chbean) {
                iView.getCh(chbean);
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    @Override
    public void loginResult(String result) {
//做判单的

    }
}
