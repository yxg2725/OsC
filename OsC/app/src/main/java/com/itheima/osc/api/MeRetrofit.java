package com.itheima.osc.api;

import com.itheima.osc.bean.UserDetailInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/9/18.
 */
public interface MeRetrofit  {

    //我的
    @GET("oschina/information/my_information.xml")
    Call<ResponseBody> getUserInfo();

    //博客
    @GET("oschina/list/favorite_list1")
    Call<String> getBlogInfo();



}
