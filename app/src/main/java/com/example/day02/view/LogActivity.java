package com.example.day02.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.day02.R;
import com.example.day02.logins.contract.Logincontract;
import com.example.day02.logins.loginpersent.Loginpersent;
import com.example.mvp.base.BaseActivity;

public class LogActivity extends BaseActivity<Loginpersent> implements Logincontract.Logview {


    private EditText editLog;
    private EditText editLo;
    private Button btnLog;



    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        editLog = (EditText) findViewById(R.id.edit_log);
        editLo = (EditText) findViewById(R.id.edit_lo);
        btnLog = (Button) findViewById(R.id.btn_log);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(getusername())&&!TextUtils.isEmpty(getpassword())){
                    presenter.login(getusername(),getpassword());
                }
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_log;
    }

    @Override
    public Loginpersent getPresenter() {
        return new Loginpersent();
    }
    @Override
    public String getusername() {
        return editLog.getText().toString();
    }

    @Override
    public String getpassword() {
        return editLo.getText().toString();
    }

    @Override
    public void loginsucces() {
        Intent intent = new Intent(LogActivity.this, PushActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFail() {
        Log.i("TAG", "loginFail: "+"失败了");
    }
}