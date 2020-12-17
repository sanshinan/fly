package com.example.day02;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.day02.base.BaseActivity;
import com.example.day02.contract.MainContract;
import com.example.day02.modle.MainModelImpl;
import com.example.day02.modle.UserBean;
import com.example.day02.persent.MainPresenterImpl;
import com.example.day02.util.net.URLConstant;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainContract.IMainView {

    private String url="exam2003/anewslist.json";
    private EditText editZh;
    private EditText editMm;
    private Button button;
    List<UserBean.NewsBean> use;
    @Override
    protected void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(URLConstant.NEWLIST);
            }
        });

    }

    @Override
    protected void initView() {

        editZh = (EditText) findViewById(R.id.edit_zh);
        editMm = (EditText) findViewById(R.id.edit_mm);
        button = (Button) findViewById(R.id.button);
        use=new ArrayList<>();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenterImpl getPresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    public String getUserName() {
        return editZh.getText().toString();
    }

    @Override
    public String getPassword() {
        return editMm.getText().toString();
    }

    @Override
    public void getData(UserBean string) {
        //网络获取的数据
        use.addAll(string.getNews());
        Log.i("TAG", "getData: "+string.toString());

    }


}