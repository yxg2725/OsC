package com.itheima.osc.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by bearabit on 2016/9/17 19:37.
 */
public class BasePagerAdapter extends PagerAdapter {
    protected ArrayList<Object> mData;
    protected Context mContext;

    public BasePagerAdapter(Context context) {
        super();
        mContext = context;
    }

    public BasePagerAdapter(ArrayList<Object> list) {
        super();
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(list);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
