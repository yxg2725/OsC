package com.itheima.osc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.itheima.osc.R;

/**
 * Created by Administrator on 2016/9/18.
 */
public class ViewPagerActivity extends AppCompatActivity {

    private PagerSlidingTabStrip mPsts;
    private ViewPager mVp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initView();
        initData();
    }

    private void initView() {
        mPsts = (PagerSlidingTabStrip) findViewById(R.id.me_viewpager_pstb);
        mVp = (ViewPager) findViewById(R.id.me_viewpager_vp);
        mPsts.setViewPager(mVp);
    }

    private void initData() {
        //给viewpager设置数据
       // mVp.setAdapter();
    }

}
