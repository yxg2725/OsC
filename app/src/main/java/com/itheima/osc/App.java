package com.itheima.osc;

import android.app.Application;
import android.content.Context;

/**
 * Created by admin on 2016/9/17.
 */
public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }


}
