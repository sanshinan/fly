package com.example.day02.persent;



import com.example.day02.contract.MainContract;
import com.example.day02.modle.bean.Banbean;
import com.example.day02.modle.bean.Chbean;
import com.example.day02.modle.MainModelImpl;
import com.example.day02.modle.bean.UserBean;
import com.example.mvp.base.BasePresenter;
import com.example.mvp.net.INetCallBack;


public class MainPresenterImpl extends BasePresenter<MainContract.IMainView,MainContract.IMainModel> implements MainContract.IMainPresenter{


    //Banner业务
    @Override
    public void loginban(String url) {
        iModle.getLoginBan(url, new INetCallBack<Banbean>() {

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
        iModle.getLoginData(url, new INetCallBack<UserBean>() {@Override
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
        iModle.getLoginCh(url, new INetCallBack<Chbean>() {

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

    @Override
    public MainContract.IMainModel getiModle() {
        return new MainModelImpl(this);
    }
}
