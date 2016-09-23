package com.itheima.osc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.itheima.osc.R;

import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/9/18.
 */
public abstract class MeBaseFragment extends Fragment {
    private View mContentLayout;
    public Context mContext;
    public Retrofit mRetrofit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         mContext = getActivity();
        mContentLayout = initView();
        return mContentLayout ;
    }

    //当view创建完成了
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.81.211/")
                .build();
        initData();
    }


    protected abstract void initData();

    protected abstract View initView();

}
