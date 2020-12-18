package com.example.day02.view;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.day02.R;
import com.example.day02.base.BaseActivity;
import com.example.day02.contract.MainContract;
import com.example.day02.modle.bean.Banbean;
import com.example.day02.modle.bean.Chbean;
import com.example.day02.modle.bean.UserBean;
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
    private TextView textx;

    @Override
    protected void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(URLConstant.NEWLIST);
                presenter.loginban(URLConstant.NEWBAN);
                presenter.loginCh(URLConstant.NEWCH);
            }
        });

    }

    @Override
    protected void initView() {

        editZh = (EditText) findViewById(R.id.edit_zh);
        editMm = (EditText) findViewById(R.id.edit_mm);
        button = (Button) findViewById(R.id.button);
        use=new ArrayList<>();
        textx = (TextView) findViewById(R.id.textx);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenterImpl getPresenter() {
        return new MainPresenterImpl();
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
    public void getBan(Banbean banbean) {
        Log.i("TTT", "getBan: "+banbean.toString());
    }

    @Override
    public void getData(UserBean string) {
        //网络获取的数据
        use.addAll(string.getNews());
        Log.i("TAG", "getData: "+string.toString());
        textx.setText(string.toString());
    }

    @Override
    public void getCh(Chbean chbean) {
        Log.i("TTT", "getCh: "+chbean.toString());
    }


}