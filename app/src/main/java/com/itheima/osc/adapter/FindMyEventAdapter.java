package com.itheima.osc.adapter;

import android.view.View;

import java.util.List;

/**
 * Created by admin on 2016/9/18.
 */
public class FindMyEventAdapter extends MyBaseAdapter{
    public FindMyEventAdapter(List datas) {
        super(datas);
    }

    @Override
    public int getItemLayoutId(int position) {
        return 0;
    }

    @Override
    public Object createViewHolderAndFindViewById(int position, View convertView) {
        return null;
    }

    @Override
    public void showData(int position, Object viewHolder, Object data) {

    }
}
