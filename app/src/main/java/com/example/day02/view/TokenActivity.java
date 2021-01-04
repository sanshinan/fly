package com.example.day02.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.day02.R;
import com.example.day02.logintoken.tokenpersent.Tokenpersent;
import com.example.mvp.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class TokenActivity extends BaseActivity<Tokenpersent> {


    private TextView textToken;
    private Disposable subscribe;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        textToken = (TextView) findViewById(R.id.text_token);
        subscribe = Observable
                .interval(1, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (aLong <= 3) {
                            textToken.setText((3 - aLong) + "");
                        } else {
                            subscribe.dispose();
                        }
                    }
                });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_token;
    }

    @Override
    public Tokenpersent getPresenter() {
        return new Tokenpersent();
    }
}