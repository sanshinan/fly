package com.example.day02.app;

import android.app.Application;

import com.tencent.mmkv.MMKV;

public class Myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this);
    }
}
