package com.example.day02.util.net;

public interface INetCallBack<T> {

    public void onSuccess(T t);

    public void onFail(String err);

}
