package com.itheima.osc.interf;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bearabit on 2016/9/18 13:10.
 */
public interface TweetNetApi {
    /**
     * 获取动弹的xml数据（此处没有解析，原始xml数据）
     * @param url
     * @return
     */
    @GET("oschina/list/{path}")
    Call<ResponseBody> getXml(@Path("path") String url);
}
