package com.itheima.osc;

import android.app.Application;
import android.content.Context;

/**
 * Created by fuxiaoer on 2016/9/18.
 */
public class App extends Application {

    public static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
    }
}
