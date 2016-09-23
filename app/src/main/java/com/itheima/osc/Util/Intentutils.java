package com.itheima.osc.Util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by admin on 2016/9/16.
 */
public class Intentutils {

    //跳转界面
    public static void startIntent(Context context,Class clazz){
        Intent intent = new Intent(context,clazz);
        context.startActivity(intent);
    }

}
