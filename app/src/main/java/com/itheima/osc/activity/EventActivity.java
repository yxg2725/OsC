package com.itheima.osc.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.itheima.osc.R;
import com.itheima.osc.adapter.ViewPagerAdapter;
import com.itheima.osc.fragment.MyEventFragment;
import com.itheima.osc.fragment.RecentEventFragment;
import com.itheima.osc.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/17.
 */
public class EventActivity extends AppCompatActivity{

    private ViewPager find_event_vp;
    private PagerSlidingTabStrip tab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.find_event);

        tab = (PagerSlidingTabStrip) findViewById(R.id.find_event_tab);
        find_event_vp = (ViewPager) findViewById(R.id.find_event_vp);

        initData();


    }

    private void initData() {
        List<Fragment> pages= new ArrayList<Fragment>();
        pages.add(new RecentEventFragment());
        pages.add(new MyEventFragment());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),pages);
        find_event_vp.setAdapter(adapter);

        tab.setDividerColor(android.R.color.transparent);
        tab.setTextSize(23);

        tab.setTabTextColor(R.color.deepGreen,Color.GRAY);
      //  tab.setPadding(10,20,10,20);
        tab.setIndicatorColor(R.color.deepGreen);
        //tab和viewpager关联
        tab.setViewPager(find_event_vp);
    }
}
