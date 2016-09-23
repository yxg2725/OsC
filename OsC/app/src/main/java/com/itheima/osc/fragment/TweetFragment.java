package com.itheima.osc.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.itheima.osc.R;
import com.itheima.osc.adapter.TweetFragmentVPAdapter;

/**
 * Created by bearabit on 2016/9/16 19:34.
 * 1.正常来说fragment这里应该是需要联网加载数据的，但是因为接口文档中直接给出了ViewPager三个页面数据所使用的Url，
 * 所以这里没有必要在这里再联网获取这三个Url，而且接口文档中也没有获取这三个Url的Url。这里逻辑感觉有问题，问题就
 * 出在这里。
 * 2.考虑到上面的情况，加载数据就全部放在ViewPager的adapter中执行。
 * 3.第一次进入fragment时需要加载第一页数据，这里直接通过ViewPager的InitialItem方法实现，就不需要在当前类中再
 * 实现。
 * 4.当前类主要的工作仅有两个：1）提供fragment的布局 2）关联tab和viewpager。
 */
public class TweetFragment extends Fragment {
    private PagerSlidingTabStrip mTabs;
    private ViewPager mViewPager;
    private SwipeRefreshLayout mSwipeLayout;
    private final int mPageLimit = 4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = View.inflate(getActivity(), R.layout.fragment_dongtan, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View root) {
        //关联tab和viewpager
        mTabs = (PagerSlidingTabStrip) root.findViewById(R.id.fragment_dongtan_psts);
        mViewPager = (ViewPager) root.findViewById(R.id.fragment_dongtan_viewpager);
        mViewPager.setOffscreenPageLimit(mPageLimit);
        mViewPager.setAdapter(new TweetFragmentVPAdapter(getActivity()));
        mTabs.setViewPager(mViewPager);
    }
}
