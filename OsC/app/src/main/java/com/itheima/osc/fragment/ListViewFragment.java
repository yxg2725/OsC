package com.itheima.osc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.itheima.osc.R;
import com.itheima.osc.UserInfo;
import com.itheima.osc.api.MeRetrofit;

import retrofit2.Callback;

/**
 * Created by Administrator on 2016/9/17.
 */
public class ListViewFragment extends MeBaseFragment {

    private LinearLayout mContentLayout;
    private ListView mListView;
    private String mUserInfo;




    public View initView() {
        View view = View.inflate(mContext, R.layout.me_lv_layout, null);
        mListView = (ListView) view.findViewById(R.id.me_listFragment_lv);
        return view;
    }

    public void initData() {
        //初始化retrofit
        MeRetrofit meRetrofit = mRetrofit.create(MeRetrofit.class);

        //取出argment
        Bundle arguments = getArguments();
        mUserInfo = arguments.getString(UserInfo.USERINFO);
        //对取出来的信息进行判断
        switch (mUserInfo){
            case "我的资料":
                //拼接字符串，请求网络
               // Callback<String> userInfo = meRetrofit.getUserInfo();

                break;
            case "博客":
                break;
            case "便签":
                break;
            case "团队":
                break;

        }

    }

}
