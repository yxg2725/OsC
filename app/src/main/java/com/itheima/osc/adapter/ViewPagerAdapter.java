package com.itheima.osc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itheima.osc.base.BaseFragment;

import java.util.List;

/**
 * Created by admin on 2016/9/17.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> pages;
    public ViewPagerAdapter(FragmentManager fm,List<Fragment> pages) {
        super(fm);
        this.pages = pages;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        BaseFragment fragment = (BaseFragment) pages.get(position);

        return fragment.getTitle();
    }


}
