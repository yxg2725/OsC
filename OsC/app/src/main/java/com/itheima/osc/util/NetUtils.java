package com.itheima.osc.util;

import com.itheima.osc.interf.TweetNetApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by bearabit on 2016/9/18 11:05.
 */
public class NetUtils {
    private static Retrofit mRetrofit;
    private static TweetNetApi mNetApi;

    static {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Urls.baseUrl)
                .build();

        mNetApi = mRetrofit.create(TweetNetApi.class);
    }

    public static void requestXml(String url, retrofit2.Callback<ResponseBody> callback){
        //1.请求本地数据
        requestLocalData(url,callback);
        //2.请求网络数据
        Call<ResponseBody> call = mNetApi.getXml(url);
        call.enqueue(callback);
    }

    private static void requestLocalData(String url, Callback<ResponseBody> callback) {

    }

}
