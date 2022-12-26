package com.example;

import android.app.Application;

import com.joke.pay.BaseWXPayActivity;

import joke.annotations.WXPayEntry;

@WXPayEntry(packageName = "com.example",entryClass = BaseWXPayActivity.class)
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

    }
}
