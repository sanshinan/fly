package com.example.mvp.base;

public abstract class BasePresenter<V extends BaseView,M extends BaseModle> {
    public V iView;
    public M iModle;
    public void attachView(V v){
        iView = v;
        iModle=getiModle();
    }
    public void detachView(){
        iView=null;
        iModle=null;
    }

    public abstract M getiModle();

}
