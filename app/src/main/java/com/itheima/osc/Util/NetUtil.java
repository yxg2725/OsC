package com.itheima.osc.Util;

import com.itheima.osc.App;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.InputStream;

/**
 * Created by admin on 2016/9/21.
 */
public class NetUtil {

    //请求网络数据
    public static  FutureCallback<InputStream> getNetData(String url,FutureCallback<InputStream> callback){
        Ion.with(App.getContext())
                .load(url)
                .asInputStream()
                .setCallback(callback);
        return  callback;
    }

}
