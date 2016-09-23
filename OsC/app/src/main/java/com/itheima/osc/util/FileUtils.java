package com.itheima.osc.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.itheima.osc.App;

/**
 * Created by bearabit on 2016/9/18 16:17.
 */
public class FileUtils {
    private static SharedPreferences mSharedPreferences;

    public static String getXmlData(String key) {
        return getString(key);
    }

    public static boolean saveXmlData(String key, String xml) {
        return putString(key, xml);
    }

    private static boolean putString(String key, String data) {
        if (mSharedPreferences == null) {
            Context context = App.mApplicationContext;
            mSharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        }
        return mSharedPreferences.edit().putString(key, data).commit();
    }

    private static String getString(String key) {
        if (mSharedPreferences == null) {
            Context context = App.mApplicationContext;
            mSharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getString(key, "");
    }
}
