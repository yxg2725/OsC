package com.itheima.osc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.itheima.osc.R;
import com.itheima.osc.UserInfo;

import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/9/17.
 */
public class ListViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private String mUserInfo;
    private SwipeRefreshLayout mSwipeRefresh;
    private ListView mListView;
    private Retrofit mRetrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        //创建retrofit对象
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.81.211/")
                .build();
        //获取传过来的数据
        mUserInfo = getIntent().getStringExtra(UserInfo.USERINFO);
        initView();
        initListener();

    }

    private void initView() {
        initToolBar();
        //更具传过来的数据，看是显示那个界面
        //找到控件
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.me_lvactivity_srl);
        mListView = (ListView) findViewById(R.id.me_lvactivity_lv);

        initData();

    }

    private  void  initListener(){
        mSwipeRefresh.setOnRefreshListener(this);
    }

    private void initToolBar() {
        //获取actionbar
        ActionBar supportActionBar = getSupportActionBar();
        //实现返回按钮的监听
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeButtonEnabled(true);
        if("关注".equals(mUserInfo)||"粉丝".equals(mUserInfo)){
            supportActionBar.setTitle("关注/粉丝");
        }else {
            supportActionBar.setTitle(mUserInfo);
        }


    }

    private void initData() {

        if(mUserInfo != null){
            switch (mUserInfo){
                //如果是下面四个，就用一般的listviewfragment代替
                case "我的资料":
                    //创建我的资料的adapter

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //当选择的是返回键时，返回
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    public void onRefresh() {

    }
}
