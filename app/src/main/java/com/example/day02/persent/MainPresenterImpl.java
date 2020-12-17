package com.example.day02.persent;


import com.example.day02.base.BasePresenter;
import com.example.day02.contract.MainContract;
import com.example.day02.modle.MainModelImpl;
import com.example.day02.modle.UserBean;
import com.example.day02.util.net.INetCallBack;

public class MainPresenterImpl extends BasePresenter implements MainContract.IMainPresenter{

    private MainContract.IMainModel mainModel;
    private MainContract.IMainView mainView;

    public MainPresenterImpl(MainContract.IMainView mainView) {
        this.mainModel = new MainModelImpl();
        this.mainView = mainView;

    }
    @Override
    public void login(String url) {
        mainModel.getLoginData(url, new INetCallBack<UserBean>() {
            @Override
            public void onSuccess(UserBean userBean) {
                mainView.getData(userBean);
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
