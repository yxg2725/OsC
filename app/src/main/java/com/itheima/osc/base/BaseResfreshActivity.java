package com.itheima.osc.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.osc.R;
import com.itheima.osc.Util.NetUtil;
import com.itheima.osc.Util.XmlUtils;
import com.itheima.osc.adapter.FriendAdapter;
import com.itheima.osc.bean.Active;
import com.itheima.osc.bean.ActiveList;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.InputStream;
import java.util.List;

/**
 * Created by admin on 2016/9/16.
 */
public abstract class BaseResfreshActivity extends AppCompatActivity {


    public  Context context;
    public PullToRefreshListView  lv;
    public SwipeRefreshLayout  swiperefresh;


    public static final int PULL_FROME_START = 0;
    public static final int PULL_FROME_END = 1;
    public int currentMode = -1;
    public   int index =0;
    public BaseAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = BaseResfreshActivity.this;

        setContentView(getViewId());

        initView();
        initListener();
        initData();
    }

    public  void initView(){
        //找出listivew 和refreshlayout
          lv = (PullToRefreshListView) findViewById(findlistVIewId());
          swiperefresh = (SwipeRefreshLayout) findViewById(findRefreshLayoutId());

        //显示数据
       showListViewData(lv);

          //设置刷新方式为  上啦加载
          lv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
    }

    public  abstract void showListViewData(PullToRefreshListView lv);


    //刷新监听
    private   void initListener(){

        //下拉刷新监听
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentMode=PULL_FROME_START;

                initData();
            }
        });

        //上啦加载监听
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                currentMode=PULL_FROME_END;
                initData();
            }
        });

        //listView滚动监听
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (lv.getRefreshableView().getFirstVisiblePosition() == 0){
                    swiperefresh.setEnabled(true);
                }else{
                    swiperefresh.setEnabled(false);
                }
            }
        });

         otherListener();

    }

    //其他监听事件
    protected abstract void otherListener();


    public  void initData(){
        //判断是刷新模式还是加载模式
        if (currentMode==PULL_FROME_END){//上拉
            index = index+1;
        }else if(currentMode==PULL_FROME_START){//下拉
            index = 0;
        }

        //从服务器获取数据
        //BaseUrl/oschina/list/active_list1
        NetUtil.getNetData(getUrl(index),callback);

    }

    FutureCallback<InputStream> callback = new FutureCallback<InputStream>() {
        @Override
        public void onCompleted(Exception e, InputStream result) {

            handleData(e, result);
        }
    };

    public abstract int findlistVIewId(); //获取listVIewid
    public abstract int findRefreshLayoutId();//获取refreshLayoutid
    protected abstract void handleData(Exception e, InputStream result); //处理数据
    public abstract int getViewId();//布局id
    public abstract String getUrl(int index);//网址


}
