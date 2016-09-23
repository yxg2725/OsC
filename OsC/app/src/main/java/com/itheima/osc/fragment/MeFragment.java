package com.itheima.osc.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.itheima.osc.R;
import com.itheima.osc.UserInfo;
import com.itheima.osc.activity.ListViewActivity;
import com.itheima.osc.activity.ViewPagerActivity;
import com.itheima.osc.adapter.MeAdapter;
import com.itheima.osc.api.MeRetrofit;
import com.itheima.osc.bean.UserDetailInfo;
import com.meg7.widget.CircleImageView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/9/17.
 */
public class MeFragment extends android.support.v4.app.Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private  Context mContext;
    private View mContentView;
    private ListView mListView;
    private CircleImageView mIcon;
    private LinearLayout mScore;
    private LinearLayout mCollection;
    private LinearLayout mAttention;
    private LinearLayout mFans;
    private Intent mInetntToLVA;
    private Intent mInetntToVPA;
    private Retrofit mRetrofit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        //创建一个retrofit的对象
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.81.211/")
                .build();
        //把视图打气进来
        mContentView = inflater.inflate(R.layout.me_layout, container, false);
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        //初始化数据
        initData();
        //设置监听
        initListener();
    }


    private void initView() {
        //中间的listview
        mListView = (ListView) mContentView.findViewById(R.id.me_lv);
        //头像
        mIcon = (CircleImageView) mContentView.findViewById(R.id.user_icon);
        //积分,收藏，关注，粉丝
        mScore = (LinearLayout) mContentView.findViewById(R.id.score);
        mCollection = (LinearLayout) mContentView.findViewById(R.id.collection);
        mAttention = (LinearLayout) mContentView.findViewById(R.id.attention);
        mFans = (LinearLayout) mContentView.findViewById(R.id.fans);


    }

    private void initListener() {
        mIcon.setOnClickListener(this);
        mScore.setOnClickListener(this);
        mCollection.setOnClickListener(this);
        mAttention.setOnClickListener(this);
        mFans.setOnClickListener(this);
        //给中间的listview设置数据
        mListView.setOnItemClickListener(this);
    }


    private void initData() {
        //给中间listview设置数据
        MeAdapter mAdapter = new MeAdapter();
        mListView.setAdapter(mAdapter);
        //点击用户图标，跳转listviewActivity
        mInetntToLVA = new Intent(mContext,ListViewActivity.class);
        //点击用户图标，跳转listviewActivity
        mInetntToVPA = new Intent(mContext,ViewPagerActivity.class);
        //进行网络数据访问
        MeRetrofit meRetrofit = mRetrofit.create(MeRetrofit.class);
        Call<ResponseBody> userInfo = meRetrofit.getUserInfo();
        //联网异步访问网络
        userInfo.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //访问网络请求成功
                ResponseBody body = response.body();
                try {
                    String string = body.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //访问网络失败
                System.out.println(t);
            }
        });
    }

    @Override
    public void onClick(View v) {

        //点击相应的条目，实现相应的操作
        switch (v.getId()){
            case R.id.user_icon:
                //携带数据
                mInetntToLVA.putExtra(UserInfo.USERINFO,UserInfo.USERDATA);
                startActivity(mInetntToLVA);
                break;

            case R.id.attention:
                mInetntToVPA.putExtra(UserInfo.USERINFO,UserInfo.ATTENTION);
                startActivity(mInetntToVPA);
                break;

            case R.id.collection:
                mInetntToVPA.putExtra(UserInfo.USERINFO,UserInfo.COLLECTION);
                startActivity(mInetntToVPA);
                break;

            case R.id.fans:
                mInetntToVPA.putExtra(UserInfo.USERINFO,UserInfo.FANS);
                startActivity(mInetntToVPA);
                break;

        }

    }

    //listview的监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position){
            //第一个条目时 ，跳珠viewpageractivity
            case 0:
                mInetntToVPA.putExtra(UserInfo.USERINFO,UserInfo.MESSAGE);
                startActivity(mInetntToVPA);
                break;
            //其他条目时 ，跳转listviewactivity
            case 1:
                //博客
                mInetntToLVA.putExtra(UserInfo.USERINFO,UserInfo.BLOG);
                startActivity(mInetntToLVA);
                break;
            case 2:
                //便签
                mInetntToLVA.putExtra(UserInfo.USERINFO,UserInfo.NOTE);
                startActivity(mInetntToLVA);
                break;
            case 3:
                //团队
                mInetntToLVA.putExtra(UserInfo.USERINFO,UserInfo.TEAM);
                startActivity(mInetntToLVA);
                break;
        }
    }
}
